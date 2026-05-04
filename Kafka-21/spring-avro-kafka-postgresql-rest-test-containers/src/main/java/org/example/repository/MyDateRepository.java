package org.example.repository;

import org.example.entity.MyDateEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface MyDateRepository extends CrudRepository<MyDateEntity, UUID> {

    @Modifying
    @Query("""
    INSERT INTO my_date (id, name, my_date)
    VALUES (:id, :name, :myDate)
    ON CONFLICT (id)
    DO UPDATE SET
        name = EXCLUDED.name,
        my_date = EXCLUDED.my_date
""")
    void upsert(@Param("id") UUID id, @Param("name") String name, @Param("myDate") Instant myDate);

    @Modifying
    @Query("""
    INSERT INTO my_date (id, name, my_date)
    VALUES (:#{#entity.id}, :#{#entity.name}, :#{#entity.myDate})
    ON CONFLICT (id)
    DO UPDATE SET
        name = EXCLUDED.name,
        my_date = EXCLUDED.my_date
""")
    void upsert2(@Param("entity") MyDateEntity entity);
}
