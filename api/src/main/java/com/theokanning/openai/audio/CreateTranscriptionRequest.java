package com.theokanning.openai.audio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * A request for OpenAi to create transcription based on an audio file
 * All fields except model are optional
 *
 * https://platform.openai.com/docs/api-reference/audio/create
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateTranscriptionRequest {

    /**
     * The name of the model to use.
     */
    @NonNull
    String model;

    /**
     * An optional text to guide the model's style or continue a previous audio segment. The prompt should match the audio language.
     */
    String prompt;

    /**
     * The format of the transcript output, in one of these options: json or verbose_json
     */
    @JsonProperty("response_format")
    String responseFormat;

    /**
     * The sampling temperature, between 0 and 1.
     * Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.
     * If set to 0, the model will use log probability to automatically increase the temperature until certain thresholds are hit.
     */
    Double temperature;

    /**
     * The language of the input audio. Supplying the input language in ISO-639-1 format will improve accuracy and latency.
     */
    String language;

    /**
     * The timestamp granularities to populate for this transcription. response_format must be set verbose_json to use timestamp granularities.<br>
     * Either or both of these options are supported: word, or segment. <br>
     * Note: There is no additional latency for segment timestamps, but generating word timestamps incurs additional latency.
     */
    @JsonProperty("timestamp_granularities")
    List<String> timestampGranularities;

    /**
     * If true, stream transcript delta events.
     */
    Boolean stream;

    /**
     * Additional response parts, e.g. ["logprobs"].
     */
    List<String> include;

    /**
     * Strategy for chunking long audio inputs.
     */
    @JsonProperty("chunking_strategy")
    Object chunkingStrategy;

    /**
     * Optional known speaker names for diarization models.
     */
    @JsonProperty("known_speaker_names")
    List<String> knownSpeakerNames;

    /**
     * Optional known speaker references for diarization models.
     */
    @JsonProperty("known_speaker_references")
    List<String> knownSpeakerReferences;

}
