package com.app.topology;

import com.app.model.Person;
import com.app.model.PersonPattern;
import com.app.model.PersonRewards;
import com.app.util.Helper;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaStreamBrancher;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.Base64;
import java.util.function.Consumer;

@Configuration
public class OrderTwoStream {

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
        KStream<String, PersonPattern> personPatternStreamArray = personStream.mapValues(p->Helper.mapToPersonPattern(p));

        new KafkaStreamBrancher<String, PersonPattern>().branch(new Predicate<String, PersonPattern>() {
            @Override
            public boolean test(String s, PersonPattern personPattern) {
                return personPattern.getPatternName().length()>=5;
            }
        }, new Consumer<KStream<String, PersonPattern>>() {
            @Override
            public void accept(KStream<String, PersonPattern> stringPersonPatternKStream) {
                stringPersonPatternKStream.to("t.commodity.plastic", Produced.with(stringSerde, personPatternSerde));
            }
        }).defaultBranch(new Consumer<KStream<String, PersonPattern>>() {
            @Override
            public void accept(KStream<String, PersonPattern> stringPersonPatternKStream) {
                stringPersonPatternKStream.to("t.commodity.nonplastic", Produced.with(stringSerde, personPatternSerde));
            }
        }).onTopOf(personStream.mapValues(p->Helper.mapToPersonPattern(p)));


        //2nd sink stream to rewards; filter only large
        KStream<String, PersonRewards> personRewardsStream = personStream.map(new KeyValueMapper<String, Person, KeyValue<? extends String, ? extends PersonRewards>>() {

            @Override
            public KeyValue<? extends String, ? extends PersonRewards> apply(String s, Person person) {
                return new KeyValue<>(s+"map()", new PersonRewards(person));
            }
        });

        personRewardsStream.to("t.commodity.order-one2", Produced.with(stringSerde, personRewardsSerde));

        //3rd sink stream to storage
        //no transformation
        KStream<String, String> base64stream = personStream.mapValues(v-> Base64.getEncoder().encodeToString(v.toString().getBytes()));
        base64stream.to("t.commodity.orderstorage-one2", Produced.with(stringSerde, stringSerde));

        return personStream;
    }


}
