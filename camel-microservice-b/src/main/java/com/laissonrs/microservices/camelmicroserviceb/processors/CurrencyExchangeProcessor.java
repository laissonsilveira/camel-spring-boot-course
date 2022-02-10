package com.laissonrs.microservices.camelmicroserviceb.processors;

import com.laissonrs.microservices.camelmicroserviceb.dto.CurrencyExchangeRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class CurrencyExchangeProcessor implements Processor {

    @Override
    public void process(final Exchange exchange) {
        CurrencyExchangeRequest currencyExchangeRequest = (CurrencyExchangeRequest) exchange.getMessage().getBody();
        exchange.getMessage().setBody("Exchange result is " + currencyExchangeRequest.getValue().multiply(currencyExchangeRequest.getConversionMultiple()));
    }

}