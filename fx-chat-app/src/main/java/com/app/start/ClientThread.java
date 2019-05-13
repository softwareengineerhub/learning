package com.app.start;

import java.io.InputStream;
import java.net.Socket;
import javafx.scene.control.TextArea;

public class ClientThread extends Thread {
    private Socket socket;
    private TextArea chatHistoryArea;

    public ClientThread(Socket socket, TextArea chatHistoryArea) {
        this.socket = socket;
        this.chatHistoryArea = chatHistoryArea;
    }

    public void run() {
        while (true) {
            try {
                InputStream in = socket.getInputStream();
                int value=in.available();
                if (value>0) {
                    byte[] data = new byte[in.available()];
                    in.read(data);
                    String msg = new String(data);
                    String old=chatHistoryArea.getText();
                    System.out.println("FRPOM SERVER: "+value);
                    chatHistoryArea.setText(old+"\n"+msg);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }


    public Socket getSocket() {
        return socket;
    }

}
