# camel-spring-boot-course

Project for course about Apache Camel + Spring Boot

## Active MQ 

To use the **Active MQ** 

* run `docker run -p 61616:61616 -p 8161:8161 rmohr/activemq`
* add to file **_application.properties_**: `spring.activemq.broker-url=tcp://localhost:61616`