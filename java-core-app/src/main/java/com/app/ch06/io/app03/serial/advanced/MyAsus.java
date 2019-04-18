package com.app.ch06.io.app03.serial.advanced;

import java.io.Serializable;

public class MyAsus extends MyComputer implements Serializable {

    //private transient String modelName;
    private String modelName;

    public MyAsus() {
        System.out.println("MyAsus()");
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public String toString() {
        return "MyAsus{" +
                "modelName='" + modelName + '\'' +
                '}'+super.toString();
    }
}
