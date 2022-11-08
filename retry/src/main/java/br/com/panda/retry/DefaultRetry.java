package br.com.panda.retry;


import br.com.panda.client.Request;
import br.com.panda.client.Response;
import br.com.panda.client.Retryable;
import br.com.panda.client.exception.RetryException;
import br.com.panda.client.exception.RetryExhausted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.Objects.nonNull;

public class DefaultRetry implements Retryable {

    public static final DefaultRetry DEFAULT_RETRY = new DefaultRetry(3);

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRetry.class);

    private final int retries;

    public DefaultRetry(int retries) {
        this.retries = retries;
    }

    @Override
    public Response retry(Request request, String uri, String body) {
        return this.retry(request, uri, body, 1);
    }

    @Override
    public List<Class<? extends Throwable>> retryableExceptions() {
        return List.of(Exception.class);
    }


    private Response retry(final Request request, String uri, String body, int retryNumber) {

        if (retryNumber > this.retries) {
            throw new RetryExhausted(this.retries);
        }

        Response response = null;
        try {
            response = request.call(uri);
            if (!isStatusCode2xx(response.statusCode())) {
                LOGGER.warn("Occurred something wrong during the request, trying by {} time", retryNumber);
                return this.retry(request, uri, body, ++retryNumber);
            }
        } catch (Exception ex) {
            if (!this.retryableExceptions().contains(ex.getClass())) {
                treatException(response, retryNumber, ex);
            }
            if (response == null || !isStatusCode2xx(response.statusCode()) && retryNumber <= retries) {
                LOGGER.warn("Could not possible execute the request. Trying by {} time", retryNumber);
                return this.retry(request, uri, body, ++retryNumber);
            }
        }
        return response;
    }

    private boolean isStatusCode2xx(int status) {
        return status >= 200 && status < 300;
    }

    private void treatException(Response response, int retryNumber, Exception exception) {
        if (nonNull(response)) {
            throw new RetryException(retryNumber, response.statusCode(), exception);
        }
        throw new RetryException(retryNumber, exception);
    }
}
