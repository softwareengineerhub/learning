package com.app.mapper;

import com.app.dao.PersonDao;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface PersonMapper {

    @DaoFactory
    PersonDao personDao();
}
