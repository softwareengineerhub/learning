package com.app.sample02;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleDao {


    public void createTable() {
        try (CqlSession session = getConnection()) {
            session.execute(readContent("/create.txt"));
        }
    }

    public void save(Persons person) {
        try (CqlSession session = getConnection()) {
            String cql = String.format("INSERT INTO persons(id, name, age) VALUES(%s,'%s', %s)", person.getId(), person.getName(), person.getAge());
            session.execute(cql);
        }
    }

    public Persons find(int id) {
        try (CqlSession session = getConnection()) {
            String cql = String.format("SELECT * FROM persons WHERE id=%s", id);
            ResultSet rs = session.execute(cql);
            Persons person = null;
            for (Row row : rs) {
                person = new Persons();
                String name = row.getString("name");
                int age = row.getInt("age");
                person.setName(name);
                person.setAge(age);
                person.setId(id);
            }
            return person;
        }
    }

    private CqlSession getConnection() {
        String username = System.getenv("ASTRA_USERNAME");
        String password = System.getenv("ASTRA_PASSWORD");
        String bundle = System.getenv("ASTRA_BUNDLE");
        return CqlSession.builder()
                .withCloudSecureConnectBundle(Paths.get(bundle))
                .withAuthCredentials(username, password)
                .withKeyspace("persons")
                .build();
    }


    private String readContent(String filePath) {
        try {
            URI uri = getClass().getResource(filePath).toURI();
            return new String(Files.readAllBytes(Paths.get(uri)));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
