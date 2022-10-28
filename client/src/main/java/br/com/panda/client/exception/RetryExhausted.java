package br.com.panda.client.exception;

public class RetryExhausted extends RuntimeException {

    public RetryExhausted(int quantity) {
        super(String.format("Number of retries was exhausted(%d)", quantity));
    }
}
