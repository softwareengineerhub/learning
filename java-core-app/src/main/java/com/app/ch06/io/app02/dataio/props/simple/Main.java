package com.app.ch06.io.app02.dataio.props.simple;

import com.app.ch06.io.model.EmployeeData;

public class Main {

    public static void main(String[] args) {
        String filePath = "mydata.properties";
        PropsProcessor propsProcessor = new PropsProcessorImpl();
        /*EmployeeData item = new EmployeeData();
        item.setName("Name1");
        item.setAge(1);
        propsProcessor.saveToFile(item, filePath);*/

        EmployeeData item = propsProcessor.readFrom(filePath);
        System.out.println(item);
    }
}
