package com.app.dao;

import com.app.model.Person;

import java.util.List;

public interface Dao {

    public void create(Person person);

    public List<Person> read();

    public void update(Person candidate, Person current);

    public void delete(String name);

    public void delete(int age);

    public void delete(String name, int age);

}
