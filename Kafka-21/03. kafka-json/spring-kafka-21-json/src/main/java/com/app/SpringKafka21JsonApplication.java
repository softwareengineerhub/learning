package com.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKafka21JsonApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafka21JsonApplication.class, args);
    }

    static final String TEST = "TEST_GROUP-${random.uuid}";

    @Value("${random.uuid}")
    private String testData;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("####TEST=" + TEST);
        System.out.println("####testData=" + testData);
    }
}
