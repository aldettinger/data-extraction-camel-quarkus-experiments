package org.aldettinger;

import static io.restassured.RestAssured.given;
import static org.apache.commons.io.IOUtils.resourceToString;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.github.dockerjava.zerodep.shaded.org.apache.commons.codec.Charsets;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class RouteTest {

    @Test
    void customPojoExtractionServiceReturnPojo() throws IOException {

        String conversation = resourceToString("/texts/03_kate-boss-13-08-1999-satisfied.txt", Charsets.UTF_8);

        given()
                .body(conversation)
                .when()
                .post("/custom-pojo-extraction-service")
                .then()
                .statusCode(200);
    }

}
