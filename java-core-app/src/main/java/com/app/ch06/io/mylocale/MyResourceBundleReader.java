package com.app.ch06.io.mylocale;

import com.app.ch06.io.model.EmployeeData;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class MyResourceBundleReader {

    public EmployeeData readData(String fileName, Locale locale){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName, locale);
        String name=resourceBundle.getString("name");
        int age = Integer.parseInt(resourceBundle.getString("age"));
        EmployeeData employeeData = new EmployeeData();
        employeeData.setName(name);
        employeeData.setAge(age);
        return employeeData;
    }
}
