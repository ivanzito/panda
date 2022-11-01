package br.com.panda.client;

public interface Encoder {


    <T> T encode(String body, Class<T> clazz);
}
