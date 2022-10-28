package br.com.panda.client;

import java.lang.reflect.Type;

public interface Encoder {

    void prettyPrinter(Object value);

    Object encode(Object object, Type value);
}
