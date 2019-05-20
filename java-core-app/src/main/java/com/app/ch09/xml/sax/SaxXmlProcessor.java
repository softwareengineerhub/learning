package com.app.ch09.xml.sax;

import com.app.ch09.xml.common.ComputerData;
import com.app.ch09.xml.common.XmlProcessor;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class SaxXmlProcessor implements XmlProcessor {
    private String fileName;

    public SaxXmlProcessor(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(ComputerData computerData) {
        String xml = toXml(computerData);
        write(xml);
    }

    @Override
    public ComputerData read() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            ComputerData computerData = new ComputerData();
            saxParser.parse(new File(fileName), new DefaultHandler(){
                private String currentTagName;

                @Override
                public void startDocument() throws SAXException {
                    System.out.println("----START----");
                }

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                   // System.out.println("------Start Element-------tag="+qName);
                    currentTagName=qName;
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    String value = new String(ch, start, length);
                    if("name".equals(currentTagName)){
                        computerData.setName(value);
                    }
                    if("age".equals(currentTagName)){
                        computerData.setAge(Integer.parseInt(value));
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    //System.out.println("------End Element-------tag="+qName);
                }

                @Override
                public void endDocument() throws SAXException {
                    System.out.println("----END----");
                }
            });
            return computerData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String toXml(ComputerData computerData) {
        String xml = String.format("<computerdata>" +
                "<name>%s</name>" +
                "<age>%s</age>" +
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
}
