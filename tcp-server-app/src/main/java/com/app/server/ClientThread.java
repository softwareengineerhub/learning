package com.app.server;

import java.io.InputStream;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;
    private ClientsProcessor clientsProcessor;

    public ClientThread(Socket socket, ClientsProcessor clientsProcessor) {
        this.socket = socket;
        this.clientsProcessor=clientsProcessor;
    }

    public void run(){
        while(true){
            try {
                InputStream in = socket.getInputStream();
                if(in.available()>0){
                    byte[] clientMessage = new byte[in.available()];
                    in.read(clientMessage);
                    String msg = new String(clientMessage);
                    System.out.println("Client message: "+msg);
                    clientsProcessor.notifyAllClients(msg);
                }
                Thread.sleep(500);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
