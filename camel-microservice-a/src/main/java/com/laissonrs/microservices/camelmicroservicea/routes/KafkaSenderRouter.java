package com.laissonrs.microservices.camelmicroservicea.routes;

import org.apache.camel.builder.RouteBuilder;

// @Component
public class KafkaSenderRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("file:files/kafka")
                .routeId("Kafka-Sender-Route")
                .log("${body}");
                // .to("kafka:my-kafka-topic");

    }

}