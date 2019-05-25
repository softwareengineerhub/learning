package com.app.com.app.ch12.patterns.creational.app02.factory;

public class XlsContentProcessor implements ContentProcessor {
    @Override
    public String read() {
        return "xls";
    }

    @Override
    public void write(String content) {

    }
}
