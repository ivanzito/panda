package br.com.panda.decoder;

import br.com.panda.client.Decoder;
import br.com.panda.client.Encoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;

public class JacksonCodec implements Decoder, Encoder {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Object decode(final String value, final Type type) throws JsonProcessingException {
        return mapper.readValue(value, (JavaType) type);
    }


    @Override
    public void prettyPrinter(final Object value) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            //TODO Implementar as exceptions
        }
    }

    @Override
    public Object encode(final Object value, final Type clazz) {
        return mapper.convertValue(value, (JavaType) clazz);
    }

}
