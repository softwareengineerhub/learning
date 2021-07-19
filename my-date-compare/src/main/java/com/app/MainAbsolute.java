package com.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class MainAbsolute {

    public static void main(String[] args) throws Exception {
        DateFormat df = new SimpleDateFormat();
        //df.parse("05-11-2021");
        //LocalDate.parse("05-11-2021").plusDays(1).atStartOfDay().toString();

        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println("res1="+zdt.toString());
        ZoneId zoneId = zdt.getZone();
        //String res=zdt.plusDays(1).toString();
        //System.out.println("res2="+res);
        //ZonedDateTime zdt2 = zdt.plusDays(1);
        ZonedDateTime zdt2 = zdt.plusDays(1);
        long count = ChronoUnit.MINUTES.between(zdt, zdt2);
        System.out.println("count="+count);
    }

}
