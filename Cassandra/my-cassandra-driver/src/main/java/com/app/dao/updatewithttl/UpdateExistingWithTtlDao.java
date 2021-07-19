package com.app.dao.updatewithttl;

public interface UpdateExistingWithTtlDao extends AutoCloseable {

    public void insert(Data data);

    public void update(int id, String name, int age, int ttl);

    public void updateSimple(int id, String name, int age, int ttl);

    public void update(Data data);

    public void alterTable(int defaultTtl);

    public void checkWtAndUpdateTtlWithYear(int id, int ttl);

    public void checkWtAndUpdateTtlWithMinute(int id, int ttl);

    public void createTable();

    public void dropTable();

    void close();

}
