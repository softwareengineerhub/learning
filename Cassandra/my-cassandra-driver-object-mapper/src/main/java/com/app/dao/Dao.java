package com.app.dao;

import com.app.model.Persons;

public interface Dao {

    public void save(Persons persons);

    public Persons find(int id);

}
