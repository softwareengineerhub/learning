package com.app.com.app.ch12.patterns.creational.app02.factory;

public class Main {

    public static void main(String[] args) {
        ContentProcessor contentProcessor = getProcessor("xls");
        String read=contentProcessor.read();
        System.out.println(read);
    }

    public static ContentProcessor getProcessor(String type) {
        if ("xml".equalsIgnoreCase(type)) {
            return new XmlContentProcessor();
        }
        if ("json".equalsIgnoreCase(type)) {
            return new JsonContentProcessor();
        }
        if ("xls".equalsIgnoreCase(type)) {
            return new XlsContentProcessor();
        }
        throw new RuntimeException("Illegal type: " + type);
    }
}
