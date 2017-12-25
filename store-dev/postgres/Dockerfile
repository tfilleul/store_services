FROM library/postgres

MAINTAINER tfilleul@gmail.com

ENV psl_data_dir /tmp/psql_data/ 

USER root
RUN pg_createcluster 9.6 main --start

# load SQL Dump
RUN mkdir -p ${psl_data_dir}
COPY store14032017.sql ${psl_data_dir}

#COPY load_db.sh /docker-entrypoint-initdb.d/

RUN chown -R postgres:postgres ${psl_data_dir}
#RUN chown -R postgres:postgres /docker-entrypoint-initdb.d/load_db.sh


#USER postgres 
#RUN /etc/init.d/postgresql start && psql --command "ALTER USER postgres WITH PASSWORD 'titi';" && createdb -O postgres store && psql store < /tmp/psql_data/store14032017.sql
#RUN psql store < /tmp/psql_data/store14032017.sql
   
#CMD ["/opt/jboss/wildfly/bin/standalone.sh","-c","standalone.xml","-b","0.0.0.0"]
