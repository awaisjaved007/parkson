FROM jboss/wildfly:18.0.0.Final

WORKDIR /app

ARG artifactid

ARG version

ENV artifact ${artifactid}-${version}.war

COPY ./target/${artifact} /opt/jboss/wildfly/standalone/deployments/ROOT.war

EXPOSE 8080


