package com.app.dao.pagination;

public class Main {

    public static void main(String[] args) {
        PaginationDao paginationDao = new PaginationDaoImpl();
        paginationDao.findData(2, 1);
    }

}
