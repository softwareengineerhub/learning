package com.app.topology;

import com.app.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PromotionUppercaseStreamJson {

    private ObjectMapper mapper = new ObjectMapper();

    @Bean
    public KStream<String, String> kstreamPromotionUppercaseJson(StreamsBuilder builder) {
        KStream<String, String> sourceStream = builder.stream("t.commodity.promotion-json", Consumed.with(Serdes.String(), Serdes.String()));
        KStream<String, String> uppercaseStream = sourceStream.mapValues(s -> upperCaseValue(s));
        uppercaseStream.to("t.commodity.promotion-uppercase");

        //for debugging
        // sourceStream.print(Printed.<String,String>toSysOut().withLabel("Original Stream"));
        // uppercaseStream.print(Printed.<String,String>toSysOut().withLabel("Uppercase Stream"));

        return sourceStream;
    }

    private String upperCaseValue(String text) {
        try {
            Person person = mapper.readValue(text, Person.class);
            person.setName(person.getName().toUpperCase());
            return mapper.writeValueAsString(person);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
