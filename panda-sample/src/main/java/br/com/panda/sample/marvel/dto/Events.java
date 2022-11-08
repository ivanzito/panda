package br.com.panda.sample.marvel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Events(
     float available,
     String collectionURI,
     List<Item> items,
     float returned
){}
