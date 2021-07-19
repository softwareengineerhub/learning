package com.app.dao.upsertwithbyte;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.nio.ByteBuffer;

public class ByteDataDaoImpl implements ByteDataDao {

    private CqlSession cqlSession;

    {
        cqlSession = CqlSession.builder().withKeyspace("mydb").build();
    }

    @Override
    public void insert(MyData myData) {
        //String cql = "insert into mybytes(id, name, age, content) values(%s, '%s', %s, %s)";
        //cql=String.format(cql, myData.getId(), myData.getName(), myData.getAge(), myData.getContent());
        //String cql = "insert into mybytes(id, name, age) values(%s, '%s', %s, %s)";
        //cql=String.format(cql, myData.getId(), myData.getName(), myData.getAge());
        //cqlSession.execute(cql);


        PreparedStatement ps = cqlSession.prepare("insert into mybytes(id, name, age, content) values(?, ?, ?, ?)");
        BoundStatement bs = ps.bind().setInt("id", myData.getId()).setString("name", myData.getName())
                .setInt("age", myData.getAge());
                //.setInt("age", myData.getAge()).setByteBuffer("content", ByteBuffer.wrap(myData.getContent()));
        cqlSession.execute(bs);
    }

    @Override
    public void upsert(MyData myData, int ttl) {
        PreparedStatement ps = cqlSession.prepare("insert into mybytes(id, name, age, content) values(?, ?, ?, ?) USING TTL "+ttl);
        BoundStatement bs = ps.bind().setInt("id", myData.getId()).setString("name", myData.getName())
                .setInt("age", myData.getAge()).setByteBuffer("content", ByteBuffer.wrap(myData.getContent()));
        cqlSession.execute(bs);
    }

    public MyData findById(int id){
        String cql = "select * from mybytes where id="+id;
        ResultSet rs = cqlSession.execute(cql);
        MyData myData = new MyData();
        for(Row row: rs){
            myData.setId(id);
            myData.setName(row.getString("name"));
            myData.setAge(row.getInt("age"));
            myData.setContent(row.getByteBuffer("content").array());
        }
        return myData;
    }


    @Override
    public void close() throws Exception {
        if(cqlSession!=null){
            cqlSession.close();
        }
    }
}
