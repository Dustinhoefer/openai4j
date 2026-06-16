package com.theokanning.openai.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Configuration options for reasoning models in the Responses API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reasoning {
    /**
     * Constrains effort on reasoning: none, minimal, low, medium, high, xhigh.
     */
    String effort;

    /**
     * A summary of the reasoning performed by the model: auto, concise, detailed.
     */
    String summary;
}
