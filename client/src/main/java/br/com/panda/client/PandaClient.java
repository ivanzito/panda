package br.com.panda.client;

import java.util.Map;

import static java.util.Objects.nonNull;

public class PandaClient {

    private Retryable retryable;
    private Encoder encoder;
    private Decoder decoder;
    private final Request request;


    public PandaClient(Request request) {
        this.request = request;
    }

    public PandaClient and() {
        return this;
    }

    public PandaClient retryable(Retryable retryable) {
        this.retryable = retryable;
        return this;
    }

    public PandaClient encoder(Encoder encoder) {
        this.encoder = encoder;
        return this;
    }

    public PandaClient decoder(Decoder decoder) {
        this.decoder = decoder;
        return this;
    }

    public Retryable getRetryable() {
        return retryable;
    }

    public Encoder getEncoder() {
        return encoder;
    }

    public Decoder getDecoder() {
        return decoder;
    }

    public <T> String decode(T object) {
       return this.getDecoder().prettyPrinter(object);
    }

    public Response request(String uri) {
        PandaClientProxy pandaClientProxy = new PandaClientProxy(this.request, retryable, encoder, decoder);
        if (nonNull(this.retryable)) {
            try {
                return request.call(uri);
            } catch (Exception e) {
                return pandaClientProxy.request(uri, 1);
            }
        } else {
            return request.call(uri);
        }
    }

    public Response request(String uri, HttpMethod httpMethod) {
        return request.call(uri, httpMethod);
    }
    public Response request(String uri, HttpMethod httpMethod, Map<String, String> headers) {
        return request.call(uri, httpMethod, headers);
    }
    public Response request(String uri, HttpMethod httpMethod, String body, Map<String, String> headers){
        return request.call(uri, httpMethod, body, headers);
    }

}
