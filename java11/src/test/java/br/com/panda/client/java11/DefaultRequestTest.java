package br.com.panda.client.java11;

import br.com.panda.client.Client;
import br.com.panda.client.HttpMethod;
import br.com.panda.client.Request;
import br.com.panda.client.ClientBuilder;
import br.com.panda.retry.DefaultRetry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class DefaultRequestTest {

    private final Request request = new DefaultRequest(HttpMethod.GET, Map.of("Accept", List.of("*/*")));
    private final Client client = ClientBuilder.of()
            .request(request)
            .build();

    @Test
    public void requestWithRetry() {
    System.out.println(client.request("http://www.google.com").body());

    }

}