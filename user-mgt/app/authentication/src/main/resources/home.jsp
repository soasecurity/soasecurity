<!--
 ~ Copyright (c) WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 ~
 ~ WSO2 Inc. licenses this file to you under the Apache License,
 ~ Version 2.0 (the "License"); you may not use this file except
 ~ in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~    http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing,
 ~ software distributed under the License is distributed on an
 ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 ~ KIND, either express or implied.  See the License for the
 ~ specific language governing permissions and limitations
 ~ under the License.
 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userName = (String) session.getAttribute("userName");
    if (userName == null) {
        response.sendRedirect("index.jsp?error=User not logged in. Please login again !!");
    }
%>


<script type="text/javascript">

    function changePassword(){
        location.href =  'change-password.jsp';    
    }

</script>

<html>
<head>
    <title>WSO2 Authentication Demo Application</title>
    <script type="text/javascript" src="js/jquery/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.validate.js"></script>
</head>
<body>
<div>
    <div><span>Logged in as <%=userName%> - </span> <a
            href="logout.jsp">Logout</a></div>
    <div>
        <tr>
            <td>
                <h1>WSO2 Authentication Demo Application</h1>
            </td>
        </tr>
    </div>

    <div>
        You have successfully login.
    </div>

    <div>
        <tr>
            <td>Change your password from here </td>
            <td><input onclick="changePassword();"  type="button" value="Go" class="button"/></td>
        </tr>
    </div>
</div>
</body>
</html>