package app;

import app.service.MyService;

import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args){
        System.out.println("------------------START SOAP------------------------------------");
        Endpoint.publish("http://0.0.0.0:8081/my-service", new MyService());
        System.out.println("------------------MAIN FINISH------------------------------------");
        /*while(true){
            System.out.println("I am here");
            Thread.sleep(2000);
        }*/
    }
}
