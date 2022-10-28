package br.com.panda.client;

import java.io.IOException;
import java.util.List;

public interface Retryable {

    Response retry(Request request, String uri) throws IOException, InterruptedException;

    List<Class<? extends Throwable>> retryableExceptions();

}
