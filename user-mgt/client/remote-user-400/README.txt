This contains a sample client to demo the remote user management service that expose the user store manager as a web service. Basically you can integrate this web service for authentication and do user and role management functions with you application.  This client source can be used as bridge for your application and the web service

How to Run Sample
-----------------

1. Build project using maven3
2. Run the sample

Or else

1. Start WSO2 Carbon Server
2. Run ant command in <CARBON_HOME>/bin/ . This will copy required jars into <CARBON_HOME>/repository/lib directory
3. Modify the parameters which are defined in "SampleUserRoleMgtClient" class according your configuration. (More details would be available in code itself)
4. Add jars in following directory into project's class path
        <CARBON_HOME>/repository/lib
5. Run "SampleUserRoleMgtClient" client


Note: you can see the WSDL of RemoteUserManagementService Service

1. UserAdmin is admin service according the WSO2 Carbon platform. Therefore WSDL of admin service can not be seen by default.
2. Open carbon.xml file which can be found at <IS_HOME>/repository/conf directory
3. Locate following XML element and set it to “false”

 <HideAdminServiceWSDLs>true</HideAdminServiceWSDLs>

4. Then restart WSO2 Identity Server running wso2server script which can be found at <IS_HOME>/bin directory.
5. Point to browser https://localhost:9443/services/RemoteUserStoreManagerService?wsdl If you have started server in default configurations.
