# parkson
This is the assignment for Parkson

# Instruction to run
Please modify properties file with postgres DB url if you are try to run this on your own env.
Other wise if you have install docker then please follow to below commands to run this successfully.

## Default Users

I have written an init service that creates a user with following credientials

username: javedm
password: password

##URL
Login: http://localhost:8080/login

##Version of wildfly
I have used image jboss/wildfly:18.0.0.Final for testing purpose

##Goal
Technical Assignment

##DB  Postgres

##Front End

As this is monolithic web application, I have written it's User interface in JSP.

After cloning the code, move into directory and run the following commands there.

##How to Run ?
mvn package -DskipTests
docker-compose build
docker-compose up -d
