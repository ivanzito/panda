package br.com.panda.client.java11;

import br.com.panda.client.HttpMethod;
import br.com.panda.client.Request;
import br.com.panda.client.ClientBuilder;
import br.com.panda.retry.DefaultRetry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DefaultRequestTest {


    @Test
    public void requestWithRetry() {
    Request request = ClientBuilder.of()
        .method(HttpMethod.GET)
        .retryable(DefaultRetry.DEFAULT_RETRY)
        .build();
    System.out.println(request.doRequest("http://www.google.com").body());

    }

}