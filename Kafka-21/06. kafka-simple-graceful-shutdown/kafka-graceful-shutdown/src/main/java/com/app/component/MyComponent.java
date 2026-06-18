package com.app.component;

import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("applicationShutdown")
public class MyComponent implements HealthIndicator {
    @Override
    public @Nullable Health health() {
        return Health.up().build();
    }
}
