# camel-spring-boot-course

Project for course about Apache Camel + Spring Boot

> Camel is an Open Source integration framework that empowers you to quickly and easily integrate various systems consuming or producing data.

## Apache Camel

Architecture:

![](https://laissonsilveira.github.io/camel-spring-boot-course/doc/camel-architecture.png "Apache Architecture")

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

* add dependency to project:
  ```xml
  <dependency>
    <groupId>org.apache.camel.springboot</groupId>
    <artifactId>camel-activemq-starter</artifactId>
    <version>${camel.version}</version>
  </dependency>
  ```
* run `docker run -p 61616:61616 -p 8161:8161 rmohr/activemq`
* add to file **_application.properties_**: `spring.activemq.broker-url=tcp://localhost:61616`
* interface access: `http://localhost:8161` with user/password -> `admin/admin`

## Kafka

* add dependency to project:
  ```xml
  <dependency>
    <groupId>org.apache.camel.springboot</groupId>
    <artifactId>camel-kafka-starter</artifactId>
    <version>${camel.version}</version>
  </dependency>
  ```
* use the `./docker-compose.yaml` file
* run `docker-compose up` on docker-compose directory
* add to file **_application.properties_**: `camel.component.kafka.brokers=localhost:9092`
* edit `/etc/hosts` and add unknown host created. Ex: `127.0.0.1   458077ddabfc`