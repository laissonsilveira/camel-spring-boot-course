package com.laissonrs.microservices.camelmicroservicea.processors;

import com.laissonrs.microservices.camelmicroservicea.components.SimpleLoggingProcessingComponent;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleLoggingProcessor implements Processor {

    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessor.class);

    @Override
    public void process(Exchange exchange) {
        logger.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
    }
}
