package com.app.repository;

import com.app.domain.Person;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MyRepository {

    private List<Person> list = new ArrayList<>();

    public List<Person> getList(){
        return list;
    }
}
