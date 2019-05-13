package com.app.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProcessor extends Thread {
    private ServerSocket serverSocket;
    private boolean needToRun = true;
    private ClientsProcessor clientsProcessor = new ClientsProcessor();

    public ServerProcessor(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception ex) {
            //ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void run(){
        while(needToRun){
            try {
                Socket client = serverSocket.accept();
                ClientThread clientThread = new ClientThread(client, clientsProcessor);
                clientsProcessor.addClient(clientThread);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopServer(){
        needToRun=false;
    }

    private String readClientMessage(Socket client) throws Exception {
        InputStream in = client.getInputStream();
        while(in.available()<=0){
        }
        byte[] data = new byte[in.available()];
        in.read(data);
        return new String(data);
    }

    private void writeMessageToClient(Socket client, String message) throws Exception {
        OutputStream out = client.getOutputStream();
        out.write(message.getBytes());
        out.flush();
    }

}
