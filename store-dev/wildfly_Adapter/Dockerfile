FROM jboss/wildfly:10.1.0.Final

MAINTAINER tfilleul@gmail.com

ENV KEYCLOAK_VERSION 2.5.0.CR1
ENV postgres_module_dir /opt/jboss/wildfly/modules/org/postgresql/main
ENV data_dir /tmp/data/
ENV JDBC_POSTGRES_URI="jdbc:postgresql:\/\/mypostgres:5432\/store"

WORKDIR /opt/jboss/wildfly

USER root

RUN curl -L https://downloads.jboss.org/keycloak/${KEYCLOAK_VERSION}/adapters/keycloak-oidc/keycloak-wildfly-adapter-dist-${KEYCLOAK_VERSION}.tar.gz | tar zx

USER jboss

WORKDIR /opt/jboss

# Standalone.xml modifications.
RUN sed -i -e 's/<extensions>/&\n        <extension module="org.keycloak.keycloak-adapter-subsystem"\/>/' $JBOSS_HOME/standalone/configuration/standalone.xml && \
    sed -i -e 's/<profile>/&\n   <subsystem xmlns="urn:jboss:domain:keycloak:1.1">\n <secure-deployment name="store.war">\n <realm>store<\/realm>\n <resource>store-client-back<\/resource>\n <bearer-only>true<\/bearer-only>\n <auth-server-url>http:\/\/mykeycloak:8090\/auth<\/auth-server-url>\n <ssl-required>NONE<\/ssl-required>\n <enable-cors>true<\/enable-cors>\n <\/secure-deployment>\n <\/subsystem>/' $JBOSS_HOME/standalone/configuration/standalone.xml && \
    sed -i -e 's/<datasources>/&\n <datasource jta="true" jndi-name="java:jboss\/datasources\/jbpmDS" pool-name="java:jboss\/datasources\/jbpmDS_Pool" enabled="true" use-java-context="true" use-ccm="true">\n <connection-url>'$JDBC_POSTGRES_URI'<\/connection-url><driver-class>org.postgresql.Driver<\/driver-class><driver>postgresql-jdbc4<\/driver><pool><min-pool-size>2<\/min-pool-size><max-pool-size>20<\/max-pool-size><prefill>true<\/prefill><\/pool><security><user-name>postgres<\/user-name><password>titi<\/password><\/security><validation><check-valid-connection-sql>SELECT 1<\/check-valid-connection-sql><validate-on-match>false<\/validate-on-match><background-validation>false<\/background-validation><use-fast-fail>false<\/use-fast-fail><\/validation><\/datasource>/'  $JBOSS_HOME/standalone/configuration/standalone.xml && \
    sed -i -e 's/<drivers>/&\n               <driver name="postgresql-jdbc4" module="org.postgresql"\/>   /' $JBOSS_HOME/standalone/configuration/standalone.xml && \
    sed -i -e 's/<filters>/&\n   <response-header name="access-control-allow-origin" header-name="Access-Control-Allow-Origin" header-value="http:\/\/mykeycloak:8090"\/>/' $JBOSS_HOME/standalone/configuration/standalone.xml && \    
    sed -i -e 's/<filters>/&\n     <response-header name="access-control-allow-headers" header-name="Access-Control-Allow-Headers" header-value="Origin, x-requested-with, Content-Type, x-auth-token, X-Auth-Token, Authorization"\/>/' $JBOSS_HOME/standalone/configuration/standalone.xml && \
    sed -i -e 's/<security-domains>/&\n                <security-domain name="keycloak">\n                    <authentication>\n                        <login-module code="org.keycloak.adapters.jboss.KeycloakLoginModule" flag="required"\/>\n                    <\/authentication>\n                <\/security-domain>/' $JBOSS_HOME/standalone/configuration/standalone.xml


USER root
# add rights to data_dir
RUN mkdir -p ${data_dir}
RUN chown -R jboss:jboss ${data_dir}

#Add modules
RUN mkdir -p ${postgres_module_dir}

ADD ./module-db ${postgres_module_dir}

RUN chown -R jboss:jboss ${postgres_module_dir} 
RUN chmod 755 ${postgres_module_dir}

# Add war store
ADD ./store.war ${JBOSS_HOME}/standalone/deployments/
ADD ./webstore.war ${JBOSS_HOME}/standalone/deployments/

RUN chown -R jboss:jboss ${JBOSS_HOME}/standalone/deployments/*
RUN chmod 755 ${JBOSS_HOME}/standalone/deployments/*

# add user
RUN /opt/jboss/wildfly/bin/add-user.sh admin Newton2016! --silent

USER jboss
   
CMD ["/opt/jboss/wildfly/bin/standalone.sh","-c","standalone.xml","-b","0.0.0.0","-bmanagement","0.0.0.0"]
    