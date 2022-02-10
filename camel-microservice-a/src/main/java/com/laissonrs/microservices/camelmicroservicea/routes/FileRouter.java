package com.laissonrs.microservices.camelmicroservicea.routes;

import com.laissonrs.microservices.camelmicroservicea.beans.DeciderBean;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileRouter extends RouteBuilder {

    @Autowired
    private DeciderBean deciderBean;

    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .routeId("Files-Input-Route")
                .noAutoStartup()
                .transform().body(String.class)
                .choice()
                    .when(simple("${file:ext} ends with 'xml'"))
                        .log("XML file")
                    .when(method(deciderBean))// simple("${body} contains 'BRL'");
                        .log("not xml but contains BRL")
                    .otherwise()
                        .log("not an XML file")
                .end()
                .to("direct://log-file-values")
                .to("file:files/output");

        from("direct:log-file-values")
                .log("${messageHistory} ${file:absolute.path}")
                .log("${file:name} ${file:name.ext} ${file:onlyname}")
                .log("${routeId} ${camelId} ${body}");
    }
}