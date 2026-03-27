package com.theokanning.openai.moderation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * An object containing the scores for each moderation category
 *
 * https://beta.openai.com/docs/api-reference/moderations/create
 */
@Data
public class ModerationCategoryScores {

    public double harassment;

    @JsonProperty("harassment/threatening")
    public double harassmentThreatening;

    public double hate;

    @JsonProperty("hate/threatening")
    public double hateThreatening;

    public double illicit;

    @JsonProperty("illicit/violent")
    public double illicitViolent;

    @JsonProperty("self-harm")
    public double selfHarm;

    @JsonProperty("self-harm/instructions")
    public double selfHarmInstructions;

    @JsonProperty("self-harm/intent")
    public double selfHarmIntent;

    public double sexual;

    @JsonProperty("sexual/minors")
    public double sexualMinors;

    public double violence;

    @JsonProperty("violence/graphic")
    public double violenceGraphic;
}
