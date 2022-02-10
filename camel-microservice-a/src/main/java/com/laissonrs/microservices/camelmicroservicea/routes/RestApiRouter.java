package com.laissonrs.microservices.camelmicroservicea.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestApiRouter extends RouteBuilder {

    @Override
    public void configure() {

        restConfiguration().host("localhost").port(8000);

        String from = "BRL";
        String to = "USD";
        from("timer:rest-api-consumer?period=10000")
                .routeId("Rest-API-Consumer-Route")
                .noAutoStartup()
                .setHeader("from", () -> from)
                .setHeader("to", () -> to)
                .to("rest:get:/currency-exchange/from/{from}/to/{to}?value=5")
                .log(String.format("Result to 'rest:get:/currency-exchange/from/%s/to/%s?value=5' is ${body}", from, to));
    }
}