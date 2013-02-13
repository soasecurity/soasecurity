/*
*  Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.wso2.carbon.user.mgt.sample;

import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.wso2.carbon.user.mgt.stub.UserAdminStub;

import java.io.File;

/**
 * This sample client to list user names that is associate with the carbon server.
 * For that you need to call the UserAdmin API in Carbon server.
 * As UserAdmin API is secured one. To call it, you want to pass valid credentials as
 * basic authentication header
 */
public class SampleUserRoleMgtClient {


    /**
     * Server url of the WSO2 Carbon Server
     */
    private static String SEVER_URL = "https://localhost:9443/services/";

    /**
     * User Name to access WSO2 Carbon Server
     */
    private static String USER_NAME = "admin";

    /**
     * Password of the User who access the WSO2 Carbon Server
     */
    private static String PASSWORD = "admin";


    public static void main(String args[]){


        /**
         * trust store path.  this must contains server's certificate. 
         */
        String trustStore = System.getProperty("user.dir") + File.separator +
                                                         "src" + File.separator + "main" + File.separator +
                                                         "resources" + File.separator + "wso2carbon.jks";

        /**
         * Call to https://localhost:9443/services/   uses HTTPS protocol.
         * Therefore we to validate the server certificate. The server certificate is looked up in the
         * trust store. Following code sets what trust-store to look for and its JKs password.
         * Note : The trust store should have server's certificate.
         */

        System.setProperty("javax.net.ssl.trustStore",  trustStore );

        System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");

        /**
         * Axis2 configuration context
         */
        ConfigurationContext configContext;

        try {

            /**
             * Create a configuration context. A configuration context contains information for
             * axis2 environment. This is needed to create an axis2 client
             */
            configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem( null, null);

            String serviceEndPoint = SEVER_URL + "UserAdmin";

            UserAdminStub adminStub = new UserAdminStub(configContext, serviceEndPoint);
            ServiceClient client = adminStub._getServiceClient();
            Options option = client.getOptions();

            /**
             * setting basic auth headers for authentication for user admin 
             */
            HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
            option.setProperty(HTTPConstants.COOKIE_STRING, null);
            auth.setUsername(USER_NAME);
            auth.setPassword(PASSWORD);
            auth.setPreemptiveAuthentication(true);
            option.setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, auth);
            option.setManageSession(true);

            /**
             * Do any thing with user admin API.  you can list user. add users, roles, assignments...
             * Here as an example just have implemented list user operation.
             */


            String[] users = adminStub.listUsers("*");
            if(users != null){
                System.out.println("Listing user names of Carbon server...... ");
                for(String user : users){
                    System.out.println("User Name : " + user);
                }
            }

            String authCookie = (String) adminStub._getServiceClient().getServiceContext()
                    .getProperty(HTTPConstants.COOKIE_STRING);

            System.out.println(authCookie);
            /**
             * If WSO2 Carbon has been connected with multiple user stores. Say you need to create a user in
             * domain called  it.com,  user name must be passed as   it.com/asela
             */
            //adminStub.addUser("it.com/asela", "password", null, null, null);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
