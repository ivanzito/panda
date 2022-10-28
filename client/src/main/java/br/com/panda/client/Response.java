package br.com.panda.client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public interface Response {

    String body();

    String body(Type type) throws IOException;

    int statusCode();

    Decoder decoder();

    Map<String, List<String>> headers();
}
