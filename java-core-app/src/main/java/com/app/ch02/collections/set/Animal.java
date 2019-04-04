package com.app.ch02.collections.set;

public class Animal {
    private int age;
    private String name;

    public Animal(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int hashCode(){
        return age+name==null ? 0 : name.hashCode();
    }

    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj==this){
            return true;
        }
        if(obj.getClass()==Animal.class){
            Animal a = (Animal) obj;
            if(a.age!=age){
                return false;
            }
            if(a.name!=null){
                return a.name.equals(name);
            }
            if(name!=null){
                return name.equals(a.name);
            }
            return false;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Animal{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
