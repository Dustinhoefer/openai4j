package com.theokanning.openai.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.theokanning.openai.CompletionTokensDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUsage {
    @JsonProperty("input_tokens")
    long inputTokens;

    @JsonProperty("output_tokens")
    long outputTokens;

    @JsonProperty("total_tokens")
    long totalTokens;

    @JsonProperty("output_tokens_details")
    CompletionTokensDetails outputTokensDetails;
}
