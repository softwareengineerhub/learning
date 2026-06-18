package com.app.model;

import java.time.LocalDate;

public class TestValue {
    private String title;
    private String data;

    public TestValue(String title, String data) {
        this.title = title;
        this.data = data;
    }

    public TestValue() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TestValue{" +
                "title='" + title + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
