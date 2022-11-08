package br.com.panda.sample.marvel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record Results(
    float id,
    String name,
    String description,
    String modified,
    Thumbnail thumbnail,
    @JsonProperty("resourceURI")
    String resource,
    @JsonProperty("comics")
    Comics getComics,
    @JsonProperty("series")
    Series getSeries,
    @JsonProperty("stories")
    Stories getStories,
    @JsonProperty("resourceURI")
    Events getEvents,
    List<Urls> urls
){}

