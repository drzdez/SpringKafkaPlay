# SpringKafkaProducer
Spring, Kafka producer, Actuator, Prometheus, Swagger essentials

bin/kafka-console-consumer.sh --topic topic111 --from-beginning --bootstrap-server localhost:9093

build with all dependencies
https://www.baeldung.com/executable-jar-with-maven

docker buils
- copy jar from targer to platform/target
docker build --build-arg JAR_FILE=target/*.jar -t zdrazil/kafkaproducer .

kafkastreamz
docker network connect dockaf_default kafkastreamz
