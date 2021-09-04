package com.app.serializer.serde;

import com.app.serializer.CustomJsonDeserializer;
import com.app.serializer.CustomJsonSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomJsonSerde<T> implements Serde<T> {
    private CustomJsonSerializer<T> customJsonSerializer;
    private CustomJsonDeserializer<T> customJsonDeserializer;

    public CustomJsonSerde(CustomJsonSerializer<T> customJsonSerializer, CustomJsonDeserializer<T> customJsonDeserializer) {
        this.customJsonSerializer = customJsonSerializer;
        this.customJsonDeserializer = customJsonDeserializer;
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public void close() {

    }

    @Override
    public Serializer<T> serializer() {
        return customJsonSerializer;
    }

    @Override
    public Deserializer<T> deserializer() {
        return customJsonDeserializer;
    }
}
