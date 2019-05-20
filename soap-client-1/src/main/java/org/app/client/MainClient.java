package org.app.client;

import com.app.ch09.xml.myws.MyCalculator;
import com.app.ch09.xml.myws.MyCalculatorService;

public class MainClient {

    public static void main(String[] args) {
        MyCalculatorService service = new MyCalculatorService();
        MyCalculator myCalculator = service.getMyCalculatorPort();
        int res=myCalculator.add(-1,2);
        System.out.println(res);
    }
}
