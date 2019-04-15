package com.app.ch06.io.app02.dataio.bulk;

import com.app.ch06.io.model.EmployeeData;

import java.io.IOException;
import java.util.List;

public interface BulkEmployeeDP {

    public void saveToFile(List<EmployeeData> employeeData, String filePath);

    public List<EmployeeData> readFromFile(String filePath);

}
