package org.app;

public class Data {
    @GoodContent(length = 10, typeOfContent = "qqqq")
    private String content;

    public void doAction(){
        System.out.println("CONTENT = "+content);
    }




}
