package br.com.panda.sample.marvel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record Summary(

    float offset,
    float limit,
    float total,
    float count,
    @JsonProperty("results")
    List<Results> getResults
){}
