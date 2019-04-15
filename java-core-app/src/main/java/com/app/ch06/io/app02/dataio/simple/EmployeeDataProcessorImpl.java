package com.app.ch06.io.app02.dataio.simple;

import com.app.ch06.io.model.EmployeeData;

import java.io.*;

public class EmployeeDataProcessorImpl implements EmployeeDataProcessor {

    @Override
    public void saveToFile(EmployeeData employeeData, String filePath) throws IOException {
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath))){
            out.writeInt(employeeData.getAge());
            out.writeUTF(employeeData.getName());
        }
    }

    @Override
    public EmployeeData readFromFile(String filePath) throws IOException {
        try(DataInputStream in = new DataInputStream(new FileInputStream(filePath))){
            int age=in.readInt();
            String name = in.readUTF();
            EmployeeData employeeData = new EmployeeData();
            employeeData.setAge(age);
            employeeData.setName(name);
            return employeeData;
        }
    }
}
