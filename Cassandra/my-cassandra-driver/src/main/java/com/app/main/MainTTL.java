package com.app.main;

import com.app.dao.Dao;
import com.app.dao.SimpleDaoImpl;
import com.app.dao.SimpleDaoImpl4TTLWithUpdate;
import com.app.model.Person;



/*

CREATE TABLE test_table (
    # your table definition #
) WITH default_time_to_live = 10;

Inserted rows then disappear after 10 seconds.


altert table test_table default_time_to_live = 10

*/
public class MainTTL {

    public static void main(String[] args) {
        SimpleDaoImpl4TTLWithUpdate dao = new SimpleDaoImpl4TTLWithUpdate();
        Person person = new Person();
        person.setAge(1);
        person.setName("Name1");
        person.setId(1);
        dao.save(person);
        System.out.println("------------------");
        Person res = dao.find(1);
        System.out.println(res);

        dao.save(person, 60);

        int ttl = dao.findTTL(person.getId());
        System.out.println("ttl="+ttl);
    }

}
