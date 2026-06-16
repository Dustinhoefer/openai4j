package com.theokanning.openai.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.theokanning.openai.completion.chat.ChatResponseFormat;
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
    @JsonSerialize(using = ChatResponseFormat.ChatResponseFormatSerializer.class)
    ChatResponseFormat format;

    String verbosity;
}
