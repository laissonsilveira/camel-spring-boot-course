package com.laissonrs.microservices.camelmicroserviceb.routes;

import com.laissonrs.microservices.camelmicroserviceb.dto.CurrencyExchangeRequest;
import com.laissonrs.microservices.camelmicroserviceb.processors.CurrencyExchangeProcessor;
import com.laissonrs.microservices.utils.MyCryptoData;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;

// @Component
public class ActiveMqReceiverRouter extends RouteBuilder {

    @Autowired
    private CurrencyExchangeProcessor currencyExchangeProcessor;

    public static final MyCryptoData cryptoData = new MyCryptoData();

    @Override
    public void configure() throws UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
        // CurrencyExchange
        // { "id": 1, "from": "BRL", "to": "USD", "conversionMultiple": 5, "value": 10 }

        from("activemq:my-activemq-crypto-queue")
                .routeId("ActiveMQ-Receiver-Crypto-Route")
                .unmarshal(cryptoData.createEncryptor())
                .to("log:received-messages-from-active-mq");

        // JSON
        from("activemq:my-activemq-json-queue")
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