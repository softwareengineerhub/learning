package com.app.main;

import com.app.dao.aggregation.AggregationDao;
import com.app.dao.aggregation.AggregationDaoImpl;

public class MainAggregation {

    public static void main(String[] args) {
        AggregationDao aggregationDao = new AggregationDaoImpl();
        //aggregationDao.save(1,1,"user1",1);
        //aggregationDao.save(1,1,"user11",11);

        //aggregationDao.save(1,2,"user2",2);
        //aggregationDao.save(1,2,"user22",22);

        Integer min = aggregationDao.findMinInClusterColumn(1,1);
        System.out.println("min="+min);
        min = aggregationDao.findMinInClusterColumn(1,1);
        System.out.println("min="+min);
    }

}
