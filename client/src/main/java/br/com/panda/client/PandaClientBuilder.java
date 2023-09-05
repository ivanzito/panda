package br.com.panda.client;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class PandaClientBuilder implements Serializable {

    @Serial
    private static final long serialVersionUID = UUID.randomUUID().version();
    private transient Encoder encoder;
    private transient Decoder decoder;
    private transient Retryable retryable;
    private transient Request request;
    public static PandaClientBuilder of(Request request) {
        PandaClientBuilder pandaClientBuilder = new PandaClientBuilder();
        pandaClientBuilder.request = request;
        return pandaClientBuilder;
    }

    public PandaClientBuilder encode(Encoder encoder) {
        this.encoder = encoder;
        return this;
    }
    public PandaClientBuilder decode(Decoder decoder) {
        this.decoder = decoder;
        return this;
    }
    public PandaClientBuilder retry(Retryable retryable) {
        this.retryable = retryable;
        return this;
    }

    public PandaClientBuilder and() {
        return this;
    }

    public PandaClient build() {
        return new PandaClient(this.request, this.retryable, this.encoder, this.decoder);
    }
}
