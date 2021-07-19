package com.app.main;

import java.io.FileInputStream;
import java.io.InputStream;

public class ResourceHelper {


    public static String readResource(String path){
        try(InputStream in = ResourceHelper.class.getResourceAsStream(path)){
            byte[] data = new byte[in.available()];
            in.read(data);
            return new String(data);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
