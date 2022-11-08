package br.com.panda.sample.marvel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record Characters(
    float code,
    String status,
    String copyright,
    String attributionText,
    String attributionHTML,
    String etag,
    @JsonProperty("data")
    Summary getSummary
){}

