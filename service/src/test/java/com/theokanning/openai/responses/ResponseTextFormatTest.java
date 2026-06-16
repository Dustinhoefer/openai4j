package com.theokanning.openai.responses;

import com.fasterxml.jackson.databind.JsonNode;
import com.theokanning.openai.completion.chat.ResponseJsonSchema;
import com.theokanning.openai.utils.JsonUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTextFormatTest {

    @Test
    void jsonSchemaSerializesFlatFormatForResponsesApi() throws Exception {
        ResponseJsonSchema jsonSchema = ResponseJsonSchema.builder()
                .name("poll_prediction_response")
                .strict(true)
                .schemaDefinition(Map.of(
                        "type", "object",
                        "properties", Map.of("title", Map.of("type", "string")),
                        "required", java.util.List.of("title")
                ))
                .build();

        ResponseTextConfig config = ResponseTextConfig.builder()
                .format(ResponseTextFormat.jsonSchema(jsonSchema))
                .build();

        String json = JsonUtil.writeValueAsString(config);
        JsonNode root = JsonUtil.getInstance().readTree(json);

        JsonNode format = root.get("format");
        assertNotNull(format);
        assertEquals("json_schema", format.get("type").asText());
        assertEquals("poll_prediction_response", format.get("name").asText());
        assertTrue(format.get("strict").asBoolean());
        assertNotNull(format.get("schema"));
        assertNull(format.get("json_schema"), "Responses API must not nest schema under json_schema");
    }
}
