package com.app.main;

import com.app.dao.creationdateofdata.CreationDao;
import com.app.dao.creationdateofdata.CreationDaoImpl;
import com.app.dao.pagination.PaginationDao;
import com.app.dao.pagination.PaginationDaoImpl;

public class MainPaginationDao {

    public static void main(String[] args) {
        PaginationDao paginationDao = new PaginationDaoImpl();
        paginationDao.findData(1, 2);
    }

}
