package com.app.dao.aggregation;

import com.app.model.Person;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class AggregationDaoImpl implements AggregationDao {
    private CqlSession cqlSession;

    {
        cqlSession = CqlSession.builder().withKeyspace("mydb").build();
    }

    @Override
    public void save(int id, int age, String name, int salary){
        String cql = String.format("INSERT INTO users(id, age, name, salary) VALUES(%s,%s,'%s', %s)", id, age, name, salary);
        cqlSession.execute(cql);
    }

    @Override
    public Integer findMinInClusterColumn(int id, int age) {
        String cql = String.format("SELECT min(salary) FROM users WHERE id=%s and age=%s", id, age);
        ResultSet rs = cqlSession.execute(cql);
        for (Row row : rs) {
            return row.getInt(1);
        }
        return null;
    }
}
