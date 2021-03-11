package com.app.sample02;

public class Main2 {

    public static void main(String[] args) {
        SimpleDao simpleDao = new SimpleDao();
        simpleDao.createTable();
        Persons p = new Persons();
        p.setId(1);
        p.setName("Name1");
        p.setAge(1);
        simpleDao.save(p);
        System.out.println("----------save is done------");
        Persons res = simpleDao.find(p.getId());
        System.out.println(res);
    }

}
