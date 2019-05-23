package com.app.ch11.xls.processor;

import java.util.List;

public interface DataProcessor {

    public void writeToFile(String filePath, List<BusinessData> data);

    public List<BusinessData> readFromFile(String filePath);
}
