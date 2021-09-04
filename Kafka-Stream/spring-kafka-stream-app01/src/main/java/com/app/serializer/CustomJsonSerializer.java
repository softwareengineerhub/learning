package com.app.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import javax.sql.rowset.serial.SerialException;
import java.util.Map;

public class CustomJsonSerializer<T> implements Serializer<T> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String topic, T data) {
        try {
            return mapper.writeValueAsBytes(data);
        } catch (Exception ex) {
            throw new SerializationException(ex);
        }
    }

    @Override
    public void close() {

    }
}
