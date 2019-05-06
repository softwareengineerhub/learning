package com.app.server;

public class ServerStarter {

    public static void main(String[] args) {
        ServerProcessor serverProcessor = new ServerProcessor(8083);
        serverProcessor.start();
        System.out.println("-------SERVER STARTED--------");

    }
}
