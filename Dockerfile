# This official image uses OpenJDK 11 and Wildfly 18
FROM jboss/wildfly:18.0.0.Final

# Copy the war file to the deployments folder
COPY ./target/assignment-0.0.1-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/jboss-wildfly-demo.war
