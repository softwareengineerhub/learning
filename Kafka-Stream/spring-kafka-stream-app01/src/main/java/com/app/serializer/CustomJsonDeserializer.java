package com.app.serializer;

import com.app.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.DeserializationException;

import java.util.Map;

public class CustomJsonDeserializer<T> implements Deserializer<T> {
    private ObjectMapper mapper = new ObjectMapper();
    private Class<T> deserializedClass;

    public CustomJsonDeserializer(Class<T> deserializedClass) {
        this.deserializedClass = deserializedClass;
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        try {
            return mapper.readValue(bytes, deserializedClass);
        } catch (Exception ex) {
            throw new SerializationException(ex);
        }
    }

    @Override
    public void close() {

    }
}
