package com.app.dao;

import com.app.model.Person;
import com.datastax.oss.driver.api.core.cql.*;

import java.util.Arrays;

public class PreparedStatementDaoImpl extends AbstractDaoImpl {

    @Override
    public void save(Person person) {
        PreparedStatement ps = cqlSession.prepare("INSERT INTO persons(id, name, age) VALUES(?,?,?)");
        BoundStatement bs = ps.bind().setInt("id", person.getId())
                .setString("name", person.getName())
                .setInt("age", person.getAge());
        cqlSession.execute(bs);
    }

    @Override
    public Person find(int id) {
        PreparedStatement ps = cqlSession.prepare("SELECT * FROM persons WHERE id=?");
        BoundStatement bs = ps.bind().setInt("id", id);
        ResultSet rs = cqlSession.execute(bs);
        Person person = null;
        for(Row row: rs){
            person = new Person();
            String name = row.getString("name");
            int age = row.getInt("age");
            person.setName(name);
            person.setAge(age);
            person.setId(id);
        }
        return person;
    }
}
