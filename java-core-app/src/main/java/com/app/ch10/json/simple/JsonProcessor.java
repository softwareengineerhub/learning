package com.app.ch10.json.simple;

public interface JsonProcessor {

    public String toJson(MyModel myModel);

    public MyModel fromJson(String json);
}
