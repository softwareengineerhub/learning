Confluent REST Proxy
- integrated with the schema resgistry so that consumers and producers can easily read and write to Avro topics

- there is a performance hit to using HTTP instead of Kafka's native protocol and it's been estimated that the 
throughput decrease is 3-4x

- it is up to producing application to batch events
- the Confluent REST Proxy is already installed on our Docker Kafka Cluster