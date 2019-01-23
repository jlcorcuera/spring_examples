# spring_examples

## OpenJDK 11:

https://download.java.net/java/GA/jdk11/9/GPL/openjdk-11.0.2_windows-x64_bin.zip

## Apache Tomcat (apache-tomcat-8.5.37)

https://www-eu.apache.org/dist/tomcat/tomcat-8/v8.5.37/bin/apache-tomcat-8.5.37.zip

### To mount directory as context:
- Create a xml file (sapui.xml) with the following content:
```
<?xml version="1.0" encoding="UTF-8"?>

<Context docBase="C:\Repository\sapui" path="/sapui"/>
```
- Place the created xml into ${TOMCAT_HOME}\conf\Catalina\localhost

Where "C:\Repository\sapui" folder contains the content of https://tools.hana.ondemand.com/additional/sapui5-rt-1.60.2.zip

### To define a JNDI resource on Tomcat

- Open the file ${TOMCAT_HOME}\conf\server.xml
- Add the following JNDI resource into the element "GlobalNamingResources"

        <Resource name="jdbc/testDS"
                  auth="Container"
                  type="javax.sql.DataSource"
                  username="testuser"
                  password="p4ssw0rd"
                  driverClassName="com.mysql.cj.jdbc.Driver"
                  url="jdbc:mysql://localhost:3306/test"
                  testWhileIdle="true"
                  testOnBorrow="true"
                  testOnReturn="false"
                  validationQuery="SELECT 1"
                  timeBetweenEvictionRunsMillis="5000"
                  maxTotal="100"
                  minIdle="10"
            />    

- Since in the previous JNDI resource was related to MySQL, we must place the JDBC driver of MySQL into ${TOMCAT_HOME}\lib directory (In this you must put mysql-connector-java-8.0.13.jar inside it)

## Netbeans 10:

https://www.apache.org/dyn/closer.cgi/incubator/netbeans/incubating-netbeans/incubating-10.0/incubating-netbeans-10.0-bin.zip

Once you uncompress the zip file execute the netbeans64.exe (path: C:\Software\netbeans\bin)

### To Enable required plugins

* From Tools / Plugins:
* Settings Tab -> Check on Netbeans 8.2 Plugin Portal
* Available Plugins -> Install "Java EE Base" plugin, Accept and restart the IDE
* Installed Tab -> Activate "HTML5"

### To Add OpenJDK in Netbeans: (REMEMBER, DO THIS STEP ONLY WHEN IT IS NEEDED)

* From Tools / Java Platforms
* Clic on "Add Platform"
* On the popup select "Java Standard Edition" and select Next.
* Select the path of your OpenJDK folder (in my case C:\Software\openjdk-11.0.2_windows-x64_bin)
* Clic on Finish

### To Add Tomcat 8.5.37 in Netbeans:

* From Windows / Services:
* On Servers, clic right button and choose "Add Server"
* Select Apache Tomcat or TomEE and enter "tc-8.3.57" as Name. Clic on Next.
* Enter the server location (in my case "C:\Servers\apache-tomcat-8.5.37") and enter "admin" in username and password fields and leave "Create user..." checked.
* Clic Finish.

### To run Tomcat 8.5.37 using OpenJDK 11: (REMEMBER, DO THIS STEP ONLY WHEN IT IS NEEDED)

* From Windows / Services:
* On Servers, colapse it and clic right button on "tc-8.3.57" and choose properties.
* On Platform tab, select your OpenJDK on the Java Platform dropdown.
* Clic on Close.

### To setup your project to use OpenJDK 11: (REMEMBER, DO THIS STEP ONLY WHEN IT IS NEEDED)
* From Windows / Projects:
* Clic right button on your project and choose properties.
* On Compile category, select your OpenJDK on the Java Platform dropdown. Also uncheck "Compile on Save" option.
* On Sources category, make sure that 1.8 value is selected on "Source/Binary Format" dropdown.
* On Run category, select the "tc-8.3.57" in the server dropdown and choose a browser on the related dropbown.
* Clic on OK.
