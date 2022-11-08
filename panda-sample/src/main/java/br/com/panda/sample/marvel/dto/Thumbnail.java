package br.com.panda.sample.marvel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;



@JsonInclude(JsonInclude.Include.NON_NULL)
public record Thumbnail(
    String path,
    String extension
) {}