Sample Web application that contain user login function. This Web application uses WSO2 Identity Server web service API for authenticating users. 


How To
======

1. Start WSO2 Identity Server

2. Login to management console and create some users

3. Locate web.xml file in the project and configure following according to your WSO2 Identity Server settings 

serverUrl
serverUserName  -  Admin user that is used authenticated session between Web app and Identity Server
serverPassword

4. Build project using maven 3

5. Deploy .war file in Tomcat
