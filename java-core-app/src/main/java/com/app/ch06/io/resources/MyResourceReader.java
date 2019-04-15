package com.app.ch06.io.resources;

import com.app.ch06.io.model.EmployeeData;

import java.io.InputStream;
import java.util.Properties;

public class MyResourceReader {

    public EmployeeData getDataConfig(String filePath) {
        try (InputStream in = getClass().getResourceAsStream(filePath);) {
            Properties properties = new Properties();
            properties.load(in);
            //return properties;
            int age = Integer.parseInt(properties.getProperty("age", "0"));
            String name = properties.getProperty("name");
            EmployeeData employeeData = new EmployeeData();
            employeeData.setName(name);
            employeeData.setAge(age);
            return employeeData;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
