package org.app;

import com.app.ch09.xml.myws.MyCar;
import com.app.ch09.xml.myws.MyCarProcessor;
import com.app.ch09.xml.myws.MyCarProcessorService;
import com.app.ch09.xml.myws.ObjectFactory;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        //ObjectFactory factory = new ObjectFactory();


        MyCarProcessorService service = new MyCarProcessorService();
        MyCarProcessor processor = service.getMyCarProcessorPort();
        MyCar myCar1 = new MyCar();
        myCar1.setModel("M1");
        myCar1.setPrice(1);
       // processor.save(myCar1);
        List<MyCar> myCars =  processor.read();
        for(MyCar myCar: myCars){
            System.out.println(myCar.getModel()+"; "+myCar.getPrice());
        }
    }
}
