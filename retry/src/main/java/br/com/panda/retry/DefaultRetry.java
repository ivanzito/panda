package br.com.panda.retry;


import br.com.panda.client.Retryable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class DefaultRetry implements Retryable {

    public static final DefaultRetry DEFAULT_RETRY = new DefaultRetry(3);

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRetry.class);

    private final int retries;

    public DefaultRetry(int retries) {
        this.retries = retries;
    }

    @Override
    public List<Class<? extends Throwable>> retryableExceptions() {
        return List.of(Exception.class);
    }
}
