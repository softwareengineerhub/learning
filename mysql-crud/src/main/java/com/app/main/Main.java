package com.app.main;

import com.app.dao.Dao;
import com.app.dao.DaoImpl;
import com.app.model.Person;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Dao dao = new DaoImpl();
        Person person = new Person();
        person.setName("P1");
        person.setAge(1);
        dao.create(person);
        List<Person> persons =  dao.read();
        System.out.println(persons);
        Person candidate = new Person();
        candidate.setAge(-1);
        candidate.setName("UpdatedP1");
        dao.update(candidate, person);

        dao.delete("Denis");
        dao.delete(-1);

        dao.read();



    }
}
