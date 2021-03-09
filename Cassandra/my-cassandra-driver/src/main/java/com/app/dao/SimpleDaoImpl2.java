package com.app.dao;

import com.app.model.Person;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

import java.util.Arrays;

public class SimpleDaoImpl2 extends AbstractDaoImpl {

    @Override
    public void save(Person person) {
        //String cql = String.format("INSERT INTO persons(id, name, age) VALUES(%s,'%s', %s)", person.getId(), person.getName(), person.getAge());
        SimpleStatement simpleStatement = SimpleStatement.newInstance("INSERT INTO persons(id, name, age) VALUES(?,?,?)")
                .setPositionalValues(Arrays.asList(person.getId(), person.getName(), person.getAge()));
        cqlSession.execute(simpleStatement);
    }

    @Override
    public Person find(int id) {
        //String cql = String.format("SELECT * FROM persons WHERE id=%s", id);
        SimpleStatement simpleStatement = SimpleStatement.builder("SELECT * FROM persons WHERE id=?").addPositionalValue(id).build();

        ResultSet rs = cqlSession.execute(simpleStatement);
        Person person = null;
        for(Row row: rs){
            person = new Person();
            //row.getInt("id");
            String name = row.getString("name");
            int age = row.getInt("age");
            person.setName(name);
            person.setAge(age);
            person.setId(id);
        }
        return person;
    }
}
