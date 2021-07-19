package com.app.dao.updatewithttl;

import com.app.main.ResourceHelper;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class UpdateExistingWithTtlDaoImpl implements UpdateExistingWithTtlDao {

    private CqlSession cqlSession;

    {
        cqlSession = CqlSession.builder().withKeyspace("mydb").build();
    }

    @Override
    public void insert(Data data) {
        String cql = ResourceHelper.readResource("/updatewithttl/data-insert.txt");
        cql = String.format(cql, data.getId(),  data.getAge(), data.getName());
        System.out.println(cql);
        cqlSession.execute(cql);
    }

    @Override
    public void update(int id, String name, int age, int ttl) {
        String cql = "insert into mydata(id, age, name) VALUES(%s, %s, '%s') USING TTL %s";
        cql = String.format(cql, id, age, name, ttl);
        cqlSession.execute(cql);
    }

    @Override
    public void updateSimple(int id, String name, int age, int ttl){
        //System.out.println("MINUTES="+count);
        //String cql = "update mydata using ttl %s SET id=%s, name='%s', age=%s WHERE id=%s";
        //String cql = "update mydata using ttl %s SET name='%s', age=%s WHERE id=%s";
        //cql = String.format(cql, ttl, name, age,  id);

        String cql = "update mydata USING TTL 20 SET name='%s', age=%s WHERE id=%s";
        cql = String.format(cql, name, age,  id);

        System.out.println(cql);
        cqlSession.execute(cql);
    }

    @Override
    public void update(Data data) {
        String cql = ResourceHelper.readResource("/updatewithttl/data-update-data.txt");
        cql = String.format(cql, data.getAge(), data.getName(), data.getId());
        System.out.println(cql);
        cqlSession.execute(cql);
    }

    @Override
    public void alterTable(int defaultTtl) {
        String cql = ResourceHelper.readResource("/updatewithttl/data-table-alter.txt");
        cql = String.format(cql, defaultTtl);
        cqlSession.execute(cql);
    }

    @Override
    public void checkWtAndUpdateTtlWithYear(int id, int ttl) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        String cql = ResourceHelper.readResource("/updatewithttl/data-table-select-by-id.txt");
        System.out.println(cql);
        ResultSet rs = cqlSession.execute(cql);
        for(Row row: rs){
            long wt = row.getLong("wt")/1000;
            System.out.println(new Date(wt));
            ZonedDateTime wTime = ZonedDateTime.ofInstant(new Date(wt).toInstant(), ZoneId.of("UTC"));
            long count = ChronoUnit.YEARS.between(wTime, currentDateTime);
            System.out.println("years="+count);
            if(count>1){
                System.out.println("NEED TO ");
            }
        }
    }

    @Override
    public void checkWtAndUpdateTtlWithMinute(int id, int ttl) {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        String cql = ResourceHelper.readResource("/updatewithttl/data-table-select-by-id.txt");
        System.out.println(cql);
        ResultSet rs = cqlSession.execute(cql);
        for(Row row: rs){
            long wt = row.getLong("wt")/1000;
            Integer ttlValue = row.get("ttl", Integer.TYPE);
            if(ttlValue==null) {
                ZonedDateTime wTime = ZonedDateTime.ofInstant(new Date(wt).toInstant(), ZoneId.of("UTC"));
                long count = ChronoUnit.MINUTES.between(wTime, currentDateTime);
                if (count > 3) {
                    System.out.println("NEED TO as minutes between=" + count);

                    //upsert
                    //update(row.getInt("id"), row.getString("name"), row.getInt("age"), ttl);

                    //simple update
                    updateSimple(row.getInt("id"), row.getString("name"), row.getInt("age"), ttl);
                }
            }
        }
    }



    @Override
    public void createTable() {
        String cql = ResourceHelper.readResource("/updatewithttl/data-table-create.txt");
        System.out.println(cql);
        cqlSession.execute(cql);
    }

    @Override
    public void dropTable() {
        String cql = ResourceHelper.readResource("/updatewithttl/data-table-drop.txt");
        System.out.println(cql);
        cqlSession.execute(cql);
    }

    @Override
    public void close() {
        cqlSession.close();
    }
}
