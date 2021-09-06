package com.app.topology;

import com.app.model.Person;
import com.app.model.PersonPattern;
import com.app.model.PersonRewards;
import com.app.util.Helper;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PersonOneStream {

    @Bean
    public KStream<String, Person> kstreamCommoodityTrading(StreamsBuilder builder){
        Serde<String> stringSerde = Serdes.String();
        Serde<Long> longSerde = Serdes.Long();
        Serde<Person> personSerde = new JsonSerde(Person.class);
        Serde<PersonPattern> personPatternSerde = new JsonSerde(PersonPattern.class);
        Serde<PersonRewards> personRewardsSerde = new JsonSerde(PersonRewards.class);
        //JsonSerde<PersonPattern> personPatternSerde = new JsonSerde<>(PersonPattern.class);
        //JsonSerde<PersonRewards> personRewardsSerde = new JsonSerde<>(PersonRewards.class);

        KStream<String, Person> personStream = builder.stream("t.commodity.person2", Consumed.with(stringSerde, personSerde))
                .mapValues(p-> Helper.maskCreditCard(p));

        KStream<String, Person>[] streams = personStream.branch(new Predicate<String, Person>() {

            @Override
            public boolean test(String s, Person person) {
                return person.getAge() >= 20;
            }
        }, new Predicate<String, Person>() {
            @Override
            public boolean test(String s, Person person) {
                return person.getAge() < 20;
            }
        });
        streams[0].to("t.commodity.feedback-four-good", Produced.with(stringSerde, personSerde));
        streams[0].groupByKey().count().toStream().filter((s,c)->{
            System.out.println("s="+s+"; c="+c);
            return true;
        }).mapValues((s,c)->c+"").to("t.commodity.feedback-four-good-count", Produced.with(stringSerde, stringSerde));



        //streams[1].through("t.commodity.feedback-four-bad", Produced.with(stringSerde, personSerde)).groupByKey().count().toStream().mapValues((s, c)->c+"").to("t.commodity.feedback-four-bad-count");

        streams[1].through("t.commodity.feedback-four-bad", Produced.with(stringSerde, personSerde)).groupBy((k,v)->"A").count().toStream().mapValues((s, c)->c+"").to("t.commodity.feedback-four-bad-count");


        /*streams[1].through("t.commodity.feedback-four-bad", Produced.with(stringSerde, personSerde)).
                groupBy((k,v)->{
                    System.out.println("k="+k+"; v="+v);
                    return v;
                }).count().toStream().mapValues((s, c)->c+"").to("t.commodity.feedback-four-bad-count");*/



        return personStream;
    }


}
