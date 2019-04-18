package com.app.ch06.io.app03.serial.nonserial;

import java.io.Serializable;

public class MyCarData {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MyCarData{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
