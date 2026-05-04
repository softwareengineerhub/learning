package org.example.repository;

import org.example.config.AbstractPostgresContainer;
import org.example.entity.MyEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@SpringBootTest
public class MyRepositoryTest extends AbstractPostgresContainer {

    @Autowired
    private MyRepository repository;

    @Test
    void shouldSaveAndFindEntity() {
        MyEntity entity = new MyEntity(null, "John", 30, "Desc1", null);
        MyEntity saved = repository.save(entity);
        assertThat(saved.id()).isNotNull();
        var result = repository.findByNameAndAge("John", 30);
        assertThat(result).isPresent();
        MyEntity myEntity = result.get();
        assertThat(myEntity.name()).isEqualTo("John");
        assertThat(myEntity.age()).isEqualTo(30);
        assertThat(myEntity.description()).isEqualTo("Desc1");
        assertThat(myEntity.version()).isEqualTo(0L);

        //check constraint name and age should be unique// insert as id is generated as new
        assertThatThrownBy(() ->
                repository.save(new MyEntity(null, "John", 30, "Desc2", null))
        ).isInstanceOf(DuplicateKeyException.class);

        //update with same id and different version - optimistic exception
        assertThatThrownBy(() ->
                repository.save(new MyEntity(saved.id(), "John", 30, "Desc2", 3L))
        ).isInstanceOf(OptimisticLockingFailureException.class);

        MyEntity entity2 = new MyEntity(saved.id(), "John", 30, "Desc2", saved.version());
        repository.save(entity2);

        result = repository.findByNameAndAge(entity2.name(), entity2.age());
        assertThat(result).isPresent();
        myEntity = result.get();
        assertThat(myEntity.name()).isEqualTo(entity2.name());
        assertThat(myEntity.age()).isEqualTo(entity2.age());
        assertThat(myEntity.description()).isEqualTo(entity2.description());
        assertThat(myEntity.version()).isEqualTo(saved.version() + 1);

        MyEntity entity3 = new MyEntity(null, "Mike", 30, "DescMike", null);
        repository.save(entity3);
        result = repository.findByNameAndAge("Mike", 30);
        assertThat(result).isPresent();
        MyEntity entity3Result = result.get();
        assertThat(entity3Result.version()).isEqualTo(0L);
        final UUID resultId = entity3Result.id();

        //still triggers as insert, not update if we do not put correct version
        //in case id and version are same --> update, otherwise insert
        assertThatThrownBy(() ->
                repository.save(new MyEntity(null, "John", 30, "Desc2", null))
        ).isInstanceOf(DuplicateKeyException.class);

        MyEntity entity4 = new MyEntity(resultId, "Mike", 30, "DescMike2", entity3Result.version());
        repository.save(entity4);
        result = repository.findByNameAndAge("Mike", 30);
        assertThat(result).isPresent();
        assertThat(result.get().version()).isEqualTo(entity3Result.version() + 1);


    }
}
