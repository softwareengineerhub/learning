package com.app.ch05.covariant;

public class BusinesProcessorChild  extends  BusinessProcessor {

    @Override
    public B processData() {
        return new B();
    }
}
