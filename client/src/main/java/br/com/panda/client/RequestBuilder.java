package br.com.panda.client;

import java.util.List;
import java.util.Map;

public class RequestBuilder {

    private HttpMethod method;
    private Map<String, List<String>> headers;
    private String body;

    private RequestBuilder() {}

    public static RequestBuilder of() {
        return new RequestBuilder();
    }

    public RequestBuilder method(HttpMethod method) {
        this.method = method;
        return this;
    }

    public RequestBuilder headers(Map<String, List<String>> headers) {
        this.headers = headers;
        return this;
    }

    public RequestBuilder body(String body) {
        this.body = body;
        return this;
    }

    public Request build() {
        return new Request() {
            @Override
            public HttpMethod method() {
                return method;
            }

            @Override
            public Map<String, List<String>> headers() {
                return headers;
            }

            @Override
            public String body() {
                return body;
            }

            @Override
            public Response call(String uri) {
                return null;
            }
        };
    }
}
