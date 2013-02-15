/*
 *  Copyright (c) WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.soa.security.sample.authentication.webapp;

import org.apache.axis2.AxisFault;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.soa.security.sample.authentication.webapp.client.UserManagementClient;
import org.soa.security.sample.authentication.webapp.client.ClientConfig;

import javax.servlet.http.HttpServletRequest;


/**
 * WSO2 IS Agent instance
 */
public class WSO2AuthenticationAgent {

    private UserManagementClient userManagementClient;

    private static WSO2AuthenticationAgent agent;

    private final static Object lock = new Object();

    private static Log log = LogFactory.getLog(WSO2AuthenticationAgent.class);
    
    private WSO2AuthenticationAgent() {

        ClientConfig clientConfig = ClientConfig.getInstance();
        userManagementClient = new UserManagementClient(clientConfig.getProperties());
    }

    public static WSO2AuthenticationAgent getInstance() {

        if (agent == null) {
            synchronized (lock){
                if(agent == null){
                    agent = new WSO2AuthenticationAgent();                    
                }
            }
        }
        
        return agent;
    }

    public boolean authenticateUser(String userName, String password) {

        try {
            return userManagementClient.authenticate(userName, password);
        } catch (Exception e) {
            // returning if only authentication failure
            if(e instanceof AxisFault){
                try {
                    if (WebAppConstants.ClientConstants.SESSION_TIME_OUT.
                                        equals(((AxisFault)e).getFaultCode().getLocalPart())) {
                        userManagementClient = new UserManagementClient(ClientConfig.
                                                                    getInstance().getProperties());
                        return userManagementClient.authenticate(userName, password);
                    } else {
                        log.error(e);
                    }
                } catch (Exception e1) {
                    log.error(e1);
                }
            } else {
                log.error(e);
            }
        }
        return false;
    }



    public boolean changePassword(HttpServletRequest request) {

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String userName = (String) request.getSession().getAttribute("userName");

        if(oldPassword != null && oldPassword.trim().length() > 0 &&
                newPassword != null && newPassword.trim().length() > 0){
            try {
                userManagementClient.updateCredentials(userName, oldPassword, newPassword);
                return true;
            } catch (Exception e) {
                // returning if only authentication failure
                if(e instanceof AxisFault){
                    try {
                        if (WebAppConstants.ClientConstants.SESSION_TIME_OUT.
                                            equals(((AxisFault)e).getFaultCode().getLocalPart())) {
                            userManagementClient = new UserManagementClient(ClientConfig.
                                                                        getInstance().getProperties());
                            userManagementClient.updateCredentials(userName, oldPassword, newPassword);
                            return true;
                        } else {
                            log.error(e);
                        }
                    } catch (Exception e1) {
                        log.error(e1);
                    }
                } else {
                    log.error(e);
                }
            }
        }

        return false;
    }
}
