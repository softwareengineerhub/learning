package com.app.ch12.patterns.behaivior.app01.template;

public abstract class CoderTemplate {

    public void makeProgramm(){
        System.out.println("I am a programmer and I am working with:");
        System.out.println(getLanguage());
    }

    public abstract String getLanguage();

}
