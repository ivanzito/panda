package br.com.panda.sample.marvel;

import br.com.panda.client.*;
import br.com.panda.client.java11.DefaultRequest;
import br.com.panda.sample.marvel.config.Key;
import br.com.panda.sample.marvel.config.MarvelConfig;
import br.com.panda.sample.marvel.dto.Characters;
import br.com.panda.sample.marvel.dto.Comics;
import br.com.panda.sample.marvel.dto.Events;
import br.com.panda.sample.marvel.dto.Results;
import br.com.panda.sample.marvel.dto.Series;
import br.com.panda.sample.marvel.dto.Stories;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;

import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class MarvelConnector {
    private Characters characters;
    private final Request request = new DefaultRequest(HttpMethod.GET, Map.of("Accept", List.of("*/*")));
    private final Client client = ClientBuilder.of()
            .request(request)
            .build();

    private final String URI = "https://gateway.marvel.com:443/v1/public/characters";

    public void sample() {
        new MarvelConnector().getStories();
    }

    @SneakyThrows
    public Characters getCharacters() {
        if (isNull(characters)) {
            Response response = client.request(URI + this.hash());
            this.characters = new ObjectMapper().readValue(response.body(), Characters.class);
        }
        return this.characters;
    }

    @SneakyThrows
    public List<Events> getEvents() {
        if (isNull(characters)) {
            Response response = client.request(URI + this.hash());
            this.characters = new ObjectMapper().readValue(response.body(), Characters.class);
        }
        return this.characters.getSummary()
                .getResults()
                .stream()
                .map(Results::getEvents)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public List<Stories> getStories() {
        if (isNull(characters)) {
            Response response = client.request(URI + this.hash());
            System.out.println(response.body());
            this.characters = new ObjectMapper().readValue(response.body(), Characters.class);
        }
        return this.characters.getSummary()
            .getResults()
            .stream()
            .map(Results::getStories)
            .collect(Collectors.toList());
    }

    @SneakyThrows
    public List<Series> getSeries() {
        if (isNull(characters)) {
            Response response = client.request(URI + this.hash());
            this.characters = new ObjectMapper().readValue(response.body(), Characters.class);
        }
        return this.characters
            .getSummary()
            .getResults()
            .stream()
            .map(Results::getSeries)
            .collect(Collectors.toList());
    }

    @SneakyThrows
    public List<Comics> getComics() {
        if (isNull(characters)) {
            Response response = client.request(URI + this.hash());
            this.characters = new ObjectMapper().readValue(response.body(), Characters.class);
        }
        return this.characters.getSummary()
                .getResults()
                .stream()
                .map(Results::getComics)
                .collect(Collectors.toList());
    }


    @SneakyThrows
    private String hash()  {

        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory()); // jackson databind
        MarvelConfig config =  mapper.readValue(new File("./src/main/resources/marvel.yaml"), MarvelConfig.class);
        Key keys = config.getMarvel().getKey();

        long ts = Timestamp.from(Instant.now()).getTime();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] md5Digest = md5.digest(String.format("%s%s%s", ts, keys.getSecret(), keys.getKey()).getBytes(StandardCharsets.UTF_8));
        String hash = new BigInteger(1, md5Digest).toString(16);
        return String.format("?apikey=%s&ts=%s&hash=%s", keys.getKey(), ts, hash);
    }
}
