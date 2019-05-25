package com.app.ch12.patterns.structural.app03.decorator;

public abstract class AbstractCamerman implements Cameraman {
    private Cameraman cameraman;

    public AbstractCamerman(Cameraman cameraman) {
        this.cameraman = cameraman;
    }

    @Override
    public String makeFilm() {
        return cameraman.makeFilm();
    }
}
