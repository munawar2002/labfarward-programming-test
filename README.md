# labfarward-programming-test

This application is created with 
    
    Java 8
    Mysql 5.7
    Sprigboot 2.4.5

### Problem Statement:
/docs/Take-Home Task for Senior Backend Engineer Position.pdf

### Database Structure
/docs/schema_sheet.xlsx

### Run the compilation first
After cloning the repo, run the compilation. 
mvn clean install -DskipTests

### Set your MYSQL Username and Passwor
open main/resources/appliication.yml

Change the mysql username and password according to your installation. 

### Run the compilation with unit tests 
mvn clean install

### Flyway Migration
Flyway is used for database migration.  Flyway updates a database from one version to a next using migrations. 
We migrate the database through SQL and files can be found at path "/main/resources/db/migration" 

### Integration Test (Karate Tests)
Karate is the only open-source tool to combine API test-automation, mocks, performance-testing and even UI automation into a single, unified framework
To run karate test, Open class /test/java/APIContractRunner.java and execute test in it. It will execute all scenario 
defined at /test/resources/features/**

###  Swagger Codegen
Swagger Codegen can simplify your build process by generating server stubs and client SDKs for any API, defined with the
 OpenAPI (formerly known as Swagger) specification, so your team can focus better on your API’s implementation and adoption.

To create a new API, define your api definition in "/main/resources/api-swagger.yml" and compile your code. 

### API Documentation
Run the springboot application by running ProgrammingtestApplication.java Or from run maven command mvnw

Make sure you have provided your mysql databse username and passeord in application.yml file.

After deploying the application, the documentation can be accessed through url

http://localhost:8080/swagger-ui.html 

