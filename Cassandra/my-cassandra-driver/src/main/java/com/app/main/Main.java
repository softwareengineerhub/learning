package com.app.main;

import com.app.dao.*;
import com.app.model.Person;

public class Main {

    public static void main(String[] args) {
        Dao dao = new SimpleDaoImpl();
        Person person = new Person();
        person.setAge(1);
        person.setName("Name1");
        person.setId(1);
        dao.save(person);
        System.out.println("------------------");
        Person res = dao.find(1);
        System.out.println(res);
    }

}
