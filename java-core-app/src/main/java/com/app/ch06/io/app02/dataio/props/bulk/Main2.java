package com.app.ch06.io.app02.dataio.props.bulk;

import com.app.ch06.io.model.EmployeeData;

import java.util.ArrayList;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        String filePath = "bulk2.properties";
        //List<EmployeeData> list = initData(10);
        BulkPropsProcessor bulkPropsProcessor = new BulkPropsProcessorImpl2();
        //bulkPropsProcessor.saveToFile(list, filePath);

        List<EmployeeData> list = bulkPropsProcessor.readFromFile(filePath);
        System.out.println("size="+list.size());
        for(EmployeeData item: list){
            System.out.println(item);
        }
    }

    private static List<EmployeeData> initData(int n){
        List<EmployeeData> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            EmployeeData item = new EmployeeData();
            item.setName("Name"+i);
            item.setAge(i);
            list.add(item);
        }
        return list;
    }
}
