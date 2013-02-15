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
    String errorMessage = request.getParameter("error");
%>
<html>

<head>
    <title>WSO2 Authentication Demo Application</title>
    <script type="text/javascript" src="js/jquery/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.validate.js"></script>
</head>
<body>
<div>
    <div>
        <tr>
            <td>
                <h1>WSO2 Authentication Demo Application</h1>
            </td>
        </tr>
    </div>

    <form action="login_validate.jsp" method="post" id="loginForm">
        <table>
            <tr>
                <td>
                    <h2>Please login here</h2>
                </td>
            </tr>
            <%if (errorMessage != null) {%>
            <tr>
                <td>
                    <label class="error"><%=errorMessage%></label>
                </td>
            </tr>
            <%}%>
            <tr>
                <td>User Name : </td>
                <td ><input id="userName" type="text" name="userName"
                                            class="required"/></td>
            </tr>
            <tr>
                <td>Password : </td>
                <td><input type="password" name="password" class="required">
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Login" class="button">
            </tr>
        </table>
    </form>
</div>
</body>
</html>