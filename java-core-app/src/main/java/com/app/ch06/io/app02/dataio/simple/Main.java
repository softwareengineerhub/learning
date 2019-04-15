package com.app.ch06.io.app02.dataio.simple;

import com.app.ch06.io.model.EmployeeData;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        EmployeeDataProcessor employeeDataProcessor = new EmployeeDataProcessorImpl();
        EmployeeData employeeData = new EmployeeData();
        //employeeData.setName("A");
        //employeeData.setAge(1);
        //employeeDataProcessor.saveToFile(employeeData,"empData.log");

        employeeData = employeeDataProcessor.readFromFile("empData.log");
        System.out.println(employeeData);

    }
}
