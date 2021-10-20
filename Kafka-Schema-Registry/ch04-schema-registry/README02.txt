Avro Console producer and consumer

- Avro Console producer allows quickly send data to Kafka manually by specifying the schema as an argument

- The binaries come with the Confluent Distribution of Kafka
---------------------------------------
Use console avro producer/consumer

From docker:
docker run -it --rm --net=host confluentinc/cp-schema-registry:3.3.1 bash 

kafka-avro-console-producer --broker-list 127.0.0.1:9092 --topic test-avro --property schema.registry.url=http://127.0.0.1:8081 --property value.schema='{"type":"record","name":"myrecord","fields":[{"name":"f1","type":"string"}]}'
 
 {"f1":"value1"} - enter
 {"f1":"value2"} - enter
 
 It will create a new schema and a new topic.
 And put 2 messages to this topic
 
 {"f2":"value3"} - enter
 gives error as f2 not exist
 
 {"f1":1} - enter
 gives error as f1 is string
 ---------------------------------------
 
 Consumer:
kafka-avro-console-consumer --topic test-avro --bootstrap-server localhost:9092 --property schema.registry.url=http://127.0.0.1:8081 --from-beginning
 ---------------------------------------
 Now let's break schema from producer:
 kafka-avro-console-producer --broker-list 192.168.99.100:9092 --topic
 test-avro --schema.registry.url=http://192.168.99.100:8081 
 --propert value.schema='{"type":"record","name":"myrecord","fields":[{"name":"f1","type":"int"}]}'
 
 After this changes consumer will give us error duaring startup - as it still works with old schema
 Can not not deserialize now
 
 So, you need to evaluate schema in compatible way, not just like you want.
 
 If schema is updated correctly then on UI you can see v2 for this schema
 
 
 