package br.com.panda.client.java11;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PandaRequestTest {

    @Test
    public void requestWithRetry() {
        System.out.println(new PandaRequest().call("http://www.google.com"));
    }

}