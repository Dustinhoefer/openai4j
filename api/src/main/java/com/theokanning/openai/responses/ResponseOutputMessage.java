package com.theokanning.openai.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseOutputMessage {
    String id;
    String type;
    String status;
    String role;
    List<ResponseOutputText> content;
}
