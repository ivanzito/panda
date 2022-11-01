package br.com.panda.decoder;

import br.com.panda.client.Decoder;
import br.com.panda.client.Encoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonCodec implements Decoder, Encoder {

    private String test = null;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T decode(final String value, final Class<T> clazz) {
        System.out.println(test.toCharArray()[10]);
        try {
            return mapper.readValue(value, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String prettyPrinter(final Object value) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            //TODO Implementar as exceptions
        }
        return "";
    }

    @Override
    public <T> T encode(String body, Class<T> clazz) {
        return mapper.convertValue(body, (JavaType) clazz.getGenericSuperclass());
    }
}
