package com.app.dao;

import com.app.model.Person;
import com.datastax.oss.driver.api.core.CqlSession;

import java.net.InetSocketAddress;


/*
Chapter 14 - Auth

 */
public abstract class AbstractDaoImpl implements Dao {

    //The CqlSession contains a pool of TCP connections for each host.
    /*
    Another acceptable option is to create a CqlSession
    per keyspace, if your application is accessing multiple keyspaces
     */
    protected CqlSession cqlSession;


    public AbstractDaoImpl(){
        init();
    }

    protected void init(){
        /*CqlSession cqlSession = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .build();*/


        cqlSession = CqlSession.builder().withKeyspace("mydb").build();


        /*
        CqlSession cqlSession = CqlSession.builder()
.addContactPoint(new InetSocketAddress("<some IP address>", 9042))
.addContactPoint(new InetSocketAddress("<another IP address>", 9042))
.withLocalDatacenter("<data center name>")
.build()
         */


       /* cqlSession = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .withKeyspace("mydb")
                .build();*/
    }
}
