package br.com.panda.client;

import java.util.List;
import java.util.Map;

public interface Request {

    HttpMethod method();

    Map<String, List<String>> headers();

    String body();

    Response call(String uri);
}
