package com.app.ch06.io.mylocale;

import com.app.ch06.io.model.EmployeeData;
import com.app.ch06.io.resources.MyResourceReader;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        //Locale.setDefault(Locale.CANADA);
        MyResourceBundleReader reader = new MyResourceBundleReader();
        EmployeeData emp=reader.readData("content", Locale.ITALY);
        System.out.println(emp);
        emp=reader.readData("content", Locale.getDefault());
        System.out.println(emp);
        emp=reader.readData("content", Locale.CANADA_FRENCH);
        System.out.println(emp);
        emp=reader.readData("content", Locale.CANADA);
        System.out.println(emp);
    }
}
