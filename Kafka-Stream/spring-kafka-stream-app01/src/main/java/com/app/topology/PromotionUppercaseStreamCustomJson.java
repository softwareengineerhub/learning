package com.app.topology;

import com.app.model.Person;
import com.app.serializer.serde.PersonSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class PromotionUppercaseStreamCustomJson {

    @Bean
    public KStream<String, Person> kstreamPromotionUppercaseCustomJson(StreamsBuilder builder) {

        Serde stringSerdes = Serdes.String();
        Serde personSerde = new PersonSerde();

        KStream<String, Person> sourceStream = builder.stream("t.commodity.promotion-json-custom2", Consumed.with(stringSerdes, personSerde));
        KStream<String, Person> uppercaseStream = sourceStream.mapValues(s -> upperCaseValue(s));
        uppercaseStream.to("t.commodity.promotion-uppercase-custom");

        //for debugging
        // sourceStream.print(Printed.<String,String>toSysOut().withLabel("Original Stream"));
        // uppercaseStream.print(Printed.<String,String>toSysOut().withLabel("Uppercase Stream"));

        return sourceStream;
    }

    private Person upperCaseValue(Person person) {
        try {
            person.setName(person.getName().toUpperCase());
            return person;
        } catch (Exception ex) {
            //throw new RuntimeException(ex);
            return new Person();
        }
    }
}
