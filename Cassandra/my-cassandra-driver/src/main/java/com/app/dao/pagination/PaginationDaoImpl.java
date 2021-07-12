package com.app.dao.pagination;

import com.app.model.Person;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;

public class PaginationDaoImpl implements PaginationDao {

    private CqlSession cqlSession;

    {
        cqlSession = CqlSession.builder().withKeyspace("mydb").build();

    }

    @Override
    public void findData(int limit, int pageNumber) {
        PreparedStatement ps = cqlSession.prepare("SELECT * FROM users");
        BoundStatement bs = ps.bind();
        ResultSet rs = cqlSession.execute(bs);

        int offset = limit*pageNumber;
        for(Row row: rs){
            if(rs.getAvailableWithoutFetching()<offset && !rs.isFullyFetched()){
                //rs.fefetchMoreResults(); // this is asynchronous
            }
            int id = row.getInt("id");
            String name = row.getString("name");
            System.out.println("id="+id+"; name="+name);
            //rs.getExecutionInfo().getSafePagingState().toBytes();
            //byte[] array = rs.getExecutionInfo().getPagingState().array();
            //System.out.println("state="+new String(array));
        }
    }

    @Override
    public void findDataCriteria(int limit) {
        Select select = selectFrom("users").all().limit(2);
        SimpleStatement simpleStatement = select.build();
        ResultSet rs = cqlSession.execute(simpleStatement);
        Person person = null;
        for(Row row: rs){
            person = new Person();
            String name = row.getString("name");
            int id = row.getInt("id");
            System.out.println();
        }
    }


  //  @Override
    public void findDataLegacy(int limit) {
        //statement.setFetchSize(pageSize);
        //PreparedStatement ps = cqlSession.prepare("SELECT * FROM users LIMIT 2");
        PreparedStatement ps = cqlSession.prepare("SELECT * FROM users");
        //BoundStatement bs = ps.bind().setInt("id", id);
        BoundStatement bs = ps.bind();
        //PagingState pagingState = PagingState.fromString("next");
        //bs.setPagingState(pagingState);
        // bs.setFetchSize(limit);
        ResultSet rs = cqlSession.execute(bs);
        //rs.getExecutionInfo().getSafePagingState().toBytes();

        for(Row row: rs){
            int id = row.getInt("id");
            String name = row.getString("name");
            System.out.println("id="+id+"; name="+name);
            //rs.getExecutionInfo().getSafePagingState().toBytes();

            //byte[] array = rs.getExecutionInfo().getPagingState().array();
            //System.out.println("state="+new String(array));
        }
//        return person;


        /*ResultSet rs = cqlSession.execute("select * from users");
        for(Row row: rs){
            int id = row.getInt("id");
            System.out.println("id="+id);
        }*/
    }
}
