package com.app.ch09.xml.simple;

import com.app.ch09.xml.common.ComputerData;
import com.app.ch09.xml.common.XmlProcessor;

public class Main {

    public static void main(String[] args) {
        String fileName = "computerdata.xml";
        ComputerData computerData = new ComputerData();
        computerData.setName("Name1");
        computerData.setAge(1);
        XmlProcessor xmlProcessor = new SimpleXmlProcessor(fileName);
        xmlProcessor.write(computerData);
        ComputerData computerDataRead = xmlProcessor.read();
        System.out.println(computerData.equals(computerDataRead));
    }

}
