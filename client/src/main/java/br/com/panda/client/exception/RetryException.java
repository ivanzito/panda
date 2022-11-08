package br.com.panda.client.exception;

import java.io.Serializable;

public class RetryException extends RuntimeException implements Serializable {


    private static final long serialVersionUID = 5254353245243534L;

    private final String message;
    private final int retryNumber;
    private final Exception cause;
    private int statusCode;

    public RetryException(int retryNumber, int statusCode, Exception cause) {
        this.message = "Occurred the following exception during http request: " + cause.getClass().getName();
        this.retryNumber = retryNumber;
        this.cause = cause;
        this.statusCode = statusCode;
    }


    public RetryException(int retryNumber, Exception cause) {
        this.message = "Occurred the following exception during http request: " + cause.getClass().getName();
        this.retryNumber = retryNumber;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getRetryNumber() {
        return retryNumber;
    }

    public Exception getCause() {
        return cause;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
