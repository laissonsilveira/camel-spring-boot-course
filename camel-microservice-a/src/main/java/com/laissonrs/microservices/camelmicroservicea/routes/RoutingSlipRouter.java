package com.laissonrs.microservices.camelmicroservicea.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RoutingSlipRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:routing-slip?period={{timer.period}}")
                .routeId("Routing-Slip-Route")
                .noAutoStartup()
                .transform().constant("Message test to Routing Slip")
                .routingSlip(simple("direct:endpoint01,direct:endpoint02,direct:endpoint03"))
                .to("log:routing-slip");

        from("direct:endpoint01")
                .to("log:direct-endpoint01");
        from("direct:endpoint02")
                .to("log:direct-endpoint02");
        from("direct:endpoint03")
                .to("log:direct-endpoint03");
    }

}