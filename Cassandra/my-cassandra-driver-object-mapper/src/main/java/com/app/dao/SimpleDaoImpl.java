package com.app.dao;

import com.app.mapper.PersonMapper;
import com.app.mapper.PersonMapperBuilder;
import com.app.model.Persons;

import java.util.List;

public class SimpleDaoImpl extends AbstractDaoImpl {


    @Override
    public void save(Persons persons) {
        System.out.println(persons);
        PersonMapper personMapper = new PersonMapperBuilder(cqlSession).build();
        //System.out.println("cqlSession="+cqlSession);
        PersonDao personDao = personMapper.personDao();
        personDao.save(persons);
    }

    @Override
    public Persons find(int id) {
        try {
            PersonMapper personMapper = new PersonMapperBuilder(cqlSession).build();
        /*List<Persons> list =  personMapper.personDao().findByIdAndName(id, "Name1").all();
        System.out.println("--------------findByIdAndName()-------------------");
        for(Persons p: list){
            System.out.println(p);
        }*/
            System.out.println("---------------------------------------------------");

        /*List<Persons> list = personMapper.personDao().findAll().all();
        System.out.println("--------------findAll()-------------------");
        for(Persons p: list){
            System.out.println(p);
        }
        System.out.println("---------------------------------------------------");*/


            return personMapper.personDao().findById2(id).get();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        //return null;
    }
}
