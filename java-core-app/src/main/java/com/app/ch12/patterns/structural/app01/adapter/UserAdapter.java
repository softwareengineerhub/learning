package com.app.ch12.patterns.structural.app01.adapter;

public class UserAdapter {
    private final String name;
    private final int age;

    public UserAdapter(LegacyUser legacyUser){
        name = legacyUser.getName();
        age = Integer.parseInt(legacyUser.getUserName());
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
