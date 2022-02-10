package com.laissonrs.microservices.camelmicroservicea.beans;

import java.util.Map;

import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DeciderBean {

    Logger logger = LoggerFactory.getLogger(DeciderBean.class);

    public boolean isThisConditionMet(String body, @Headers Map<String, String> headers) {
        logger.info("Body: {} | Headers: {}", body, headers);
        return body.contains("BRL");// simple("${body} contains 'BRL'");
    }

}