package com.app.sample01;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.sun.jndi.toolkit.url.Uri;

import java.net.URI;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        // Create the CqlSession object:
        String username = System.getenv("ASTRA_USERNAME");
        String password = System.getenv("ASTRA_PASSWORD");
        String bundle = System.getenv("ASTRA_BUNDLE");

//        URI uri = Main.class.getResource(bundle).toURI();
        try (CqlSession session = CqlSession.builder()
                .withCloudSecureConnectBundle(Paths.get(bundle))
                .withAuthCredentials(username, password)
                //.withAuthCredentials("<<CLIENT ID>>","<<CLIENT SECRET>>")
                .build()) {
            // Select the release_version from the system.local table:
            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            //Print the results of the CQL query to the console:
            if (row != null) {
                System.out.println(row.getString("release_version"));
            } else {
                System.out.println("An error occurred.");
            }
        }
        System.exit(0);
    }
}