package com.app.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LagController {
    @Autowired
    private LagComponent lagComponent;

    @GetMapping("/lag")
    public long checkLag() {
        long lag = lagComponent.calculateLag();
        System.out.println(lag);
        return lag;
    }

}
