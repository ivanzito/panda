package br.com.panda.sample.marvel;

import br.com.panda.client.PandaClient;
import br.com.panda.client.Response;
import br.com.panda.client.java11.PandaRequest;
import br.com.panda.sample.marvel.config.Key;
import br.com.panda.sample.marvel.config.MarvelConfig;
import br.com.panda.sample.marvel.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class MarvelConnector {

    private Characters characters;
    private final PandaClient pandaClient = new PandaClient(new PandaRequest());
    private final String URI = "https://gateway.marvel.com:443/v1/public/characters";

    public PandaClient getPandaClient() {
        return pandaClient;
    }

    public String getURI() {
        return URI;
    }

    public Characters getCharacters() throws NoSuchAlgorithmException, IOException {
        if (isNull(characters)) {
            Response response = pandaClient.request(URI + this.hash());
            this.characters = response.decode(Characters.class);
        }
        return this.characters;
    }

    public List<Events> getEvents() throws NoSuchAlgorithmException, IOException {
        if (isNull(characters)) {
            Response response = pandaClient.request(URI + this.hash());
            this.characters = response.decode(Characters.class);
        }
        return this.characters.getSummary()
                .getResults()
                .stream()
                .map(Results::getEvents)
                .collect(Collectors.toList());
    }

    public List<Stories> getStories() throws NoSuchAlgorithmException, IOException {
        if (isNull(characters)) {
            Response response = pandaClient.request(URI + this.hash());
            this.characters = response.decode(Characters.class);
        }
        return this.characters.getSummary()
            .getResults()
            .stream()
            .map(Results::getStories)
            .collect(Collectors.toList());
    }

    public List<Series> getSeries() throws NoSuchAlgorithmException, IOException {
        if (isNull(characters)) {
            Response response = pandaClient.request(URI + this.hash());
            this.characters = response.decode(Characters.class);
        }
        return this.characters
            .getSummary()
            .getResults()
            .stream()
            .map(Results::getSeries)
            .collect(Collectors.toList());
    }

    public List<Comics> getComics() throws NoSuchAlgorithmException, IOException {
        if (isNull(characters)) {
            Response response = pandaClient.request(URI + this.hash());
            this.characters = response.decode(Characters.class);
        }
        return this.characters.getSummary()
                .getResults()
                .stream()
                .map(Results::getComics)
                .collect(Collectors.toList());
    }


    private String hash() throws NoSuchAlgorithmException, IOException {

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
