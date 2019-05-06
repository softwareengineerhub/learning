package com.app.ch08.concurrency.app13.blockingqueue.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MyDelayData implements Delayed {
    private long delay;

    public MyDelayData(long delay) {
        this.delay = delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delay, unit);

    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (o.getDelay(TimeUnit.MILLISECONDS)-delay);
    }
}
