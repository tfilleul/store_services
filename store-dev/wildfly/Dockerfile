FROM jboss/wildfly

MAINTAINER tfilleul@gmail.com

ENV postgres_module_dir /opt/jboss/wildfly/modules/org/postgresql/main
ENV keycloak_module_dir /opt/jboss/wildfly/modules/system/add-ons/keycloak/org/keycloak
ENV data_dir /tmp/data/ 


USER root
# add rights to data_dir
RUN mkdir -p ${data_dir}
RUN chown -R jboss:jboss ${data_dir}

#Add modules
RUN mkdir -p ${postgres_module_dir}
RUN mkdir -p ${keycloak_module_dir}

ADD ./module-db ${postgres_module_dir}
ADD ./keycloak ${keycloak_module_dir}  

RUN chown -R jboss:jboss ${postgres_module_dir} 
RUN chown -R jboss:jboss ${keycloak_module_dir}
RUN chmod 755 ${postgres_module_dir}
RUN chmod 755 ${keycloak_module_dir}   

# Add standalone
COPY ./standalone.xml ${JBOSS_HOME}/standalone/configuration/standalone.xml

RUN chown jboss:jboss ${JBOSS_HOME}/standalone/configuration/standalone.xml
RUN chmod 755 ${JBOSS_HOME}/standalone/configuration/standalone.xml


# Add war store
ADD ./store.war ${JBOSS_HOME}/standalone/deployments/
ADD ./webstore.war ${JBOSS_HOME}/standalone/deployments/

RUN chown -R jboss:jboss ${JBOSS_HOME}/standalone/deployments/*
RUN chmod 755 ${JBOSS_HOME}/standalone/deployments/*

# add user
RUN /opt/jboss/wildfly/bin/add-user.sh admin Newton2016! --silent

USER jboss
   
CMD ["/opt/jboss/wildfly/bin/standalone.sh","-c","standalone.xml","-b","0.0.0.0","-bmanagement","0.0.0.0"]
