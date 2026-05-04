package org.example.repository;

import org.example.entity.MyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MyRepository extends CrudRepository<MyEntity, UUID> {

    Optional<MyEntity> findByNameAndAge(String name, int age);

}
