package com.laissonrs.microservices.camelmicroservicea.routes;

import com.laissonrs.microservices.camelmicroservicea.components.SplitterComponent;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SplitRouter extends RouteBuilder {

    @Autowired
    private SplitterComponent splitter;

    @Override
    public void configure() throws Exception {
        from("file:files/csv")
                .routeId("Split-File-Route")
                .noAutoStartup()

                // this
                // .unmarshal().csv()// Need of dependency camel-csv-starter
                // .split(body())

                // or this
                // .convertBodyTo(String.class)
                // .split(body(), ",")

                // or this
                .convertBodyTo(String.class)
                .split(method(splitter))

                .to("log:split-file");
    }

}