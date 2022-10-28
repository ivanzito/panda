package br.com.panda.sample.marvel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Characters {
    private float code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private String etag;

    @JsonProperty("data")
    Summary summary;
}

