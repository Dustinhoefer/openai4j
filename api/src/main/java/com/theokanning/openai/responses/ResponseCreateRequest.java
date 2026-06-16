package com.theokanning.openai.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request body for POST /v1/responses.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCreateRequest {
    String model;

    /**
     * System/developer instructions for the model.
     */
    String instructions;

    /**
     * Text, image, or file inputs. For simple text chat, use a list of messages.
     */
    Object input;

    Reasoning reasoning;

    Double temperature;

    ResponseTextConfig text;

    @Builder.Default
    Boolean store = false;
}
