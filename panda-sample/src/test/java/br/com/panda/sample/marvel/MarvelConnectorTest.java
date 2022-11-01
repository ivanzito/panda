package br.com.panda.sample.marvel;

import br.com.panda.sample.marvel.dto.Series;
import br.com.panda.sample.marvel.dto.Stories;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MarvelConnectorTest {

    @Test
    void marvelConnectorTest() {
        List<Series> stories = new MarvelConnector().getSeries();
    }
}
