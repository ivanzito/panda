package br.com.panda.client.java11;

import br.com.panda.client.HttpMethod;
import br.com.panda.client.Request;
import br.com.panda.client.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

public class PandaRequest implements Request {

    private final HttpClient httpClient;

    public PandaRequest(Duration timeout) {
        this.httpClient = HttpClientFactory.newHttpClient(timeout);
    }

    public PandaRequest() {
        this.httpClient = HttpClientFactory.newHttpClient();
    }

    @Override
    public Response call(String uri) {
        try {
            HttpRequest request = HttpRequest.newBuilder().GET()
                    .uri(new URI(uri))
                    .build();
            return extractResponse(request);
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Response call(String uri, HttpMethod httpMethod) {
        try {
            HttpRequest request = chooseMethod(httpMethod, "")
                    .uri(new URI(uri))
                    .build();
            return extractResponse(request);
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Response call(String uri, HttpMethod httpMethod, Map<String, String> headers) {
        try {
            HttpRequest request = chooseMethod(httpMethod, "")
                    .uri(new URI(uri))
                    .build();

            return extractResponse(request);
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Response call(String uri, HttpMethod httpMethod, String body, Map<String, String> headers) {
        HttpRequest.Builder builder = chooseMethod(httpMethod, body);
        try {
            HttpRequest request = extractHeaders(builder, headers)
                    .uri(new URI(uri))
                    .build();
            return extractResponse(request);
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Response extractResponse(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<byte[]> send = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());
        return new PandaResponse(new String(send.body()), send.statusCode(), send.headers().map());
    }

    private HttpRequest.Builder chooseMethod(HttpMethod httpMethod, String body) {
        return switch (httpMethod) {
            case PUT -> HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(body == null ? "" : body));
            case POST -> HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(body == null ? "" : body));
            case DELETE -> HttpRequest.newBuilder().DELETE();
            default -> HttpRequest.newBuilder().GET();
        };
    }

    private HttpRequest.Builder extractHeaders(HttpRequest.Builder requestBuilder, Map<String, String> headers) {
        headers.forEach(requestBuilder::header);
        return requestBuilder;
    }
}
