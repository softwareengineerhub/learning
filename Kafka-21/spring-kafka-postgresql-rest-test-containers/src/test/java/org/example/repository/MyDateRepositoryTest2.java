package org.example.repository;

import org.example.config.AbstractPostgresContainer;
import org.example.entity.MyDateEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class MyDateRepositoryTest2 extends AbstractPostgresContainer {

    @Autowired
    private MyDateRepository repository;

    @Test
    void shouldSaveAndFindEntity() {
        MyDateEntity entity = new MyDateEntity(null, "John", Timestamp.from(Instant.now()));
        MyDateEntity saved = repository.save(entity);
        MyDateEntity entity2 = new MyDateEntity(UUID.randomUUID(), "Boris", Timestamp.from(Instant.now()));
        repository.upsert2(entity2);
        List list = (List)repository.findAll();
        System.out.println("list="+list);
        MyDateEntity entity3 = new MyDateEntity(entity2.id(), "BorisUpdate", Timestamp.from(Instant.now()));
        repository.upsert2(entity3);
        list = (List)repository.findAll();
        System.out.println("list="+list);
    }
}
