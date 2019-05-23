package com.app.ch10.json.simple;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiJsonProcessor implements JsonProcessor {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String toJson(MyModel myModel) {
        try {
            return mapper.writeValueAsString(myModel);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public MyModel fromJson(String json) {
        try {
            return mapper.readValue(json, MyModel.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
