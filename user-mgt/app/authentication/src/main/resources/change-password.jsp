<!--
 ~ Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
<%
    String errorMessage = request.getParameter("error");
    String userName = (String) session.getAttribute("userName");
    if (userName == null) {
        response.sendRedirect("index.jsp?error=User not logged in. Please login again !!");
    }
%>


<script type="text/javascript">

    function doCancel(){
        location.href = 'home.jsp';
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
        <h2>Change Password</h2>
        <form action="change-password-finish.jsp" method="post" id="changePassword">
        <table>
            <%if (errorMessage != null) {%>
            <tr>
                <td>
                    <label class="error"><%=errorMessage%></label>
                </td>
            </tr>
            <%}%>            

            <tr>
                <td>
                    <table>
                        <tr>
                            <td>Enter your Old Password : </td>
                            <td><input type="password" name="oldPassword" id="oldPassword" /></td>
                        </tr>
                        <tr>
                            <td>Enter your New Password : </td>
                            <td><input type="password" name="newPassword" id="newPassword"/></td>
                        </tr>
                    </table>
                </td>
            </tr>

            <tr>
                <td>
                    <input type="submit" class="button" value="Update" />
                    <input type="button" onclick="doCancel();" class="button" value="Cancel" />
                </td>
            </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
