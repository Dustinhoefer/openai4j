package com.theokanning.openai.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Text output configuration for the Responses API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTextConfig {
    ResponseTextFormat format;

    String verbosity;
}
