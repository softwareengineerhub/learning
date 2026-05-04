package org.example.controller;

import org.example.config.AbstractPostgresContainer;
import org.example.entity.MyEntity;
import org.example.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class MyControllerTest extends AbstractPostgresContainer {

    @Autowired
    private MyService myService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {
        MyEntity myEntity = new MyEntity(null, "Mike", 30, "DescMike", null);
        myService.addData(myEntity);

        mockMvc.perform(get("/api")
                        .param("name", "Mike")
                        .param("age", "30")
                        .header("X-Request-Id", "test-123")
                        .header("Authorization", "Bearer dummy-token"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Mike"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.description").value("DescMike"))
                .andExpect(jsonPath("$.version").value(0));
    }

}
