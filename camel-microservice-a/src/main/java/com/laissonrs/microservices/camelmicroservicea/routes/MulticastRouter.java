package com.laissonrs.microservices.camelmicroservicea.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MulticastRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:myMulticast?period={{timer.period}}")
                .noAutoStartup()
                .multicast()
                .to("log:something01", "log:something02", "log:something03");
    }

}