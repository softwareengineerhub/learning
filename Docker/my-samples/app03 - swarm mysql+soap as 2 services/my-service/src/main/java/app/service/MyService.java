package app.service;

import app.dao.DaoImpl;
import app.model.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class MyService {
    private DaoImpl dao = new DaoImpl();

    @WebMethod
    public void initData(){
        dao.initData();
    }

    @WebMethod
    public List<Person> findAll(){
        return dao.findAll();
    }

}
