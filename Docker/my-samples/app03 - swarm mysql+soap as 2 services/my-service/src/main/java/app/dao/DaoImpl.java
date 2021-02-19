package app.dao;

import app.model.Person;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl {

    public void initData() {
        try (Connection c = getConnection()) {
            String a = readContent("/A.sql");
            PreparedStatement ps = c.prepareStatement(a);
            ps.execute();

            String b = readContent("/B.sql");
            ps = c.prepareStatement(b);
            ps.execute();

            String cSql = readContent("/C.sql");
            ps = c.prepareStatement(cSql);
            ps.execute();

            String d = readContent("/D.sql");
            ps = c.prepareStatement(d);
            ps.execute();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Person> findAll() {
        try (Connection c = getConnection()) {
            List<Person> list = new ArrayList<>();
            PreparedStatement ps = c.prepareStatement("select * from person");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int age = Integer.parseInt(rs.getString("age"));
                list.add(new Person(name, age));
            }
            return list;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private Connection getConnection() throws SQLException {
        String url = System.getProperty("MY_URL");
        String user = System.getProperty("MY_USER");
        String password = System.getProperty("MY_PASSWORD");
        System.out.println("url="+url);
        System.out.println("user="+user);
        System.out.println("password="+password);
        System.out.println("--------------------------");
        url = System.getenv("MY_URL");
        user = System.getenv("MY_USER");
        password = System.getenv("MY_PASSWORD");
        System.out.println("url="+url);
        System.out.println("user="+user);
        System.out.println("password="+password);


        return DriverManager.getConnection(url, user, password);
    }

    private String readContent(String path) {
        try (InputStream in = getClass().getResourceAsStream(path)) {
            byte[] data = new byte[in.available()];
            in.read(data);
            return new String(data);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
