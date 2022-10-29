package br.com.panda.client;

public class PandaClient {

    private Retryable retryable;
    private Encoder encoder;
    private Decoder decoder;


    public PandaClient() {}

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
}
