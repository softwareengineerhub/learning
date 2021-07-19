package com.app.main;

import com.app.dao.SimpleDaoImpl4TTLWithUpdate;
import com.app.dao.updatewithttl.Data;
import com.app.dao.updatewithttl.UpdateExistingWithTtlDao;
import com.app.dao.updatewithttl.UpdateExistingWithTtlDaoImpl;
import com.app.model.Person;


/*

CREATE TABLE test_table (
    # your table definition #
) WITH default_time_to_live = 10;

Inserted rows then disappear after 10 seconds.


altert table test_table default_time_to_live = 10

*/
public class MainUpdateExistingTTL {

    public static void main(String[] args) {
        try(UpdateExistingWithTtlDao dao = new UpdateExistingWithTtlDaoImpl();) {
            //dao.createTable();

            //dao.alterTable(60*3);

            //Data data = new Data(2, 2, "N2");
            //Data data = new Data(1, 1, "N1");
            //dao.update(new Data(1, 11, "N11"));
            //dao.update(new Data(1, 11, "N11"));

           /* dao.insert(new Data(1, 1, "N1"));
            dao.insert(new Data(2, 2, "N2"));
            dao.insert(new Data(3, 3, "N3"));
            dao.insert(new Data(4, 4, "N4"));
            dao.insert(new Data(5, 5, "N5"));*/

           dao.updateSimple(1, "N1", 1, 1);


            //dao.checkWtAndUpdateTtl(1, 1);

           // dao.checkWtAndUpdateTtlWithMinute(1,2);

            //dao.dropTable();
        }

    }

}
