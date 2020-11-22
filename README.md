# anz-engineering-project
ANZ Wholesale Engineering Sample Project for backend developer

## Detail Description

This is a backend application with REST endpoints to provide 
information about customer accounts and their transaction details.

## Supported endpoints

 **http://localhost:8081/api/account/getaccounts?customerId=2000**
 **http://localhost:8081/api/transaction/gettransactions?accountNumber=6000**


## Data
For this application to test, an in-memory database HSQLDB is used. Database schema and records are created with data.sql 
in the resources folder. Records for customerId 2000 and accountId 6000 are created. More records can be added by editing resources/data.sql

## How to run
* Please install Lombok plugin in your IDE (IDEA/Eclipse)
    * STS: Open the Settings panel. Search for "Plugins", then search for "Lombok" in the plugins. Find the plugin and install it. Restart your STS.
      Installation reference: http://codeomitted.com/setup-lombok-with-stseclipse-based-ide/
    * Eclipse: run lombok/lombok.jar provided in the source.
* Import project in IDE and run **mvn clean install**
* From IDE directly run Application.java
* Running as a Jar
    * run **mvn clean package**. This will generate anz-engineering-project-0.0.1.jar in target folder.
    * Open Command/Terminal. 
    * Go to the folder containing Jar. 
    * Execute on terminal **java -jar anz-engineering-project-0.0.1.jar**
		
		
