package com.app.ch04.helper;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HelperPerson {
    private String name;
    private int age;

    private PropertyChangeSupport propertyChangeSupport =
            new PropertyChangeSupport(this);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        /*if(!this.name.equals(name)){
            //mail notification
        }*/
        propertyChangeSupport.firePropertyChange("name", this.name, name);
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        propertyChangeSupport.firePropertyChange("age", this.age, age);
        this.age = age;
    }

    public void addListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}
