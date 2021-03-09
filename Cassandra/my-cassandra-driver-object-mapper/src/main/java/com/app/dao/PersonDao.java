package com.app.dao;

import com.app.model.Persons;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.*;
import com.datastax.oss.driver.api.mapper.annotations.Dao;


/*

need to use clustering columns to be use it in where

 */
@Dao
public interface PersonDao {

    @Insert
    void save(Persons persons);

    /*@Query("SELECT * FROM ${tableId}")
    PagingIterable<Persons> findAll();*/

    /*@Select(customWhereClause = "id = :myId AND name = :myName")
    PagingIterable<Persons> findByIdAndName(
            @CqlName("id") int myId,
            @CqlName("name") String myName);*/

    @Select(customWhereClause = "id = :id")
    Persons findById(@CqlName("id") int id);

}
