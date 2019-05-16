package com.app.main;

import com.app.dao.TransactionalDao;
import com.app.dao.TransactionalDaoImpl;

public class TransactionalMain {

    public static void main(String[] args) {
        TransactionalDao dao = new TransactionalDaoImpl();
        dao.doTransactionalOperation();
    }
}
