package com.app.ch06.io.app03.serial;

import java.io.Serializable;

public class MyCup  implements Serializable {
    private String name;
    private int age;

    private static final long serialVersionUID = 1L;

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
        return "MyCup{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
