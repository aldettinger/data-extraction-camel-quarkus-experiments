package org.aldettinger;

import org.apache.camel.builder.RouteBuilder;

public class Routes extends RouteBuilder {

    @Override
    public void configure() {
        from("direct:test")
                .bean(CustomPojoExtractionService.class)
                .log("direct:test");
    }
}
