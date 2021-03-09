package com.app.dao;

import com.app.model.Person;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.querybuilder.insert.Insert;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.select.SelectFrom;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.*;

public class QueryBuilderDaoImpl extends AbstractDaoImpl {

    @Override
    public void save(Person person) {
        Insert insert = insertInto("persons")
                .value("name", literal(person.getName()))
                .value("age", literal(person.getAge()))
                .value("id", literal(person.getId()));

        SimpleStatement simpleStatement = insert.build();
        cqlSession.execute(simpleStatement);
    }

    @Override
    public Person find(int id) {
        Select select = selectFrom("persons").all()
                .whereColumn("id").isEqualTo(literal(id));

        SimpleStatement simpleStatement = select.build();

        ResultSet rs = cqlSession.execute(simpleStatement);
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
