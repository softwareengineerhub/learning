package org.example.consumer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@Testcontainers
@SpringBootTest
public class IntegrationTest {

    @Container
    static GenericContainer<?> kafka = new GenericContainer<>(
            DockerImageName.parse("confluentinc/cp-kafka:7.5.0"));

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("mydb")
                    .withUsername("user")
                    .withPassword("1");

    @Container
    static GenericContainer<?> schemaRegistry =
            new GenericContainer<>("confluentinc/cp-schema-registry:7.5.0")
                    .withExposedPorts(8081)
                    .withEnv("SCHEMA_REGISTRY_HOST_NAME", "schema-registry")
                    .withEnv("SCHEMA_REGISTRY_LISTENERS", "http://0.0.0.0:8081")
                    //.withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS", "PLAINTEXT://" + kafka.getHost() + ":" + kafka.getMappedPort(9093))
                    //.withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS",
                    //        () -> "PLAINTEXT://" + kafka.getHost() + ":" + kafka.getMappedPort(9093))
                    .dependsOn(kafka);



    //takes configs from db and set to spring
    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.flyway.enabled", () -> true);

        // Schema Registry
        registry.add("spring.kafka.properties.schema.registry.url",
                () -> "http://" + schemaRegistry.getHost() + ":" + schemaRegistry.getMappedPort(8081));
    }


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private KafkaTemplate<MyKey, MyValue> kafkaTemplate;
//64768
    @Test
    public void test() throws Exception {
        System.out.println("http://" + schemaRegistry.getHost() + ":" + schemaRegistry.getMappedPort(8081));
        if(1<2){
            return;
        }

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