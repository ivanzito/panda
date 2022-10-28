package br.com.panda.client.java11;

import br.com.panda.client.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class DefaultRequest implements Request {

    private String body;
    private Duration timeout = Duration.ofSeconds(10);

    private final HttpClient httpClient;
    private final HttpMethod method;
    private final Map<String, List<String>> headers;

    public DefaultRequest(HttpMethod httpMethod, Duration timeout, String body, Map<String, List<String>> headers) {
        this.httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .connectTimeout(timeout)
            .version(HttpClient.Version.HTTP_2)
            .build();
        this.timeout = timeout;
        this.method = httpMethod;
        this.body = body;
        this.headers = headers;
    }

    private DefaultRequest(HttpMethod httpMethod, Duration timeout, Map<String, List<String>> headers) {
        this.httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .connectTimeout(timeout)
                .version(HttpClient.Version.HTTP_2)
                .build();
        this.headers = headers;
        this.method = httpMethod;
        this.timeout = timeout;
    }

    public DefaultRequest(HttpMethod httpMethod, Map<String, List<String>> headers) {
        this.httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .version(HttpClient.Version.HTTP_2)
                .build();
        this.headers = headers;
        this.method = httpMethod;
    }

    @Override
    public Response call(String uri) {
        try {
//            if (Objects.nonNull(retryable)) {
//                return retryable.retry(this, uri);
//            }
            return extractResponse(uri);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Response extractResponse(String uri) throws IOException, InterruptedException {
        HttpResponse<byte[]> send = httpClient.send(defaultHttpRequest(uri), HttpResponse.BodyHandlers.ofByteArray());
        return new DefaultResponse(new String(send.body()), send.statusCode(), send.headers().map());
    }

    private HttpRequest defaultHttpRequest(String uri) {

            HttpRequest.Builder builder = switch (this.method()) {
                case PUT -> HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(this.body()));
                case POST -> HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(this.body()));
                case DELETE -> HttpRequest.newBuilder().DELETE();
                default -> HttpRequest.newBuilder().GET();
            };

        try {
            return headers(builder)
                .uri(new URI(uri))
                .timeout(timeout)
                .build();
        } catch (URISyntaxException uriSyntaxException) {
            throw new RuntimeException(uriSyntaxException);
        }

    }

    private HttpRequest.Builder headers(HttpRequest.Builder headers) {
        for (Map.Entry<String, List<String>> params : this.headers().entrySet()) {
            for (String value : params.getValue()) {
                headers.setHeader(params.getKey(), value);
            }
        }
        return headers;
    }

    @Override
    public HttpMethod method() {
        return this.method;
    }

    @Override
    public String body() {
        return this.body;
    }

    @Override
    public Map<String, List<String>> headers() {
        return this.headers;
    }

}
