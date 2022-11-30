package br.com.panda.client;


import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.isNull;

class PandaClientProxy extends PandaClient {

    private final Retryable retryable;
    private final Encoder encoder;
    private final Decoder decoder;

    public PandaClientProxy(Request request, Retryable retryable, Encoder encoder, Decoder decoder) {
        super(request);
        this.retryable = retryable;
        this.encoder = encoder;
        this.decoder = decoder;

    }

    public Response request(String uri, int retry) {
        AtomicInteger atomicInteger = new AtomicInteger(retry);

        if (atomicInteger.incrementAndGet() <= this.retryable.retries()) {
            try {
                return super.request(uri);
            } catch (Exception e) {
                if (isNull(getaClass())) {
                    throw e;
                }
                super.request(uri);
            }
        }
        return super.request(uri);
    }

    private Class<? extends Throwable> getaClass() {
        return super.getRetryable().retryableExceptions()
                .stream()
                .filter(Objects::nonNull)
                .filter(ex -> this.getRetryable().retryableExceptions().contains(ex))
                .findFirst()
                .orElse(null);
    }
}
