package com.laissonrs.microservices.camelmicroservicea.routes;

import com.laissonrs.microservices.camelmicroservicea.beans.GetCurrentTimeBean;
import com.laissonrs.microservices.camelmicroservicea.components.SimpleLoggingProcessingComponent;
import com.laissonrs.microservices.camelmicroservicea.processors.SimpleLoggingProcessor;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimerRouter extends RouteBuilder {

    @Autowired
    public GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    public SimpleLoggingProcessingComponent loggingComponent;

    @Autowired
    public SimpleLoggingProcessor simpleLoggingProcessor;

    @Override
    public void configure() {
        from("timer:first-timer?period={{timer.period}}")
                .routeId("Timer-Route")
                .noAutoStartup()
                .log("${body}")// print null
                .transform().constant("My const message")
                .log("${body}")//print "My const message"

                //.transform().constant("Time now is " + LocalDateTime.now())
                //.bean("getCurrentTimeBean")// without Autowired
                //.bean(getCurrentTimeBean, "getCurrentTime")//using method

                .bean(getCurrentTimeBean)//don't need method because exist only one
                .log("${body}")//print Time now is 2022-01-28T17:11:25.267360600
                .bean(loggingComponent)//print SimpleLoggingProcessingComponent Time now is 2022-01-28T17:11:25.267360600
                .log("${body}")//print Time now is 2022-01-28T17:11:25.267360600
                .process(simpleLoggingProcessor)//print SimpleLoggingProcessor Time now is 2022-01-28T17:11:25.267360600
                .log("${body}")//print Time now is 2022-01-28T17:11:25.267360600
                .to("log:first-timer");//Exchange[ExchangePattern: InOnly, BodyType: String, Body: Time now is 2022-01-28T17:11:25.267360600]
    }
}