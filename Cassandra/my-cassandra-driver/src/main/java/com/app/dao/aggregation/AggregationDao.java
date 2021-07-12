package com.app.dao.aggregation;

public interface AggregationDao {

    public void save(int id, int age, String name, int salary);

    Integer findMinInClusterColumn(int id, int age);
}
