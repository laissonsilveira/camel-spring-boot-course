package com.laissonrs.microservices.camelmicroservicea.routes;

import com.laissonrs.microservices.camelmicroservicea.dto.CurrencyExchange;
import com.laissonrs.microservices.camelmicroservicea.strategies.ArrayListAggregationStrategy;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class AggregateRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:files/aggregate")
                .routeId("Aggregate-File-Route")
                .noAutoStartup()
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
                .completionSize(3)
                .to("log:aggregate-file");
    }

}