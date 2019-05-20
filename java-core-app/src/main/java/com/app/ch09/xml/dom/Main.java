package com.app.ch09.xml.dom;

import com.app.ch09.xml.myjaxb.UserData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        String filePath = "sax.xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
        //System.out.println(factory.hashCode());
        //System.out.println(factory2.hashCode());

        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File(filePath));

        Node root = document.getFirstChild();
        NodeList children = root.getChildNodes();
        int n = children.getLength();
        UserData userData = new UserData();
        for(int i=0;i<n;i++){
            System.out.println("---------------");
            Node node = children.item(i);
            String value = node.getTextContent();
            String tagName = node.getNodeName();
            //System.out.println("tagName="+tagName);
            //System.out.println("value="+value);
            //System.out.println("---------------");
            if("#text".equals(tagName)) {
                continue;
            }
            if("name".equalsIgnoreCase(tagName)){
                userData.setName(value);
            }
            if("age".equalsIgnoreCase(tagName)){
                userData.setAge(Integer.parseInt(value));
            }
        }
        System.out.println("-------finished----------");
        System.out.println(userData);
    }
}
