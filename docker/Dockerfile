FROM tomcat:8.5

# Defaulting common environment variables
ENV DB_POOL_MAX_ACTIVE 10
ENV DB_POOL_MIN_IDLE 3
ENV DB_POOL_MAX_WAIT 10000
ENV DB_POOL_INITIAL_SIZE 3
ENV DB_PORT 5432
ENV KAFKA_SESSION_TIMEOUT_MILLIS 30000
ENV KAFKA_CONSUMER_CLIENT_ID consumer
ENV KAFKA_CONSUMER_POLLING_INTERVAL_MILLIS 100
ENV KAFKA_PRODUCER_CLIENT_ID producer
ENV KAFKA_RPC_CLIENT_ID rpc
ENV KAFKA_RPC_RESPONSE_PORT 11111
ENV KAFKA_RPC_RESPONSE_TIMEOUT_MILLIS 10000


# Set runtime environment variables
ENV CATALINA_HOME /usr/local/tomcat
COPY setenv.sh ${CATALINA_HOME}/bin

# Set work directory
WORKDIR ${CATALINA_HOME}

# Copy configurations
COPY tomcat-users.xml ${CATALINA_HOME}/conf
RUN mkdir -p ${CATALINA_HOME}/conf/Catalina/localhost
COPY easyapp.xml ${CATALINA_HOME}/conf/Catalina/localhost

# Copy deployment war file
RUN mkdir -p ${CATALINA_HOME}/apps
COPY easyapp*.war ${CATALINA_HOME}/apps
