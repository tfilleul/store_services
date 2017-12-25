FROM jboss/base-jdk:8

MAINTAINER tfilleul@gmail.com

ENV KEYCLOAK_VERSION 2.5.0.Final
# Enables signals getting passed from startup script to JVM
# ensuring clean shutdown when container is stopped.
ENV LAUNCH_JBOSS_IN_BACKGROUND 1
ENV PROXY_ADDRESS_FORWARDING false
ENV KEYCLOAK_HOME /opt/jboss/keycloak

USER root

#RUN yum install -y epel-release && yum install -y jq && yum clean all

RUN mkdir -p /tmp/data/
COPY store.json /tmp/data/

RUN chown -R jboss:jboss /tmp/data/


USER jboss

RUN cd /opt/jboss/ && curl -L https://downloads.jboss.org/keycloak/$KEYCLOAK_VERSION/keycloak-$KEYCLOAK_VERSION.tar.gz | tar zx && mv /opt/jboss/keycloak-$KEYCLOAK_VERSION /opt/jboss/keycloak

RUN ${KEYCLOAK_HOME}/bin/add-user-keycloak.sh -u admin -p Newton2016!

ENTRYPOINT ${KEYCLOAK_HOME}/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0 -Djboss.socket.binding.port-offset=10 -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.file=/tmp/data/store.json -Dkeycloak.migration.strategy=OVERWRITE_EXISTING

#CMD ["-b", "0.0.0.0"]