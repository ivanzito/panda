package br.com.panda.client;

import java.time.Duration;

import static java.util.Objects.isNull;

public class ClientBuilder {

    private Retryable retryable;
    private Duration timeout;
    private Encoder encoder;
    private Request request;

    private ClientBuilder() {}
    public static ClientBuilder of() {
        return new ClientBuilder();
    }

    public ClientBuilder encoder(Encoder encoder) {
        this.encoder = encoder;
        return this;
    }

    public ClientBuilder retryable(Retryable retryable) {
        this.retryable = retryable;
        return this;
    }

    public ClientBuilder timeout(Duration timeout) {
        this.timeout = timeout;
        return this;
    }

    public ClientBuilder request(Request request) {
        this.request = request;
        return this;
    }

    public Client build() {
        final Duration timeoutDuration = isNull(timeout) ? this.timeout = Duration.ofSeconds(10) : timeout;
        return new Client(request, retryable, timeoutDuration, encoder);
    }
}
