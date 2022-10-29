package br.com.panda.client;

public interface Decoder {

    String prettyPrinter(Object value);

    <T> T decode(final String value, final Class<T> clazz);
}
