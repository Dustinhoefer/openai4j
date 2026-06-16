package com.theokanning.openai.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A message input for the Responses API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInputMessage {
  String role;
  String content;
  String type;
}
