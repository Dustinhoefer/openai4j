package com.theokanning.openai.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    String id;
    String object;
    @JsonProperty("created_at")
    Long createdAt;
    String status;
    String model;
    List<ResponseOutputMessage> output;
    Reasoning reasoning;
    ResponseUsage usage;

    /**
     * Aggregates all {@code output_text} parts from assistant messages.
     */
    @JsonIgnore
    public String getOutputText() {
        if (output == null || output.isEmpty()) {
            return "";
        }
        return output.stream()
                .filter(item -> "message".equals(item.getType()))
                .filter(item -> item.getContent() != null)
                .flatMap(item -> item.getContent().stream())
                .filter(part -> "output_text".equals(part.getType()))
                .map(ResponseOutputText::getText)
                .collect(Collectors.joining());
    }
}
