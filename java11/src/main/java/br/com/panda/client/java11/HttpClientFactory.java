package br.com.panda.client.java11;

import java.net.http.HttpClient;
import java.time.Duration;

public class HttpClientFactory {

    public static HttpClient newHttpClient(Duration timeout) {
        return HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .connectTimeout(timeout)
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public static HttpClient newHttpClient() {
        return HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

}
