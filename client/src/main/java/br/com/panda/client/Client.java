package br.com.panda.client;

import java.time.Duration;

public class Client extends Dispatcher {

    private final Request request;
    private final Retryable retryable;
    private final Duration timeout;
    private final Encoder encoder;

    public Client(Request request, Retryable retryable, Duration timeout, Encoder encoder) {
        this.request = request;
        this.retryable = retryable;
        this.timeout = timeout;
        this.encoder = encoder;
    }

    public Retryable getRetryable() {
        return retryable;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public Encoder getEncoder() {
        return encoder;
    }

    public Request getRequest() {
        return request;
    }

    @Override
    public Response request(String uri) {
        return request.call(uri);
    }
}
