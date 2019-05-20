package com.app.ch09.xml.common;

public class ComputerData {

    private String name;
    private int age;

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

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() == ComputerData.class) {
            ComputerData computerData = (ComputerData) other;
            if (computerData.age == age) {
                if (computerData.name == null && name == null) {
                    return true;
                }
                if (computerData.name != null) {
                    return computerData.name.equals(name);
                }
                if (name != null) {
                    return name.equals(computerData.name);
                }
            }
        }
        return false;
    }

    public int hashCode() {
        return age + name == null ? 0 : name.hashCode();
    }
}
