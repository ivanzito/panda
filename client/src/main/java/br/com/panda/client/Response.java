package br.com.panda.client;

import java.util.List;
import java.util.Map;

public interface Response {

    String body();

    <T> T decode(Class<T> clazz);

    Decoder decoder();

    int statusCode();

    Map<String, List<String>> headers();
}
