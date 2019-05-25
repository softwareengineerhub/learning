package com.app.com.app.ch12.patterns.creational.app02.factory;

public class XmlContentProcessor implements ContentProcessor {

    @Override
    public String read() {
        return "xml";
    }

    @Override
    public void write(String content) {

    }
}
