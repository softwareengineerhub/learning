package com.app.util;

import com.app.model.Person;
import com.app.model.PersonPattern;
import com.app.model.PersonRewards;
import org.apache.kafka.streams.kstream.Predicate;

public class Helper {

    public static Person maskCreditCard(Person original) {
        System.out.println("##########@start helper1");
        Person person = new Person();
        person.setName(original.getName());
        person.setAge(original.getAge());
        person.setCreditCard(original.getCreditCard());
        String s = person.getCreditCard();
        if(s!=null){
            String res = "";
            for(int i=0;i<s.length();i++){
                res+="*";
            }
            person.setCreditCard(res);
        }
        System.out.println("@finish helper1; p="+person);
        return person;
    }

    public static PersonPattern mapToPersonPattern(Person person){
        System.out.println("@@@@@@@@@@@@2here");
        return new PersonPattern(person);
    }


    public static PersonRewards mapToPersonRewards(Person person){
        return new PersonRewards(person);
    }

    public static void delay(long delay){
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis()-start<=delay){
        }
    }

}
