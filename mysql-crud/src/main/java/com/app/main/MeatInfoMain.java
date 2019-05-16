package com.app.main;

import com.app.dao.MetaInfoDao;
import com.app.dao.MetaInfoDaoImpl;
import com.app.dao.MyDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLException;

public class MeatInfoMain {

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DataSource ds = new MyDataSource();
        MetaInfoDao metaInfoDao = new MetaInfoDaoImpl(ds);
        String xml = metaInfoDao.printInfo();

        PrintStream ps = new PrintStream(new File("items.xml"));
        System.setOut(ps);
        System.out.println(xml);
    }
}
