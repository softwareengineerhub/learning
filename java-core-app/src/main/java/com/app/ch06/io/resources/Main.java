package com.app.ch06.io.resources;

import com.app.ch06.io.model.EmployeeData;

public class Main {

    public static void main(String[] args) {
        MyResourceReader myResourceReader = new MyResourceReader();


        EmployeeData employeeData =myResourceReader.getDataConfig("/myconf/employee.properties");
        System.out.println(employeeData);
    }

}
