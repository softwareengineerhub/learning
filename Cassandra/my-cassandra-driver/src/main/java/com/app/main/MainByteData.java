package com.app.main;

import com.app.dao.upsertwithbyte.ByteDataDao;
import com.app.dao.upsertwithbyte.ByteDataDaoImpl;
import com.app.dao.upsertwithbyte.MyData;
import com.app.dao.upsertwithbyte.MyWorker;

import java.io.*;

public class MainByteData {


    public static void main(String[] args) {
        try(ByteDataDao dao = new ByteDataDaoImpl();) {
            MyData myData = new MyData();
            myData.setId(1);
            myData.setName("Name");
            myData.setAge(1);


            //myData.setContent("Hello".getBytes());
            dao.insert(myData);

            MyWorker myWorker = new MyWorker();
            myWorker.setName("Name1");
            myWorker.setAge(2);
            byte[] content = serialize(myWorker);
            myData.setContent(content);


            dao.upsert(myData, 30);

            myData = dao.findById(1);
            System.out.println(myData);
            MyWorker mw = deserialize(myData.getContent());
            System.out.println(mw);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public static void mainSimpleString(String[] args) {
        try(ByteDataDao dao = new ByteDataDaoImpl();) {
            MyData myData = new MyData();
            myData.setId(1);
            myData.setName("Name");
            myData.setAge(1);
            myData.setContent("Hello".getBytes());
            //dao.insert(myData);
            dao.upsert(myData, 30);

            myData = dao.findById(1);
            System.out.println(myData);
            System.out.println(new String(myData.getContent()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static byte[] serialize(MyWorker myWorker){
        try(ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(out)){

            objOut.writeObject(myWorker);
            return out.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static MyWorker deserialize(byte[] content){
        try(ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(content))){
            return (MyWorker) in.readObject();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
