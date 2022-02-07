package com.laissonrs.microservices.camelmicroservicea.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderRouter extends RouteBuilder {
    @Override
    public void configure() {
        // from("timer:active-mq-timer?period=1000")
        //         .transform().constant("My message for Active MQ")
        //         .log("${body}")
        //         .to("activemq:my-activemq-queue");

        from("file:files/json")
                .log("${body}")
                .to("activemq:my-activemq-queue");

        from("file:files/xml")
                .log("${body}")
                .to("activemq:my-activemq-xml-queue");
    }
}