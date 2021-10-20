V1 vs V2 API

- Kafka has the "old consumer" and "old producer" API thet were valid until 0.8
- After 0.8, Kafka released the "new consumer" and "new producer" API (prev used Zookeeper to store offsets, now uses Kafka)

- The REST Proxy had been around for a long time, and historically it was using the "old" API,
named V1 int the REST Proxy

- The REST Proxy now has support for the "new" API, named V2 in the REST Proxy

Use the V2 API. Do not even think about it.
----------------------------------------------------
Making a request to the REST Proxy

- ContentType has to be specified in a Header (plus an Accept header)

application/vnd.kafka[.embedded_format].[api_version]+[serialization_format]
embedded_format might be = json, binary or avro
api_version = always v2
serialization_format=Always json

Example:
Content-Type: application/vnd.kafka.avro.v2+json
Accept: application/vnd.kafka.v2+json