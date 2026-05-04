package org.example.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("my_table")
public record MyEntity(
        @Id UUID id,
        String name,
        int age,
        String description,
        @Version
        Long version) {

}
