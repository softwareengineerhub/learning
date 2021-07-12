package com.app.dao.creationdateofdata;

import com.app.model.Person;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.util.Date;

public class CreationDaoImpl implements CreationDao {
    private CqlSession cqlSession;

    {
        cqlSession = CqlSession.builder().withKeyspace("mydb").build();
    }

    @Override
    public void findCreationDate() {
        PreparedStatement ps = cqlSession.prepare("SELECT id, name, age, writetime(name) as w FROM persons");
        BoundStatement bs = ps.bind();
        ResultSet rs = cqlSession.execute(bs);
        Person person = null;
        for(Row row: rs){
            long wTime = row.getLong("w")/1000;
            System.out.println("wTime="+wTime);
            System.out.println("wDate="+new Date(wTime));
            System.out.println("----------------------");

            person = new Person();
            String name = row.getString("name");
            int age = row.getInt("age");
            int id = row.getInt("id");
            person.setName(name);
            person.setAge(age);
            person.setId(id);
            System.out.println(person);
        }
    }

    @Override
    public void findCreationDate(int id) {
        PreparedStatement ps = cqlSession.prepare("SELECT id, name, age, writetime(name) as w FROM persons where id="+id);
        BoundStatement bs = ps.bind();
        ResultSet rs = cqlSession.execute(bs);
        Person person = null;
        for(Row row: rs){
            long wTime = row.getLong("w")/1000;
            System.out.println("wTime="+wTime);
            System.out.println("wDate="+new Date(wTime));
            System.out.println("----------------------");

            person = new Person();
            String name = row.getString("name");
            int age = row.getInt("age");
          //  int id = row.getInt("id");
            person.setName(name);
            person.setAge(age);
            person.setId(id);
            System.out.println(person);
        }
    }

    /*@Override
    public void findCreationDate() {
        PreparedStatement ps = cqlSession.prepare("SELECT * FROM persons");
        BoundStatement bs = ps.bind();
        ResultSet rs = cqlSession.execute(bs);
        Person person = null;
        for(Row row: rs){
            person = new Person();
            String name = row.getString("name");
            int age = row.getInt("age");
            int id = row.getInt("id");
            person.setName(name);
            person.setAge(age);
            person.setId(id);
            System.out.println(person);
        }
    }*/

}
