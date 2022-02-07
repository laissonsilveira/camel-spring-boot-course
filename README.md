# camel-spring-boot-course

Project for course about Apache Camel + Spring Boot

> Camel is an Open Source integration framework that empowers you to quickly and easily integrate various systems consuming or producing data.

## Apache Camel

Architecture:

![](https://github.com/laissonsilveira/camel-spring-boot-course/blob/main/doc/camel-architecture.png "Apache Architecture")

* Camel Context
  * Endpoint: Reference to a queue, database or file
  * Route: Endpoints + Processor(s) + Transformer(s)
  * Components: Extensions (Kafka, JSON, JMS, etc)
  * Transformation
    * Data format transformation: XML to JSON
    * Data type transformation: String to CurrencyConversionBean
* Message: Body + Headers + Attachments
* Exchange: Request + Response
  * Exchange ID
  * Message Exchange Pattern (MEP): InOnly/InOut
  * Input Message and (optional) Output Message

## Active MQ 

To use the **Active MQ** 

* run `docker run -p 61616:61616 -p 8161:8161 rmohr/activemq`
* add to file **_application.properties_**: `spring.activemq.broker-url=tcp://localhost:61616`
* interface access: `http://localhost:8161` with user/password -> `admin/admin`
