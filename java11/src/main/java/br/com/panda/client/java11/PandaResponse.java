package br.com.panda.client.java11;


import br.com.panda.client.Decoder;
import br.com.panda.client.Response;
import br.com.panda.decoder.JacksonCodec;

import java.util.List;
import java.util.Map;


public class PandaResponse implements Response {

    private final int statusCode;
    private final String body;
    private final Map<String, List<String>> headers;
    private boolean verbose = true;

    public PandaResponse(String body, int statusCode, Map<String, List<String>> headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = headers;
    }

    @Override
    public String body() {
        return this.body;
    }

    @Override
    public <T> T decode(Class<T> clazz) {
        T decode = this.decoder().decode(this.body, clazz);
        if (verbose) {
            System.out.println(this.decoder().prettyPrinter(decode));
        }
        return decode;
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
