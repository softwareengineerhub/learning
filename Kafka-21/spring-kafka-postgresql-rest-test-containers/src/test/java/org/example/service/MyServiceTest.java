package org.example.service;

import org.example.config.AbstractPostgresContainer;
import org.example.entity.MyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MyServiceTest extends AbstractPostgresContainer {

    @Autowired
    private MyService myService;

    @Test
    public void test() {
        MyEntity myEntity = new MyEntity(null, "Mike", 30, "DescMike", null);
        myService.addData(myEntity);

        MyEntity result = myService.getData(myEntity.name(), myEntity.age());
        assertThat(result.name()).isEqualTo(myEntity.name());
        assertThat(result.age()).isEqualTo(myEntity.age());
        assertThat(result.description()).isEqualTo(myEntity.description());
        assertThat(result.version()).isEqualTo(0L);
    }

}
