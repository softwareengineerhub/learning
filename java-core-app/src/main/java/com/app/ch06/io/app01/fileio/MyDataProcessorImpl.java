package com.app.ch06.io.app01.fileio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyDataProcessorImpl implements MyDataProcessor {

    @Override
    public void saveToFile(String content, String filePath) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File(filePath));
            byte[] data = content.getBytes();
            out.write(data);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public String readFromFile(String filePath) {
        InputStream in = null;
        try {
            in = new FileInputStream(new File(filePath));
            //byte[] data = new byte[in.available()];
            //in.read(data);


            /*
            List<Byte> content = new ArrayList();
            int t = -1;
            while((t=in.read())!=-1){
                content.add((byte)t);
            }
            //Byte[] data = (Byte[])(content.toArray());
            byte[] data = new byte[content.size()];
            for(int i=0;i<content.size();i++){
                data[i] = content.get(i);
            }
            return new String(data);*/


            byte[] buff = new byte[1024];
            String tmpStr = "";
            while (in.read(buff)!=-1){
                tmpStr+=new String(buff);
            }
            return tmpStr;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
