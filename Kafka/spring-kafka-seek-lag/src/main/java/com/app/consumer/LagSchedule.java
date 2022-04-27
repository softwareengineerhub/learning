package com.app.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LagSchedule {
    @Autowired
    private LagComponent lagComponent;

    //@Scheduled(fixedRate = 3000)
    public void checkLag() {
        long lag = lagComponent.calculateLag();
        System.out.println("!!!!!!!lag=" + lag + "!!!!!!!");
    }

}
