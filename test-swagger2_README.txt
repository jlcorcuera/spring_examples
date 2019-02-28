project: test-web

setup instructions

1. On your Tomcat directory, create a folder called "properties".
2. Inside the created directory ("properties") create "test-swagger2" folder.
3. Put there app.properties file (this file is located in: ${REPO_HOME}\properties\test-swagger2 folder)
4. Open the file ${TOMCAT_HOME}\conf\catalina.properties and modify the properties "shared.loader" as it is indicated below:

shared.loader=${catalina.home}/properties

The line above will add to the shared classloader the "properties" directory. In this way, our application will locate our properties file (line 49 of spring-context.xml file) in ${TOMCAT_HOME}\properties\test-web\app.properties.

Please read:

https://dzone.com/articles/using-more-one-property-file
