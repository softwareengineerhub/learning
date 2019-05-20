package com.app.ch09.xml.sax;

import com.app.ch09.xml.common.ComputerData;
import com.app.ch09.xml.common.XmlProcessor;

public class Main {

    public static void main(String[] args) {
        String fileName = "sax.xml";
        XmlProcessor xmlProcessor = new SaxXmlProcessor(fileName);
        ComputerData computerData = new ComputerData();
        computerData.setAge(2);
        computerData.setName("Name2");
        xmlProcessor.write(computerData);
        ComputerData computerDataRead = xmlProcessor.read();
        System.out.println(computerData.equals(computerDataRead));

    }
}
