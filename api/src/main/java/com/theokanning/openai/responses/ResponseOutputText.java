package com.theokanning.openai.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseOutputText {
    String type;
    String text;
}
