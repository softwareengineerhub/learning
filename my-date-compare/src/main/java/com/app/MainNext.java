package com.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class MainNext {

    public static void main(String[] args) throws Exception {
        DateFormat df = new SimpleDateFormat();
        //df.parse("05-11-2021");
        //LocalDate.parse("05-11-2021").plusDays(1).atStartOfDay().toString();
        ZonedDateTime zdt = ZonedDateTime.now();

        /*ZonedDateTime zdt2 = zdt.plusDays(1);
        ZonedDateTime zdt3 = zdt2.with(ChronoField.HOUR_OF_DAY, 0)
                .with(ChronoField.MINUTE_OF_HOUR, 0)
                .with(ChronoField.SECOND_OF_MINUTE, 0)
                .with(ChronoField.MINUTE_OF_HOUR, 0)
                .with(ChronoField.MONTH_OF_YEAR, 1);*/

        /*ZonedDateTime zdt2 = zdt.plusYears(1);
        ZonedDateTime zdt3 = zdt2.with(ChronoField.MILLI_OF_SECOND, 0)
                .with(ChronoField.SECOND_OF_MINUTE, 0)
                .with(ChronoField.MINUTE_OF_HOUR, 0)
                .with(ChronoField.HOUR_OF_DAY, 0)
                //.with(ChronoField.DAY_OF_WEEK, 1)
                .with(ChronoField.DAY_OF_MONTH, 1)
                .with(ChronoField.MONTH_OF_YEAR, 1);*/


        ZonedDateTime zdt2 = zdt.plusWeeks(2);
        ZonedDateTime zdt3 = zdt2.with(ChronoField.MILLI_OF_SECOND, 0)
                .with(ChronoField.SECOND_OF_MINUTE, 0)
                .with(ChronoField.MINUTE_OF_HOUR, 0)
                .with(ChronoField.HOUR_OF_DAY, 0)
                .with(ChronoField.DAY_OF_WEEK, 1);

        System.out.println("zdt="+zdt);
        System.out.println("zdt2="+zdt);
        System.out.println("zdt3="+zdt3);

        System.out.println("--------------------");
        System.out.println("res1="+zdt.toString());


        long count = ChronoUnit.MINUTES.between(zdt, zdt2);
        System.out.println("count="+count);
    }

}
