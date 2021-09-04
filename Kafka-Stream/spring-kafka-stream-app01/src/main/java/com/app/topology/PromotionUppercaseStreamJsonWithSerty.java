package com.app.topology;

import com.app.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class PromotionUppercaseStreamJsonWithSerty {

    private ObjectMapper mapper = new ObjectMapper();

    @Bean
    public KStream<String, Person> kstreamPromotionUppercaseJsonSerty(StreamsBuilder builder) {

        Serde stringSerdes = Serdes.String();
        Serde jsonSerde = new JsonSerde(Person.class);

        KStream<String, Person> sourceStream = builder.stream("t.commodity.promotion-json-serty", Consumed.with(stringSerdes, jsonSerde));
        KStream<String, String> uppercaseStream = sourceStream.mapValues(s -> upperCaseValue(s));
        uppercaseStream.to("t.commodity.promotion-uppercase");

        //for debugging
        // sourceStream.print(Printed.<String,String>toSysOut().withLabel("Original Stream"));
        // uppercaseStream.print(Printed.<String,String>toSysOut().withLabel("Uppercase Stream"));

        return sourceStream;
    }

    private String upperCaseValue(Person person) {
        try {
            person.setName(person.getName().toUpperCase());
            return mapper.writeValueAsString(person);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
