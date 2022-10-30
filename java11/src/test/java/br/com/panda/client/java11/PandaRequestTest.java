package br.com.panda.client.java11;


import br.com.panda.client.PandaClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PandaRequestTest {

    private final PandaClient client = new PandaClient(new PandaRequest());

    @Test
    public void requestWithRetry() {
        System.out.println(client.request("http://www.google.com"));
    }

}