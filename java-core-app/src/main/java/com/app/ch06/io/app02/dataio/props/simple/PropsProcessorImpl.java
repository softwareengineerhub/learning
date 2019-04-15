package com.app.ch06.io.app02.dataio.props.simple;

import com.app.ch06.io.model.EmployeeData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropsProcessorImpl implements PropsProcessor {

    @Override
    public void saveToFile(EmployeeData employeeData, String filePath) {
        Properties properties = new Properties();
        properties.setProperty("name", employeeData.getName());
        properties.setProperty("age", employeeData.getAge() + "");
        try (OutputStream out = new FileOutputStream(filePath)) {
            properties.store(out, "");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public EmployeeData readFrom(String filePath) {
        Properties properties = new Properties();
        try (InputStream in = new FileInputStream(filePath)) {
            properties.load(in);
            String name = properties.getProperty("name");
            String ageValue = properties.getProperty("age");
            int age = Integer.parseInt(ageValue);
            EmployeeData employeeData = new EmployeeData();
            employeeData.setAge(age);
            employeeData.setName(name);
            return employeeData;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
