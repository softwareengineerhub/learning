package com.app.ch09.xml.myjaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MyJaxbXmlProcessor {
    private String fileName;

    public MyJaxbXmlProcessor(String fileName) {
        this.fileName = fileName;
    }

    public void write(UserData userData) {
        try {
            JAXBContext context = JAXBContext.newInstance(UserData.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(userData, new File(fileName));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public UserData read() {
        try {
            JAXBContext context = JAXBContext.newInstance(UserData.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UserData userData = (UserData) unmarshaller.unmarshal(new File(fileName));
            return userData;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}
