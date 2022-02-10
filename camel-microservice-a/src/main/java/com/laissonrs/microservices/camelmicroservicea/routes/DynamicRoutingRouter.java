package com.laissonrs.microservices.camelmicroservicea.routes;

import com.laissonrs.microservices.camelmicroservicea.beans.DynamicRouterBean;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DynamicRoutingRouter extends RouteBuilder {

    @Autowired
    private DynamicRouterBean dynamicRouterBean;

    @Override
    public void configure() throws Exception {
        from("timer:dynamic-routing?period=35000")
                .routeId("Dynamic-Routing-Route")
                .dynamicRouter(method(dynamicRouterBean));

        from("direct:endpoint04")
                .to("log:direct-endpoint04");
        from("direct:endpoint05")
                .to("log:direct-endpoint05");
        from("direct:endpoint06")
                .to("log:direct-endpoint06");
    }

}