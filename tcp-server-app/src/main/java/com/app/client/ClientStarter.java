package com.app.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientStarter {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("52.39.234.36",8083);
        OutputStream out = socket.getOutputStream();
        String message = "Hello from Client";
        out.write(message.getBytes());
        out.flush();
        InputStream in = socket.getInputStream();
        while(in.available()<=0){
        }
        byte[] data = new byte[in.available()];
        in.read(data);
        System.out.println(new String(data));
        //Thread.sleep(Integer.MAX_VALUE);
        //socket.close();
    }
}
