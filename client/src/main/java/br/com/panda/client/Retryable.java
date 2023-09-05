package br.com.panda.client;

import java.util.List;

public interface Retryable {
    default int retries() {
        return 3;
    }

    List<Class<? extends Throwable>> retryableExceptions();

    void onRequestError(Exception exception);

    boolean shouldRetry();

}
