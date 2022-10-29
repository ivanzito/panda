package br.com.panda.client;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public interface Response {

    String body();

    <T> T decode(Class<T> clazz);

    int statusCode();

    Decoder decoder();

    Map<String, List<String>> headers();
}
