package com.app;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class Main {

    public static void main(String[] args) {
        java.util.Date juDate = new java.util.Date();
        DateTime current = new DateTime(juDate);

        System.out.println(current);


        DateTime created = new DateTime();
        created = created.withYear(2021);
        created = created.withMonthOfYear(4);
        created = created.withDayOfMonth(21);

        //Days.daysBetween(start.withTimeAtStartOfDay() , end.withTimeAtStartOfDay() ).getDays();
        //int res = Days.daysBetween(created.withTimeAtStartOfDay() , current.withTimeAtStartOfDay() ).getDays();
        int res = Days.daysBetween(created , current).getDays();
        System.out.println("res="+res);
    }

}
