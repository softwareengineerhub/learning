package com.app.ch10.json.simple.bulk;

import com.app.ch10.json.simple.MyModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BulkMain {

    public static void main(String[] args) throws IOException {
        List<MyModel> list = initData(10);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        System.out.println(json);
        System.out.println("-----------------------");
        List<MyModel> resList= mapper.readValue(json, ArrayList.class);
        for(int i=0;i<resList.size();i++){
            System.out.println(resList.get(i));
        }
    }


    private static List<MyModel> initData(int n){
        List<MyModel> data = new ArrayList();
        for(int i=0;i<n;i++){
            MyModel myModel = new MyModel();
            myModel.setAge(i);
            myModel.setName("Name"+i);
            data.add(myModel);
        }
        return data;
    }
}
