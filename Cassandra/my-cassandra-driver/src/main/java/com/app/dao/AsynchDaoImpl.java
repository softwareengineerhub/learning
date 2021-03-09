package com.app.dao;

import com.app.model.Person;
import com.datastax.oss.driver.api.core.cql.AsyncResultSet;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

import java.util.Arrays;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class AsynchDaoImpl extends AbstractDaoImpl {

    @Override
    public void save(Person person) {
        try {
            SimpleStatement simpleStatement = SimpleStatement.newInstance("INSERT INTO persons(id, name, age) VALUES(?,?,?)")
                    .setPositionalValues(Arrays.asList(person.getId(), person.getName(), person.getAge()));
            cqlSession.executeAsync(simpleStatement).toCompletableFuture().get();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Person find(int id) {
        try {
            SimpleStatement simpleStatement = SimpleStatement.builder("SELECT * FROM persons WHERE id=?").addPositionalValue(id).build();

            CompletionStage<AsyncResultSet> result = cqlSession.executeAsync(simpleStatement);

            Function<AsyncResultSet, Person> f = new Function<AsyncResultSet, Person>() {

                @Override
                public Person apply(AsyncResultSet asyncResultSet) {
                    Person person = null;
                    for (Row row : asyncResultSet.currentPage()) {
                        person = new Person();
                        String name = row.getString("name");
                        int age = row.getInt("age");
                        person.setName(name);
                        person.setAge(age);
                        person.setId(id);
                    }
                    return person;
                }
            };

            return result.thenApply(f).toCompletableFuture().get();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
