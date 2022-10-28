package br.com.panda.client.java11;


import br.com.panda.client.Decoder;
import br.com.panda.client.Encoder;
import br.com.panda.client.Response;
import br.com.panda.decoder.JacksonCodec;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


public class DefaultResponse implements Response {

    private final int statusCode;
    private final String body;
    private final Map<String, List<String>> headers;

    public DefaultResponse(String body, int statusCode, Map<String, List<String>> headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
    }

    @Override
    public String body() {
        return this.body;
    }


    @Override
    public String body(final Type type) {
        try {
            return (String) this.decoder().decode(body, type);
        } catch (IOException ex) {
            throw new RuntimeException("Occurred an error while decoding response", ex);
        }
    }

    @Override
    public int statusCode() {
        return this.statusCode;
    }

    @Override
    public Decoder decoder() {
        return new JacksonCodec();
    }

    @Override
    public Map<String, List<String>> headers() {
        return this.headers;
    }

}
