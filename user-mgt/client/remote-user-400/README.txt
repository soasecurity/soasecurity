This project contains a sample client to demo the user management service that is exposed by carbon.
There are two web services that can be used  for these function. One is UserAdmin and other one is
RemoteUserStoreManagerService (which is only available with WSO2 IS). UserAdmin web service API
has been written thinking of Carbon user management UI. Therefore the best API is the RemoteUserStoreManagerService
that can be used for external application, such as web applications.
You can use this web service for authentication and do user and role management functions.
This project contains a web service client source can be used to call this web service.
Basically  you can use this code as bridge for your own application and the web service.

How to Run Sample
-----------------

1. Build project using maven3
2. Start WSO2 Carbon Server
3. Modify the parameters which are defined in "SampleUserRoleMgtClient" class according your configuration. (More details would be available in code itself)
4. Run the sample

Or else you can run this without building using maven

1. Run ant command in <CARBON_HOME>/bin/ . This will copy required jars into <CARBON_HOME>/repository/lib directory
2. Add jars in following directory into project's class path
        <CARBON_HOME>/repository/lib


Note: You can see the WSDL of RemoteUserStoreManagerService by going through following steps

1. RemoteUserStoreManagerService is admin service according the WSO2 Carbon platform.
     Therefore WSDL of admin service can not be seen by default.
2. Open carbon.xml file which can be found at <IS_HOME>/repository/conf directory
3. Locate following XML element and set it to “false”

 <HideAdminServiceWSDLs>true</HideAdminServiceWSDLs>

4. Then restart WSO2 Identity Server running wso2server script which can be found at <IS_HOME>/bin directory.
5. Point to browser https://localhost:9443/services/RemoteUserStoreManagerService?wsdl
     If you have started server in default configurations.
