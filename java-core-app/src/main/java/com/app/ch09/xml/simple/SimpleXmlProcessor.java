package com.app.ch09.xml.simple;

import com.app.ch09.xml.common.ComputerData;
import com.app.ch09.xml.common.XmlProcessor;

import java.io.*;

public class SimpleXmlProcessor implements XmlProcessor {
    private String fileName;

    public SimpleXmlProcessor(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(ComputerData computerData) {
        String xml = toXml(computerData);
        write(xml);
    }

    @Override
    public ComputerData read() {
        String xml = readXml();
        ComputerData computerData = fromXml(xml);
        return computerData;
    }

    private ComputerData fromXml(String xml) {
        ComputerData computerData = new ComputerData();
        int start = xml.indexOf("<name>")+"<name>".length();
        int end = xml.indexOf("</name>");
        //[)
        String name = xml.substring(start, end);
        start = xml.indexOf("<age>")+"<age>".length();
        end = xml.indexOf("</age>");
        //[)
        String ageValue = xml.substring(start, end);
        int age = Integer.parseInt(ageValue);
        computerData.setAge(age);
        computerData.setName(name);
        return computerData;
    }

    private String toXml(ComputerData computerData) {
        String xml = String.format("<computerdata>" +
                "<name>%s</name>"+
                "<age>%s</age>"+
                "</computerdata>", computerData.getName(), computerData.getAge());
        return xml;
    }

    private void write(String xml) {
        try (Writer writer = new FileWriter(new File(fileName))) {
            writer.write(xml);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String readXml() {
        try (Reader reader = new FileReader(new File(fileName));
             BufferedReader buff = new BufferedReader(reader);
        ) {
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = buff.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
