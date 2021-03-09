package com.app.dao;

import com.app.model.Person;

public interface Dao {

    public void save(Person person);

    public Person find(int id);

}
