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

@Configuration
public class PersonOneStream {

    @Bean
    public KStream<String, Person> kstreamCommoodityTrading(StreamsBuilder builder){
        Serde<String> stringSerde = Serdes.String();
        Serde<Person> personSerde = new JsonSerde(Person.class);
        Serde<PersonPattern> personPatternSerde = new JsonSerde(PersonPattern.class);
        Serde<PersonRewards> personRewardsSerde = new JsonSerde(PersonRewards.class);
        //JsonSerde<PersonPattern> personPatternSerde = new JsonSerde<>(PersonPattern.class);
        //JsonSerde<PersonRewards> personRewardsSerde = new JsonSerde<>(PersonRewards.class);

        KStream<String, Person> personStream = builder.stream("t.commodity.person2", Consumed.with(stringSerde, personSerde))
                .mapValues(p-> Helper.maskCreditCard(p));

        //1st sink stream to pattern
        KStream<String, PersonPattern> personPatternStream = personStream.mapValues(p->Helper.mapToPersonPattern(p));
        personPatternStream.to("t.commodity.person-one2", Produced.with(stringSerde, personPatternSerde));


        //2nd sink stream to rewards; filter only large
        KStream<String, PersonRewards> personRewardsStream = personStream.filter(new Predicate<String, Person>() {
            @Override
            public boolean test(String s, Person person) {
                //Helper.delay(5*1000);
                return person.getName().length()>5;
            }
        }).mapValues(p -> Helper.mapToPersonRewards(p));
        personRewardsStream.to("t.commodity.reward-one2", Produced.with(stringSerde, personRewardsSerde));

        //3rd sink stream to storage
        //no transformation
        personStream.to("t.commodity.storage-one2", Produced.with(stringSerde, personSerde));

        return personStream;
    }


}