package com.app.topology;

import com.app.model.Person;
import com.app.model.PersonData;
import com.app.transformer.MyTransformer;
import com.app.util.Helper;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.StoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.apache.kafka.streams.state.internals.KeyValueStoreBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class PersonOneStream {

    @Bean
    public KStream<String, Person> kstreamCommoodityTrading(StreamsBuilder builder){
        Serde<String> stringSerde = Serdes.String();
        Serde<Person> personSerde = new JsonSerde(Person.class);
        Serde<PersonData> personDataSerde = new JsonSerde(PersonData.class);

        KStream<String, Person> personStream = builder.stream("t.commodity.person2", Consumed.with(stringSerde, personSerde))
                .mapValues(p-> Helper.maskCreditCard(p));

        String feedbackRatingOneStateStoreName = "feedbackRatingOneStateStore";
        KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore(feedbackRatingOneStateStoreName);
        StoreBuilder storeBuilder = Stores.keyValueStoreBuilder(storeSupplier, stringSerde, personDataSerde);
        builder.addStateStore(storeBuilder);

        ValueTransformer<Person, PersonData> transformer = new MyTransformer(feedbackRatingOneStateStoreName);
        personStream.transformValues(()->transformer, feedbackRatingOneStateStoreName).filter((k,v)->{
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@k="+k+"; v="+v);
            return true;
        }
            ).to("t.commodity.processed", Produced.with(stringSerde, personDataSerde));

        /*personStream.transformValues(new ValueTransformerSupplier<Person, String>() {
            @Override
            public ValueTransformer<Person, String> get() {
                return new ValueTransformer<Person, String>() {

                    @Override
                    public void init(ProcessorContext processorContext) {

                    }

                    @Override
                    public String transform(Person person) {
                        return person.toString();
                    }

                    @Override
                    public void close() {

                    }
                };
            }
        }).to("t.commodity.feedback-four-good", Produced.with(stringSerde, stringSerde));*/

        return personStream;
    }


}
