package com.laissonrs.microservices.camelmicroserviceb.routes;

import com.laissonrs.microservices.camelmicroserviceb.dto.CurrencyExchangeRequest;
import com.laissonrs.microservices.camelmicroserviceb.processors.CurrencyExchangeProcessor;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component
public class ActiveMqReceiverRouter extends RouteBuilder {

    @Autowired
    private CurrencyExchangeProcessor currencyExchangeProcessor;

    @Override
    public void configure() {
        // CurrencyExchange
        // { "id": 1, "from": "BRL", "to": "USD", "conversionMultiple": 5, "value": 10 }

        // JSON
        from("activemq:my-activemq-queue")
                .routeId("ActiveMQ-Receiver-JSON-Route")
                .unmarshal()
                .json(JsonLibrary.Jackson, CurrencyExchangeRequest.class)
                .process(currencyExchangeProcessor)
                .to("log:received-messages-from-active-mq");

        // XML
        from("activemq:my-activemq-xml-queue")
                .routeId("ActiveMQ-Receiver-XML-Route")
                .unmarshal()
                .jacksonxml(CurrencyExchangeRequest.class)
                .process(currencyExchangeProcessor)
                .to("log:received-messages-from-active-xml-mq");
    }
}