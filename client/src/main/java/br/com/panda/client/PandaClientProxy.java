package br.com.panda.client;


import java.io.Serial;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.isNull;

class PandaClientProxy extends PandaClient {

    @Serial
    private static final long serialVersionUID = UUID.randomUUID().version();

    public PandaClientProxy(Request request, Retryable retryable, Encoder encoder, Decoder decoder) {
        super(request, retryable, encoder, decoder);
    }

    public Response request(String uri, int retry) {
        AtomicInteger atomicInteger = new AtomicInteger(retry);

        if (atomicInteger.incrementAndGet() <= super.getRetry().retries()) {
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
        return super.getRetry().retryableExceptions()
                .stream()
                .filter(Objects::nonNull)
                .filter(ex -> this.getRetry().retryableExceptions().contains(ex))
                .findFirst()
                .orElse(null);
    }
}
