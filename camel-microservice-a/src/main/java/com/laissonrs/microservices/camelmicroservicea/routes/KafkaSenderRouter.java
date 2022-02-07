package com.laissonrs.microservices.camelmicroservicea.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaSenderRouter extends RouteBuilder {
    @Override
    public void configure() {
        from("file:files/kafka")
                .log("${body}")
                .to("kafka:my-kafka-topic");

    }
}