package com.app.ch06.io.app02.dataio.bulk;

import com.app.ch06.io.model.EmployeeData;

import java.util.ArrayList;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        String fileName = "bulk2.txt";
        BulkEmployeeDP bulkEmployeeDP = new BulkEmployeeDPImpl2();
        //List<EmployeeData> list = initData(20);
        //bulkEmployeeDP.saveToFile(list, fileName);

        List<EmployeeData> resultList = bulkEmployeeDP.readFromFile(fileName);
        System.out.println("size="+resultList.size());
        for(EmployeeData item: resultList){
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
