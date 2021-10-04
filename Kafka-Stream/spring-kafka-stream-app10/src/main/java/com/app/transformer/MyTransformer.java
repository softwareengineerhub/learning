package com.app.transformer;

import com.app.model.Person;
import com.app.model.PersonData;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.StateStore;
import org.apache.kafka.streams.state.KeyValueStore;

public class MyTransformer implements ValueTransformer<Person, PersonData> {
    private ProcessorContext processorContext;
    private final String stateStoreName;
    private StateStore ratingStateStore;

    public MyTransformer(String stateStoreName) {
        this.stateStoreName = stateStoreName;
    }

    @Override
    public void init(ProcessorContext processorContext) {
        this.processorContext = processorContext;
        ratingStateStore = (KeyValueStore<String, PersonData>) this.processorContext.getStateStore(stateStoreName);
    }

    @Override
    public PersonData transform(Person person) {
        String key = person.getName();
        System.out.println("key="+key);
        PersonData current = ratingStateStore.get(person.getName());
        if(current==null){
            current = new PersonData();
        }
        current.setCount(current.getCount()+1);
        current.setSum(current.getSum()+person.getAge());
        current.setAvg(current.getSum()/current.getCount());
        System.out.println("ratingStateStore="+ratingStateStore);
        ratingStateStore.get(key);
       // ratingStateStore.putIfAbsent(person.getName(), current);
        return current;
    }

    @Override
    public void close() {

    }
}
