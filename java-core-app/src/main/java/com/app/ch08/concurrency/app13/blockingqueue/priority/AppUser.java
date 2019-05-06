package com.app.ch08.concurrency.app13.blockingqueue.priority;

public class AppUser implements Comparable<AppUser> {
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

    @Override
    public int compareTo(AppUser other) {
        if(other.getAge()==getAge()){
            if(other.name==null && name==null){
                return 0;
            }
            if(other.getName()!=null){
                return -1*other.getName().compareTo(name);
            }
            return -1*1;
        }
        return -1*(other.getAge()-getAge());
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
