package com.app.ch12.patterns.structural.app03.decorator;

public class DefaultCameraman implements Cameraman {

    @Override
    public String makeFilm() {
        return "I am working!!!";
    }
}
