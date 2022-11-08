package br.com.panda.sample.marvel;

import br.com.panda.sample.marvel.dto.Series;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class MarvelConnectorTest {

    @Test
    void marvelConnectorTest() throws NoSuchAlgorithmException, IOException {
        List<Series> stories = new MarvelConnector().getSeries();
    }
}
