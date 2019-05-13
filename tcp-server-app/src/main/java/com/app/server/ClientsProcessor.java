package com.app.server;

import java.io.OutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ClientsProcessor {
    private Set<ClientThread> clients = new HashSet();

    public void addClient(ClientThread clientThread) {
        clients.add(clientThread);
        clientThread.start();
    }

    public void notifyAllClients(String msg) {
        for (ClientThread clientThread : clients) {
            try {
                Socket socket = clientThread.getSocket();
                OutputStream out = socket.getOutputStream();
                out.write(msg.getBytes());
                out.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
