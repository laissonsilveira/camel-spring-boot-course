package com.laissonrs.microservices.camelmicroservicea.routes;

import org.apache.camel.builder.RouteBuilder;

// @Component
public class ActiveMqSenderRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("timer:active-mq-timer?period=1000")
                .noAutoStartup()
                .transform().constant("My message for Active MQ")
                .log("${body}")
                .to("activemq:my-activemq-queue");

        from("file:files/json")
                .routeId("ActiveMQ-Sender-JSON-Route")
                .log("${body}")
                .to("activemq:my-activemq-queue");

        from("file:files/xml")
                .routeId("ActiveMQ-Sender-XML-Route")
                .log("${body}")
                .to("activemq:my-activemq-xml-queue");
    }
}