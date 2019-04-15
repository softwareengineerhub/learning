package com.app.ch06.io.app02.dataio.props.bulk;

import com.app.ch06.io.model.EmployeeData;

import java.util.List;

public interface BulkPropsProcessor {

    public void saveToFile(List<EmployeeData> list, String filePath);

    public List<EmployeeData> readFromFile(String filePath);
}
