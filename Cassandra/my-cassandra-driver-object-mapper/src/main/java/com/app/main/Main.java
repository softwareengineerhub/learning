package com.app.main;

import com.app.dao.*;
import com.app.model.Persons;

public class Main {

    public static void main(String[] args) {
        Dao dao = new SimpleDaoImpl();
        Persons persons = new Persons();
        persons.setAge(1);
        persons.setName("Name1");
        persons.setId(1);
        dao.save(persons);
        System.out.println("------------------");
        Persons res = dao.find(1);
        System.out.println(res);
    }

}
