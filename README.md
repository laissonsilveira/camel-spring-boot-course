# camel-spring-boot-course

Project for course about Apache Camel + Spring Boot.

OBS: In case that don't work, check routes with `.noAutoStartup()` pipeline or with `@Component` commented 

> Camel is an Open Source integration framework that empowers you to quickly and easily integrate various systems consuming or producing data.

| Topic Reference | Reference |
|---|---|
| Why Camel? | https://camel.apache.org/manual/latest/faq/why-the-name-camel.html |
| Camel Examples | https://github.com/apache/camel-examples/tree/master/examples |
| Camel Spring Boot Configuration | https://camel.apache.org/camel-spring-boot/latest/spring-boot.html |
| Complete Spring Boot Starters List | https://camel.apache.org/camel-spring-boot/latest/list.html |
| Camel Spring Boot Examples | https://github.com/apache/camel-spring-boot-examples |
| Enterprise Integration Patterns | https://camel.apache.org/components/latest/eips/enterprise-integrationpatterns.html |

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

## Integration Examples

Some examples

### Active MQ 

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

### Kafka

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

### Rest API
* 
* add dependency to project:
  ```xml
  <dependency>
    <groupId>org.apache.camel.springboot</groupId>
    <artifactId>camel-http-starter</artifactId>
    <version>${camel.version}</version>
  </dependency>
  ```
* create class with `@RestController` in service B and add config `restConfiguration().host("localhost").port(8000);`
* create class `RestApiRoute.java` in service A with `to("rest:METHOD:/PATH")`

# Examples Routes / Controllers / Processors / Beans / Strategies

**Microservice A**

* [ActiveMqSenderRouter](https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-a/src/main/java/com/laissonrs/microservices/camelmicroservicea/routes/ActiveMqSenderRouter.java)
* [AggregateRouter](https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-a/src/main/java/com/laissonrs/microservices/camelmicroservicea/routes/AggregateRouter.java)
* [DynamicRoutingRouter](https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-a/src/main/java/com/laissonrs/microservices/camelmicroservicea/routes/DynamicRoutingRouter.java)
* [FileRouter](https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-a/src/main/java/com/laissonrs/microservices/camelmicroservicea/routes/FileRouter.java)
* [KafkaSenderRouter](https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-a/src/main/java/com/laissonrs/microservices/camelmicroservicea/routes/KafkaSenderRouter.java)
* [MulticastRouter](https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-a/src/main/java/com/laissonrs/microservices/camelmicroservicea/routes/MulticastRouter.java)
* [RestApiRouter](https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-a/src/main/java/com/laissonrs/microservices/camelmicroservicea/routes/RestApiRouter.java)
* [RoutingSlipRouter](https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-a/src/main/java/com/laissonrs/microservices/camelmicroservicea/routes/RoutingSlipRouter.java)
* [SplitRouter](https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-a/src/main/java/com/laissonrs/microservices/camelmicroservicea/routes/SplitRouter.java)
* [TimerRouter](https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-a/src/main/java/com/laissonrs/microservices/camelmicroservicea/routes/TimerRouter.java)

**Microservice B**

* [ActiveMqReceiverRouter](https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-b/src/main/java/com/laissonrs/microservices/camelmicroserviceb/routes/ActiveMqReceiverRouter.java)
* [KafkaReceiverRouter](https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-b/src/main/java/com/laissonrs/microservices/camelmicroserviceb/routes/KafkaReceiverRouter.java)

# Creating Key Store

Command to create:

```shell
keytool -genseckey -alias myDesKey -keypass someKeyPassword -keystore myDesKey.jceks -storepass someKeystorePassword -v -storetype JCEKS -keyalg DES
```

OBS: put `myDesKey.jceks` in your `src/main/resources` directory

Crypto data class: [MyCryptoData](`https://laissonsilveira.github.io/camel-spring-boot-course/camel-microservice-lib/src/main/java/com/laissonrs/microservices/utils/MyCryptoData.java`)