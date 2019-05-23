package com.app.ch10.json.simple;

public class Main {

    public static void main(String[] args) {
        MyModel myModel = new MyModel();
        myModel.setAge(10);
        myModel.setName("Name10");
        JsonProcessor jsonProcessor = getProcessor(1);
        String json = jsonProcessor.toJson(myModel);
        System.out.println(json);
        System.out.println("------------------------");
        MyModel myModel1 = jsonProcessor.fromJson(json);
        System.out.println(myModel1);
    }

    private static JsonProcessor getProcessor(int type) {
        switch (type) {
            case 0:
                return new SimpleJsonProcessor();
            case 1:
                return new ApiJsonProcessor();
            default:
                throw new RuntimeException("Incorrect type=" + type);
        }
    }
}
