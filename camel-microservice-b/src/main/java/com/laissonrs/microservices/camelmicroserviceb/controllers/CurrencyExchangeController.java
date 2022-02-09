package com.laissonrs.microservices.camelmicroserviceb.controllers;

import com.laissonrs.microservices.camelmicroserviceb.CurrencyExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange findConversionValue(
            @PathVariable String from,
            @PathVariable String to,
            @RequestParam BigDecimal value
    ) {
        return new CurrencyExchange(1L, from, to, BigDecimal.TEN, value);
    }

}
