package br.com.panda.client.exception;

public class RetryException extends RuntimeException {

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
