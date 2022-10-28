package br.com.panda.client;

import java.io.IOException;
import java.lang.reflect.Type;

public interface Decoder {

    Object decode(String value, Type type) throws IOException;
}
