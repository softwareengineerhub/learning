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
import org.springframework.kafka.support.serializer.JsonSerde;
import org.apache.kafka.streams.kstream.Serialized;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FlashSaleVoteOneStream {

    @Bean
    public KStream<String, Person> kstreamFlashSaleVote(StreamsBuilder builder){
        Serde<String> stringSerde = Serdes.String();
        Serde<Long> longSerde = Serdes.Long();
        Serde<Person> personSerde = new JsonSerde(Person.class);
        Serde<PersonPattern> personPatternSerde = new JsonSerde(PersonPattern.class);
        Serde<PersonRewards> personRewardsSerde = new JsonSerde(PersonRewards.class);

        KStream<String, Person> personStream = builder.stream("t.commodity.person2", Consumed.with(stringSerde, personSerde));

        personStream.map((k,v)->new KeyValue<>(v.getAge()+"", v.getName())).to("t.commodity.flashsale.vote-user-item");



        builder.table("t.commodity.flashsale.vote-user-item", Consumed.with(stringSerde, stringSerde))
                .groupBy((k,v)->KeyValue.pair(k,v)).count().toStream()
                .mapValues(item->item.toString())
                .to("t.commodity.flashsale.vote-one-result");



        return personStream;
    }


}
