package com.app.ch09.xml.myws2;

public class MyCar {
    private int price;
    private String model;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "MyCar{" +
                "price=" + price +
                ", model='" + model + '\'' +
                '}';
    }
}
