package com.app.com.app.ch12.patterns.creational.app02.factory;

public class JsonContentProcessor implements ContentProcessor {

    @Override
    public String read() {
        return "json";
    }

    @Override
    public void write(String content) {

    }
}
