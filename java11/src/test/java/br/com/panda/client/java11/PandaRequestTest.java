package br.com.panda.client.java11;


import br.com.panda.client.PandaClient;
import br.com.panda.client.PandaClientBuilder;
import br.com.panda.client.Response;
import br.com.panda.client.exception.RetryExhausted;
import br.com.panda.retry.DefaultRetry;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@WireMockTest
public class PandaRequestTest {

    private final static WireMockServer wireMockServer = new WireMockServer();

    @BeforeAll
    public static void init() {
        wireMockServer.start();
    }

    @AfterAll
    public static void shutdown() {
        wireMockServer.stop();
    }

    private static final String URL = "http://localhost:8080/animals/rand/10";
    @Test
    void given_a_get_request_when_do_a_request_then_is_expected_status_a_200() {

        stubFor(get("http://localhost:8080/animals/rand/10")
                .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json", "charset=utf-8")));


        PandaClient client = PandaClientBuilder.of(new PandaRequest(Duration.ofSeconds(2))).build();
        Response response = client.request("http://localhost:8080/animals/rand/10");
        assertThat(response.statusCode(), is(200));
    }

    @Test
    void given_a_request_when_the_duration_excedes_the_timeout_then_throw_an_exception() {

        final PandaClient client = PandaClientBuilder.of(new PandaRequest(Duration.ofSeconds(8))).build();

        stubFor(get("http://localhost:8080/delayed")
                .willReturn(aResponse()
                        .withStatus(408)
                        .withFixedDelay(10000)
                        .withHeader("Content-Type", "application/json", "charset=utf-8")));


        Response response = client.request("http://localhost:8080/delayed");
        assertThat(response.statusCode(), is(408));
    }

    @Test
    void testRetry() {
        PandaClient client = PandaClientBuilder
                .of(new PandaRequest())
                .and()
                .retry(DefaultRetry.RETRY).build();
        Assertions.assertThrows(RetryExhausted.class, () -> client.request("http://www.googles.com"));
    }
}