package com.app.dao.pagination;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class PaginationDaoImpl implements PaginationDao {

    public void findData(int limit, int pageSize) {
        try(MyCassandraConnector myCassandraConnector = new MyCassandraConnector();){
            myCassandraConnector.connect("127.0.0.1", 9042);
            Session session = myCassandraConnector.getSession();
            ResultSet rs = session.execute("SELECT * FROM mydb.users");
            int offset = limit*pageSize;
            for(Row row: rs){
                if(rs.getAvailableWithoutFetching()<offset && !rs.isFullyFetched()){
                    rs.fetchMoreResults(); // this is asynchronous
                }
                int id = row.getInt("id");
                String name = row.getString("name");
                System.out.println("id="+id+"; name="+name);
                //rs.getExecutionInfo().getSafePagingState().toBytes();
                //byte[] array = rs.getExecutionInfo().getPagingState().array();
                //System.out.println("state="+new String(array));
            }

        }
    }
}
