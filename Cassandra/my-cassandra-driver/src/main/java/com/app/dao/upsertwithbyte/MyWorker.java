package com.app.dao.upsertwithbyte;

import java.io.Serializable;

public class MyWorker implements Serializable {
    private static final long serialVersionUID = 1;
    private String name;
    private int age;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
        return "MyWorker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
