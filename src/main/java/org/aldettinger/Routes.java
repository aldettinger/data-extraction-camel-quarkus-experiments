package org.aldettinger;

import org.apache.camel.builder.RouteBuilder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class Routes extends RouteBuilder {

    @Inject
    CustomPojoExtractionService customPojoExtractionService;

    @Override
    public void configure() {
        from("platform-http:/custom-pojo-extraction-service")
                .bean(customPojoExtractionService)
                .bean(this, "toPrettyFormat")
                .log("Extracted message is ${body}");
    }

    private final static String FORMAT = "****************************************\n"
                                         + "customerSatisfied: %s\n"
                                         + "customerName: %s\n"
                                         + "customerBirthday: %td %tB %tY\n"
                                         + "summary: %s\n"
                                         + "****************************************\n";

    public static String toPrettyFormat(CustomPojoExtractionService.CustomPojo extract) {
        return String.format(FORMAT, extract.customerSatisfied, extract.customerName, extract.customerBirthday,
                extract.customerBirthday, extract.customerBirthday, extract.summary);
    }
}
