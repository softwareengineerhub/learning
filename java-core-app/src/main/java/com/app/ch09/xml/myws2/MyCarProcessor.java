package com.app.ch09.xml.myws2;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class MyCarProcessor {
    private List<MyCar> cars;

    @WebMethod
    public void save(MyCar myCar) {
        if (cars == null) {
            cars = new ArrayList<>();
        }
        cars.add(myCar);
    }

    @WebMethod
    public List<MyCar> read() {
        return cars;
    }
}
