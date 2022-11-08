package br.com.panda.sample.marvel.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)

public record Key(
        @JsonProperty("public")
        String getKey,
        @JsonProperty("private")
        String getSecret
) {
}
