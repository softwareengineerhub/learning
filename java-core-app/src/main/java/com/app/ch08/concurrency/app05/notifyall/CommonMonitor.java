package com.app.ch08.concurrency.app05.notifyall;

public class CommonMonitor {
    private boolean needToConsume;

    public boolean isNeedToConsume() {
        return needToConsume;
    }

    public void setNeedToConsume(boolean needToConsume) {
        this.needToConsume = needToConsume;
    }
}
