package br.com.panda.sample.marvel.config;


import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record MarvelConfig(
    Marvel getMarvel
){}
