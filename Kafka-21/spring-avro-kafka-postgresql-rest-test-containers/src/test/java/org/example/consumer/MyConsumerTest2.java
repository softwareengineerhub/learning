package org.example.consumer;

import org.example.config.AbstractPostgresContainer;
import org.example.service.MyService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.kafka.KafkaContainer;

import java.io.File;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class MyConsumerTest2  extends AbstractPostgresContainer{

    @Container
   // @ServiceConnection
    static KafkaContainer kafka = new KafkaContainer("confluentinc/cp-kafka:7.5.0");


    @BeforeAll
    public static void beforeAll() {
        kafka.start();
    }

    @AfterAll
    public static void afterAll() {
        kafka.stop();
    }


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private KafkaTemplate<MyKey, MyValue> kafkaTemplate;
    @Autowired
    private MyService myService;


    @Test
    public void workflowTest() throws Exception {
        mockMvc.perform(get("/api")
                        .param("name", "WrongValue")
                        .param("age", "300")
                        .header("X-Request-Id", "test-123")
                        .header("Authorization", "Bearer dummy-token"))
                .andExpect(status().isNotFound());

        var id = System.currentTimeMillis();
        MyKey myKey = new MyKey(id, "title" + id);
        MyValue myValue = new MyValue("Boris", 30, "description333");
        kafkaTemplate.send("datatopic", myKey, myValue);
        Thread.sleep(3000);

        mockMvc.perform(get("/api")
                        .param("name", myValue.name())
                        .param("age", myValue.age() + "")
                        .header("X-Request-Id", "test-123")
                        .header("Authorization", "Bearer dummy-token"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(myValue.name()))
                .andExpect(jsonPath("$.age").value(myValue.age()))
                .andExpect(jsonPath("$.description").value(myValue.description()))
                .andExpect(jsonPath("$.version").value(0));
    }
}
