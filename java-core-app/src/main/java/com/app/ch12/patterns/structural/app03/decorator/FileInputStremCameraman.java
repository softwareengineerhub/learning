package com.app.ch12.patterns.structural.app03.decorator;

public class FileInputStremCameraman extends AbstractCamerman {

    public FileInputStremCameraman(Cameraman cameraman) {
        super(cameraman);
    }

    @Override
    public String makeFilm() {
        return super.makeFilm()+"; this is for FileInputStream";
    }
}
