package com.app.ch04.helper;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyListenerA implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("MyListenerA");
        String name=evt.getPropertyName();
        Object old=evt.getOldValue();
        Object newValue =evt.getNewValue();
        System.out.println("name="+name);
        System.out.println("old="+old);
        System.out.println("newValue="+newValue);
    }
}
