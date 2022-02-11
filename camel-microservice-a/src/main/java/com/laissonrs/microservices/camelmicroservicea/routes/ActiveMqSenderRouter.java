package com.laissonrs.microservices.camelmicroservicea.routes;

import com.laissonrs.microservices.utils.MyCryptoData;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.camel.builder.RouteBuilder;

// @Component
public class ActiveMqSenderRouter extends RouteBuilder {

    public static final MyCryptoData cryptoData = new MyCryptoData();

    @Override
    public void configure() throws UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException {
        from("timer:active-mq-timer?period={{timer.period}}")
                .noAutoStartup()
                .transform().constant("My message for Active MQ")
                .marshal(cryptoData.createEncryptor())
                .log("${body}")
                .to("activemq:my-activemq-crypto-queue");

        from("file:files/json")
                .routeId("ActiveMQ-Sender-JSON-Route")
                .log("${body}")
                .to("activemq:my-activemq-json-queue");

        from("file:files/xml")
                .routeId("ActiveMQ-Sender-XML-Route")
                .log("${body}")
                .to("activemq:my-activemq-xml-queue");
    }
}