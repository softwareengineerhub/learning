package com.app.ch06.io.app03.serial.nonserial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class MyGarage implements Serializable {

    private transient MyCarData carData;

    public MyCarData getCarData() {
        return carData;
    }

    public void setCarData(MyCarData carData) {
        this.carData = carData;
    }

    @Override
    public String toString() {
        return "MyGarage{" +
                "carData=" + carData +
                '}';
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        System.out.println("write!!!!");
        out.writeInt(carData.getPrice());
        out.writeUTF(carData.getName());
        //out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            int price = in.readInt();
            String name = in.readUTF();
            System.out.println("name=" + name);
            carData = new MyCarData();
            carData.setPrice(price);
            carData.setName(name);

    }
}
