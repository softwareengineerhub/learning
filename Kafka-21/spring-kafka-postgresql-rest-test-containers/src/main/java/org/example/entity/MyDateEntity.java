package org.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Table("my_date")
public record MyDateEntity(
        @Id UUID id,
        String name,
        Timestamp myDate) {

}
