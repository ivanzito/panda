package br.com.panda.sample.marvel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Results {
    private float id;
    private String name;
    private String description;
    private String modified;
    private Thumbnail thumbnail;
    @JsonProperty("resourceURI")
    private String resource;
    private Comics comics;
    private Series series;
    private Stories stories;
    private Events events;
    private List<Urls> urls;
}

