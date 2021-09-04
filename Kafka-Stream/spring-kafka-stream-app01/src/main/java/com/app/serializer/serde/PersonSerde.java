package com.app.serializer.serde;

import com.app.model.Person;
import com.app.serializer.CustomJsonDeserializer;
import com.app.serializer.CustomJsonSerializer;

public class PersonSerde extends CustomJsonSerde<Person> {

    public PersonSerde() {
        super(new CustomJsonSerializer<>(), new CustomJsonDeserializer<>(Person.class));
    }
}
