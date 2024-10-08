package org.aldettinger;

import java.time.LocalDate;

import org.apache.camel.Handler;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
@ApplicationScoped
public interface CustomPojoExtractionService {

    @RegisterForReflection
    static class CustomPojo {
        public boolean customerSatisfied;
        public String customerName;
        public LocalDate customerBirthday;
        public String summary;
    }

    static final String CUSTOM_POJO_EXTRACT_PROMPT
            = "Extract information about a customer from the text delimited by triple backticks: ```{text}```."
              + "The customerBirthday field should be formatted as YYYY-MM-DD."
              + "The summary field should concisely relate the customer main ask.";

    @UserMessage(CUSTOM_POJO_EXTRACT_PROMPT)
    @Handler
    CustomPojo extractFromText(String text);
}
