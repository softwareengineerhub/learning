package com.app.ch06.io.app02.dataio.props.simple;

import com.app.ch06.io.model.EmployeeData;

public interface PropsProcessor {

    public void saveToFile(EmployeeData employeeData, String filePath);

    public EmployeeData readFrom(String filePath);
}
