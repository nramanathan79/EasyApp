CATALINA_OPTS="-Ddb.pool.max.active=$DB_POOL_MAX_ACTIVE -Ddb.pool.min.idle=$DB_POOL_MIN_IDLE -Ddb.pool.max.wait=$DB_POOL_MAX_WAIT -Ddb.pool.initial.size=$DB_POOL_INITIAL_SIZE -Ddb.port=$DB_PORT -Ddb.username=$DB_USERNAME -Ddb.password=$DB_PASSWORD -Ddb.hostname=$DB_HOSTNAME -Ddb.database=$DB_DATABASE -Dkafka.zookeeper.connect=$KAFKA_ZOOKEEPER_CONNECT -Dkafka.session.timeout.ms=KAFKA_SESSION_TIMEOUT_MILLIS -Dkafka.consumer.client.id=$KAFKA_CONSUMER_CLIENT_ID -Dkafka.producer.client.id=$KAFKA_PRODUCER_CLIENT_ID -Dkafka.rpc.client.id=$KAFKA_RPC_CLIENT_ID -Dkafka.rpc.response.port=$KAFKA_RPC_RESPONSE_PORT -Dkafka.rpc.response.timeout.ms=$KAFKA_RPC_RESPONSE_TIMEOUT_MILLIS"
