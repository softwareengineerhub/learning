package com.app.ch10.json.simple;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SimpleJsonProcessor implements JsonProcessor {
    @Override
    public String toJson(MyModel myModel) {
        try (InputStream in = getClass().getResourceAsStream("/simple_template.json");) {
            byte[] data = new byte[in.available()];
            in.read(data);
            String jsonTemplate = new String(data);
            return String.format(jsonTemplate, myModel.getName(), myModel.getAge());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MyModel fromJson(String json) {
        MyModel myModel = new MyModel();
        int start = json.indexOf("\"");
        int startItem = json.indexOf("\":", start);
        int end = json.indexOf("\",", startItem);
        String name=json.substring(startItem, end);
        name=name.replaceAll("\"","");
        name=name.replaceAll(":","");
        name=name.trim();
        myModel.setName(name);
        //start = json.indexOf("\"");
        startItem = json.indexOf(":", end+1);
        end = json.lastIndexOf("\"");
        String ageValue = json.substring(startItem, end);
        ageValue=ageValue.replaceAll("\"","");
        ageValue=ageValue.replaceAll(":","");
        ageValue=ageValue.trim();
        //System.out.println(ageValue);
        myModel.setAge(Integer.parseInt(ageValue));
        //String[] array = json.split(":");
        //System.out.println(Arrays.toString(array));
        return myModel;
    }
}
