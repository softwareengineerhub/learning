package com.app.ch09.xml.myjaxb;

public class Main {

    public static void main(String[] args) {
        String fileName = "jaxb.xml";
        MyJaxbXmlProcessor processor = new MyJaxbXmlProcessor(fileName);
        UserData userData = new UserData();
        userData.setAge(3);
        userData.setName("Name3");
        processor.write(userData);
        UserData userDataRead = processor.read();
        System.out.println(userData);
        System.out.println(userDataRead);

    }
}
