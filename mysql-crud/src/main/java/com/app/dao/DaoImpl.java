package com.app.dao;

import com.app.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao {

    /*static {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            //Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    public void create(Person person) {
        Connection c = null;
        try{
            c = getConnection();
            Statement statement = c.createStatement();
            String sql = String.format("INSERT INTO my_db.person(name, age) VALUES('%s', %s) ", person.getName(), person.getAge());
            statement.execute(sql);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            if(c!=null){
                try {
                    c.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public List<Person> read() {
        try(Connection c = getConnection()){
            List<Person> persons = new ArrayList<>();
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT  * FROM person");
            while(rs.next()){
                String name = rs.getString("name");
                int age = rs.getInt("age");
                Person person = new Person();
                person.setAge(age);
                person.setName(name);
                persons.add(person);
            }
            return persons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Person candidate, Person current) {
        try(Connection c = getConnection()){
            Statement statement = c.createStatement();
            String sql = String.format("UPDATE person SET age=%s, name='%s' where age=%s AND name='%s'",candidate.getAge(), candidate.getName(), current.getAge(), current.getName());
            statement.execute(sql);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public void delete(String name) {
        try(Connection c = getConnection()){
            Statement statement = c.createStatement();
            statement.execute("DELETE FROM person WHERE name='"+name+"'");
        }catch(Exception ex){
            throw  new RuntimeException(ex);
        }
    }

    public void delete(int age) {
        try(Connection c = getConnection()){
            Statement statement = c.createStatement();
            statement.execute("DELETE FROM person WHERE age='"+age+"'");
        }catch(Exception ex){
            throw  new RuntimeException(ex);
        }
    }

    public void delete(String name, int age) {
        try(Connection c = getConnection()){
            Statement statement = c.createStatement();
            statement.execute("DELETE FROM person WHERE name='"+name+"' AND age="+age);
        }catch(Exception ex){
            throw  new RuntimeException(ex);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3307/my_db?autoReconnect=true", "root", "11111111");
    }

}
