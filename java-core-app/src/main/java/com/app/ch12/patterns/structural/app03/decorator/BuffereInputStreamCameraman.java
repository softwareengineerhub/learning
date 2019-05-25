package com.app.ch12.patterns.structural.app03.decorator;

public class BuffereInputStreamCameraman extends AbstractCamerman {
    public BuffereInputStreamCameraman(Cameraman cameraman) {
        super(cameraman);
    }

    @Override
    public String makeFilm() {
        return super.makeFilm()+"; this bufferedInputStream ";
    }
}
