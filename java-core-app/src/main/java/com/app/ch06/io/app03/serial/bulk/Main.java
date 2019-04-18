package com.app.ch06.io.app03.serial.bulk;

import com.app.ch06.io.app03.serial.MyCup;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filePath = "bulkMyCup.txt";
        List<MyCup> list = init(200);
        BulksOperation bulksOperation = new BulkOperationImpl();
        //bulksOperation.save(list, filePath);

        List<MyCup> list2=bulksOperation.read(filePath);
        System.out.println("size="+list2.size());
        for(MyCup item: list2){
            System.out.println(item);
        }
    }

    private static List<MyCup> init(int n){
        List<MyCup> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            MyCup myCup = new MyCup(i);
            myCup.setName("Name"+i);
            myCup.setAge(i);
            list.add(myCup);
        }
        return list;
    }
}
