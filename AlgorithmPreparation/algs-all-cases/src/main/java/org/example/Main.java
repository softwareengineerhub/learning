package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm'Z'";
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String res = "#"+simpleDateFormat.format(date)+"#";
        System.out.println("res="+res);

    }
}