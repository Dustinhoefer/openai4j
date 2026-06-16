package com.theokanning.openai.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kjetland.jackson.jsonSchema.JsonSchemaConfig;
import com.kjetland.jackson.jsonSchema.JsonSchemaGenerator;
import com.theokanning.openai.completion.chat.ResponseJsonSchema;
import com.theokanning.openai.utils.JsonUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

/**
 * Text output format for the Responses API ({@code text.format}).
 * Unlike Chat Completions {@code response_format}, json_schema fields are flat
 * under format (name/strict/schema), not nested in a json_schema object.
 */
@Data
@NoArgsConstructor
@JsonSerialize(using = ResponseTextFormat.ResponseTextFormatSerializer.class)
public class ResponseTextFormat {
    private String type;
    private String name;
    private Boolean strict;

    @JsonProperty("schema")
    private Object schemaDefinition;

    private Class<?> schemaClass;

    private ResponseTextFormat(String type) {
        this.type = type;
    }

    public static final ResponseTextFormat TEXT = new ResponseTextFormat("text");

    public static final ResponseTextFormat JSON_OBJECT = new ResponseTextFormat("json_object");

    public static ResponseTextFormat jsonSchema(ResponseJsonSchema jsonSchema) {
        ResponseTextFormat format = new ResponseTextFormat("json_schema");
        format.setName(jsonSchema.getName());
        format.setStrict(jsonSchema.isStrict());
        format.setSchemaClass(jsonSchema.getSchemaClass());
        format.setSchemaDefinition(jsonSchema.getSchemaDefinition());
        return format;
    }

    public static class ResponseTextFormatSerializer extends JsonSerializer<ResponseTextFormat> {
        private final JsonSchemaConfig config = JsonSchemaConfig.vanillaJsonSchemaDraft4();
        private final JsonSchemaGenerator jsonSchemaGenerator = new JsonSchemaGenerator(JsonUtil.getInstance(), config);

        @Override
        public void serialize(ResponseTextFormat value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("type", value.getType());

            if ("json_schema".equals(value.getType())) {
                gen.writeStringField("name", value.getName());
                if (value.getStrict() != null) {
                    gen.writeBooleanField("strict", value.getStrict());
                }
                gen.writeFieldName("schema");
                if (value.getSchemaClass() != null) {
                    ObjectNode parameterSchema = (ObjectNode) jsonSchemaGenerator.generateJsonSchema(value.getSchemaClass());
                    parameterSchema.remove("$schema");
                    parameterSchema.remove("title");
                    gen.writeRawValue(JsonUtil.writeValueAsString(parameterSchema));
                } else {
                    Object schemaDef = value.getSchemaDefinition();
                    if (schemaDef instanceof String stringSchema && JsonUtil.isValidJson(stringSchema)) {
                        gen.writeRawValue(JsonUtil.getInstance().readTree(stringSchema).toPrettyString());
                    } else {
                        gen.writeRawValue(JsonUtil.writeValueAsString(schemaDef));
                    }
                }
            }

            gen.writeEndObject();
        }
    }
}
