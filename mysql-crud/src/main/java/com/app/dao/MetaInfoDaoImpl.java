package com.app.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class MetaInfoDaoImpl implements MetaInfoDao {

    //@Resource(name="aaa")
    private DataSource ds;

    public MetaInfoDaoImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public String printInfo() {
        List<Map<String, Object>> data = readData();
        return createXml(data);
    }

    private List<Map<String, Object>> readData(){
        try(Connection c = ds.getConnection()){
            ResultSet rs = c.prepareStatement("SELECT * FROM test").executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int n = meta.getColumnCount();
            Set<String> names = new HashSet<>();
            for(int i=0;i<n;i++){
                String columnName=meta.getColumnName(i+1);
                /*System.out.println("---------------------------");
                String columnName=meta.getColumnName(i+1);
                String className=meta.getColumnClassName(i+1);
                System.out.println("columnName="+columnName);
                System.out.println("className="+className);*/
                names.add(columnName);
            }
            List<Map<String, Object>> data = new ArrayList();
            while(rs.next()){
                Map<String, Object> item = new HashMap<>();
                data.add(item);
                for(String cloumn: names){
                    Object value=rs.getObject(cloumn);
                    item.put(cloumn, value);
                }
            }
            return data;
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }


    private String createXml(List<Map<String, Object>> data){
        StringBuilder xmlData = new StringBuilder();
        xmlData.append("<items>\n");
        for(Map<String, Object> item: data){
            String xmlItem = createXml(item);
            xmlData.append(xmlItem);
            //xmlData.append("<items>\n");
        }
        xmlData.append("</items>");
        return xmlData.toString();
    }

    private String createXml(Map<String, Object> item){
        StringBuilder xmlData = new StringBuilder();
        xmlData.append("\t<item>\n");
        for(String key: item.keySet()){
            Object value = item.get(key);
            String content = String.format("\t\t<%s>%s</%s>\n", key, value, key);
            xmlData.append(content);
        }
        xmlData.append("\t</item>\n");
        return xmlData.toString();
    }



}
