package br.com.panda.client;

import br.com.panda.client.exception.RetryExhausted;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.nonNull;

public class PandaClient implements Serializable {


    @Serial
    private static final long serialVersionUID = UUID.randomUUID().version();

    private transient Retryable retryable;
    private transient Encoder encoder;
    private transient Decoder decoder;
    private transient final Request request;

    public PandaClient(Request req, Retryable retryable, Encoder encoder, Decoder decoder) {
        this.retryable = retryable;
        this.encoder = encoder;
        this.decoder = decoder;
        this.request = req;
    }

    public PandaClient getRetry(Retryable retryable) {
        this.retryable = retryable;
        return this;
    }

    public PandaClient getEncoder(Encoder encoder) {
        this.encoder = encoder;
        return this;
    }

    public PandaClient getDecoder(Decoder decoder) {
        this.decoder = decoder;
        return this;
    }

    public Retryable getRetry() {
        return this.retryable;
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
        return this.request(pandaClientProxy, uri);

    }
    private Response request(PandaClientProxy pandaClientProxy, String uri) {
        try {
            return request.call(uri);
        } catch (Exception e) {
            if (nonNull(pandaClientProxy.getRetry())) {
                pandaClientProxy.getRetry().onRequestError(e);
                if (retryable.shouldRetry()) {
                    this.request(pandaClientProxy, uri);
                }
            }
            throw new RetryExhausted(this.retryable.retries());
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
