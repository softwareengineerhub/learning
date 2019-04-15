package com.app.ch06.io.app02.dataio.bulk;

import com.app.ch06.io.model.EmployeeData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BulkEmployeeDPImpl implements BulkEmployeeDP {


    @Override
    public void saveToFile(List<EmployeeData> employeeData, String filePath){
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath))){
            out.writeInt(employeeData.size());
            for(EmployeeData item: employeeData){
                out.writeInt(item.getAge());
                out.writeUTF(item.getName());
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<EmployeeData> readFromFile(String filePath){
        List<EmployeeData> list = new ArrayList();
        try(DataInputStream in = new DataInputStream(new FileInputStream(filePath))){
            int n = in.readInt();
            EmployeeData item = null;
            for(int i=0;i<n;i++){
                item = new EmployeeData();
                int age = in.readInt();
                String name = in.readUTF();
                item.setAge(age);
                item.setName(name);
                list.add(item);
            }
            return list;
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }
}
