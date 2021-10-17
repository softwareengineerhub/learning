package com.app;


import org.apache.avro.reflect.Nullable;

public class ReflectedCustomer {
    private String firstName;
    private String lastName;
    @Nullable
    private String nickName;

    public ReflectedCustomer(String firstName, String lastName, String nickName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    public ReflectedCustomer() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "ReflectedCustomer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
