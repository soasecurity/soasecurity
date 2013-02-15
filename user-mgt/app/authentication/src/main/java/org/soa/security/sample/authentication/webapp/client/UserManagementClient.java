/*
 *  Copyright (c) WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */


package org.soa.security.sample.authentication.webapp.client;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.soa.security.sample.authentication.webapp.WebAppConstants;
import org.wso2.carbon.um.ws.api.stub.RemoteUserStoreManagerServiceStub;

import java.util.Properties;

/**
 *
 */
public class UserManagementClient {

    private RemoteUserStoreManagerServiceStub userMgtStub;

    private static String authCookie;

    public UserManagementClient(Properties properties) {

        String backEndServerURL = properties.getProperty(WebAppConstants.ClientConstants.SERVER_URL);
        ConfigurationContext configCtx = (ConfigurationContext)properties.
                                                        get(WebAppConstants.ClientConstants.CONTEXT);
        if(backEndServerURL != null){
            backEndServerURL = backEndServerURL.trim();
            if (!backEndServerURL.endsWith("/")) {
                backEndServerURL += "/";
            }
        }
        String userMgtServiceURL = backEndServerURL + "services/RemoteUserStoreManagerService";

        try {
            userMgtStub = new RemoteUserStoreManagerServiceStub(configCtx, userMgtServiceURL);
            ServiceClient client = userMgtStub._getServiceClient();
            Options option = client.getOptions();
            HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
            auth.setUsername(properties.getProperty(WebAppConstants.ClientConstants.SERVER_USER));
            auth.setPassword(properties.getProperty(WebAppConstants.ClientConstants.SERVER_PASSWORD));
            auth.setPreemptiveAuthentication(true);
            option.setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, auth);
            option.setManageSession(true);
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }
    }

    public void updateCredentials(String userId, String oldPassword, String newPassword) throws Exception {

        userMgtStub.updateCredential(userId, newPassword, oldPassword);
    }

    public boolean authenticate(String userId, String password) throws Exception {

        return userMgtStub.authenticate(userId, password);

    }
}
