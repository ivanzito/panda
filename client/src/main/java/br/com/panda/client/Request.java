package br.com.panda.client;

import java.util.Map;

public interface Request {

    Response call(String uri);
    Response call(String uri, HttpMethod httpMethod);
    Response call(String uri, HttpMethod httpMethod, Map<String, String> headers);
    Response call(String uri, HttpMethod httpMethod, String body, Map<String, String> headers);
}
