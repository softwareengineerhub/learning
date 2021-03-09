package com.app.dao;

import com.app.model.Person;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class SimpleDaoImpl extends AbstractDaoImpl {

    @Override
    public void save(Person person) {
        String cql = String.format("INSERT INTO persons(id, name, age) VALUES(%s,'%s', %s)", person.getId(), person.getName(), person.getAge());
        cqlSession.execute(cql);
    }

    @Override
    public Person find(int id) {
        String cql = String.format("SELECT * FROM persons WHERE id=%s", id);
        ResultSet rs = cqlSession.execute(cql);
        Person person = null;
        for(Row row: rs){
            person = new Person();
            //row.getInt("id");
            String name = row.getString("name");
            int age = row.getInt("age");
            person.setName(name);
            person.setAge(age);
        }
        return person;
    }
}
