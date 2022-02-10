package com.laissonrs.microservices.camelmicroserviceb.controllers;

import com.laissonrs.microservices.camelmicroserviceb.dto.CurrencyExchangeRequest;
import com.laissonrs.microservices.camelmicroserviceb.dto.Response;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public Response conversionValue(
            @PathVariable String from,
            @PathVariable String to,
            @RequestParam BigDecimal value
    ) {
        CurrencyExchangeRequest currencyExchangeRequest = new CurrencyExchangeRequest(1L, from, to, BigDecimal.TEN, value);
        String result = String.valueOf(currencyExchangeRequest.getValue().multiply(currencyExchangeRequest.getConversionMultiple()));
        return new Response(result);
    }

}