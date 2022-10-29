package br.com.panda.client;

import java.util.List;
import java.util.Map;

public interface Request {

    Response call(String uri);
    Response call(String uri, HttpMethod httpMethod);
    Response call(String uri, HttpMethod httpMethod, Map<String, List<String>> headers);
    Response call(String uri, HttpMethod httpMethod, String body, Map<String, List<String>> headers);
}
