package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.MyEntity;
import org.example.repository.MyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MyService {

    private final MyRepository myRepository;

    public void addData(MyEntity myEntity) {
        myRepository.save(myEntity);
    }

    public MyEntity getData(String name, int age) {
        return myRepository.findByNameAndAge(name, age)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }


}
