Confluent Schema Registry Purpose:

- store and retrieve schema for Producers / Consumers
- enforce backward /forward/ full compatibility on topics
- decrease the size of the payload of data sent to Kafka

Confluent Schema Registry Operations:
 - add schenas
 - retrieve schema
 - update schema
 - delete schema (as of 3.3.0)
 - All as a rest api
 
 ------------------------------------------------------
 To create schema got to schemas and choose
 Global Compatibility level: backward, forward, full, none
 use full
 
 Click NEW
 
 com.example.
 name: CustomerTest
 first_name string
 age int
 height float
 
 in v2 jsut add 
 sting last_name
 default: Unknown
 