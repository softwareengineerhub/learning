package com.app.dao.upsertwithbyte;

public interface ByteDataDao extends AutoCloseable {

    public void insert(MyData myData);

    public void upsert(MyData myData, int ttl);

    public MyData findById(int id);

}
