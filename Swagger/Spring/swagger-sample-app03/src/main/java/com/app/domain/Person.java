package com.app.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "some simple data")
public class Person {
    //@NotNull
    //@NotBlank
    @ApiModelProperty(notes = "name value of person")
    private String name;
    //@Min(10)
    //@Max(200)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
