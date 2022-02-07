package com.laissonrs.microservices.camelmicroserviceb.processors;

import com.laissonrs.microservices.camelmicroserviceb.CurrencyExchange;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * @author Laisson R. Silveira
 * laisson.r.silveira@gmail.com
 * 02/07/22
 **/
@Component
public class CurrencyExchangeProcessor implements Processor {

    @Override
    public void process(final Exchange exchange) {
        CurrencyExchange currencyExchange = (CurrencyExchange) exchange.getMessage().getBody();
        exchange.getMessage().setBody("Exchange result is " + currencyExchange.getValue().multiply(currencyExchange.getConversionMultiple()));
    }

}