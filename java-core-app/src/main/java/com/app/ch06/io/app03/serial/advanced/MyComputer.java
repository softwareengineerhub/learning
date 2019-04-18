package com.app.ch06.io.app03.serial.advanced;

public class MyComputer {
    private String manufactor;
    private int price;

    public MyComputer() {
        System.out.println("MyComputer()");
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MyComputer{" +
                "manufactor='" + manufactor + '\'' +
                ", price=" + price +
                '}';
    }
}
