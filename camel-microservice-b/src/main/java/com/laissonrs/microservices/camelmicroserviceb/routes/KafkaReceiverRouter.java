package com.laissonrs.microservices.camelmicroserviceb.routes;

import com.laissonrs.microservices.camelmicroserviceb.dto.CurrencyExchangeRequest;
import com.laissonrs.microservices.camelmicroserviceb.processors.CurrencyExchangeProcessor;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component
public class KafkaReceiverRouter extends RouteBuilder {

    @Autowired
    private CurrencyExchangeProcessor currencyExchangeProcessor;

    @Override
    public void configure() {
        // CurrencyExchange
        // { "id": 1, "from": "BRL", "to": "USD", "conversionMultiple": 5, "value": 10 }

        // JSON
        from("kafka:my-kafka-topic")
                .unmarshal()
                .json(JsonLibrary.Jackson, CurrencyExchangeRequest.class)
                .process(currencyExchangeProcessor)
                .to("log:received-messages-from-kafka-topic");

        // // XML
        from("activemq:my-activemq-xml-queue")
                .unmarshal()
                .jacksonxml(CurrencyExchangeRequest.class)
                .process(currencyExchangeProcessor)
                .to("log:received-messages-from-active-xml-mq");
    }
}