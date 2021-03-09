package com.app.model;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.NamingStrategy;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import static com.datastax.oss.driver.api.mapper.entity.naming.NamingConvention.SNAKE_CASE_INSENSITIVE;


//reservations_by_confirmation
@Entity(defaultKeyspace = "mydb")
//@NamingStrategy(convention = SNAKE_CASE_INSENSITIVE)
public class Persons {

    @PartitionKey
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
