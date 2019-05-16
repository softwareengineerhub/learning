package com.app.dao;

import java.sql.*;

public class TransactionalDaoImpl implements TransactionalDao {

    @Override
    public int doTransactionalOperation() {
         try(Connection c = getConnection()){
             c.setAutoCommit(false);

             String sql = "INSERT INTO person(name, age) VALUES(?,?)";

             PreparedStatement ps = c.prepareStatement(sql);
             ps.setObject(1, "Test");
             ps.setObject(2, 21);
             ps.execute();

             sql = "INSERT INTO person_fake(name, age) VALUES(?,?)";
             ps = c.prepareStatement(sql);
             ps.setObject(1, "Test2");
             ps.setObject(2, 22);
             ps.execute();

             c.commit();


             return 1;
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3307/my_db?autoReconnect=true","root","11111111");
    }
}
