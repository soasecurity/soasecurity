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


import org.soa.security.sample.authentication.webapp.client.ClientConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.Properties;

/**
 * servlet class init the configuration from web.xml file
 */
public class WSO2AgentServlet extends HttpServlet {


	private static final long serialVersionUID = -6161453324989741214L;

	public void init(ServletConfig config) throws ServletException {
        
        Properties properties = new Properties();

        String backEndServerURL = config.getInitParameter(WebAppConstants.ClientConstants.SERVER_URL);
        if(backEndServerURL != null){
            backEndServerURL = backEndServerURL.trim();
            if (!backEndServerURL.endsWith("/")) {
                backEndServerURL += "/";
            }
        }
        
        properties.setProperty(WebAppConstants.ClientConstants.SERVER_URL, backEndServerURL);
        properties.setProperty(WebAppConstants.ClientConstants.SERVER_USER,
                    config.getInitParameter(WebAppConstants.ClientConstants.SERVER_USER));
        properties.setProperty(WebAppConstants.ClientConstants.SERVER_PASSWORD,
                    config.getInitParameter(WebAppConstants.ClientConstants.SERVER_PASSWORD));
        ClientConfig.getInstance(properties);
        
		super.init(config);
	}


}
