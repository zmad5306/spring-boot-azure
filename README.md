# Spring Boot Azure

Simple applicaiton that reads a file from Azure File Storage, loads it into a SQL Server Database at start up and provide a web API to expose the data.

It was build following this guide: https://spring.io/guides/gs/spring-boot-for-azure/

## Example settings.xml

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
        <server>
            <id>azure-auth</id>
            <configuration>
                <client><app id here></client>
                <tenant><tenant here></tenant>
                <key><password here></key>
                <environment>AZURE</environment>
            </configuration>
        </server>
    </servers>
</settings>
```

## App Configuration

### src/main/resources/application.properties

```application.properties
spring.datasource.url=jdbc:sqlserver://<db url>:<db port>;database=<database name>;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
spring.datasource.username=<db user name>
spring.datasource.password=<db password>
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.hibernate.ddl-auto=create-drop
azure.storage.connection-string=DefaultEndpointsProtocol=https;AccountName=<storage account name>;AccountKey=<access key>
azure.storage.share-name=<share name>
```

## Commands

### Generate Maven Access Key

`az ad sp create-for-rbac --name "uuuuuuuu" --password "pppppppp"`

https://spring.io/guides/gs/spring-boot-for-azure/

### Generate Storage Access Key

`az storage account keys list -g <resource group> -n <storage account>`

`az storage account keys list -g sb-demo -n sbaz`

https://docs.microsoft.com/en-us/cli/azure/storage/account/keys?view=azure-cli-latest

### Build with Maven

`mvnw clean package`

### Deploy to Azure

You must generate a settings.xml using access keys retrieved from Azure CLI. See Guilde for details. This settings.xml must be provided to azure deploy script below.

`mvnw azure-webapp:deploy -s .mvn/wrapper/settings.xml`