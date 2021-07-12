package com.app.main;

import com.app.dao.creationdateofdata.CreationDao;
import com.app.dao.creationdateofdata.CreationDaoImpl;

public class MainCreationDao {

    public static void main(String[] args) {
        CreationDao creationDao = new CreationDaoImpl();
        //creationDao.findCreationDate();
        creationDao.findCreationDate(1);
    }

}
