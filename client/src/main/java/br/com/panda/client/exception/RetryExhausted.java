package br.com.panda.client.exception;

import java.io.Serializable;

public class RetryExhausted extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 85028572;

    public RetryExhausted(int quantity) {
        super(String.format("Number of retries was exhausted(%d)", quantity));
    }
}
