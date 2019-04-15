package com.app.ch06.io.app02.dataio.props.bulk;

import com.app.ch06.io.model.EmployeeData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BulkPropsProcessorImpl implements BulkPropsProcessor {


    @Override
    public void saveToFile(List<EmployeeData> list, String filePath) {
        Properties properties = new Properties();
        properties.setProperty("n",list.size()+"");
        for(int i=0;i<list.size();i++){
            EmployeeData item = list.get(i);
            properties.setProperty("age."+i, item.getAge()+"");
            properties.setProperty("name."+i, item.getName());
        }
        try(OutputStream out = new FileOutputStream(filePath)){
            properties.store(out,"");
        }catch(Exception ex){
            throw  new RuntimeException(ex);
        }
    }

    @Override
    public List<EmployeeData> readFromFile(String filePath) {
        try(InputStream in = new FileInputStream(filePath)){
            Properties properties = new Properties();
            properties.load(in);
            List<EmployeeData> list = new ArrayList<>();
            int n=Integer.parseInt(properties.getProperty("n", "0"));
            for(int i=0;i<n;i++){
                int age=Integer.parseInt(properties.getProperty("age."+i));
                String name=properties.getProperty("name."+i);
                EmployeeData employeeData = new EmployeeData();
                employeeData.setAge(age);
                employeeData.setName(name);
                list.add(employeeData);
            }
            return list;
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
