package com.app.ch06.io.app02.dataio.simple;

import com.app.ch06.io.model.EmployeeData;

import java.io.IOException;

public interface EmployeeDataProcessor {

    public void saveToFile(EmployeeData employeeData, String filePath) throws IOException;

    public EmployeeData readFromFile(String filePath) throws IOException ;



}
