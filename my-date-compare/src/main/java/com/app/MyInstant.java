package com.app;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/*
--------------------------------------
2021-05-13T12:31:43.596+03:00[Europe/Helsinki]
2021-05-13T09:31:43.655Z[UTC]
--------------------------------------
1620898303596
1620898303596
1620898303395
 */

public class MyInstant {

    public static void main(String[] args) {
        long time3 = System.currentTimeMillis();
        long time4 = Clock.systemDefaultZone().millis();
        long time5 = Clock.system(ZoneId.of("UTC")).millis();
        ZonedDateTime zdt1 = ZonedDateTime.now();
        ZonedDateTime zdt2 = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println("--------------------------------------");
        System.out.println(zdt1);
        System.out.println(zdt2);
        System.out.println("--------------------------------------");
        long time1 = zdt1.toInstant().toEpochMilli();
        long time2 = zdt1.toInstant().toEpochMilli();
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time3);
        System.out.println(time4);
        System.out.println(time5);
        System.out.println("--------------------------------------");

        long t = 1620898303596L;
        Instant instant = Instant.ofEpochMilli(t);
        //System.out.println("instant="+instant);
        ZonedDateTime res1 = ZonedDateTime.ofInstant(instant, zdt1.getZone());
        ZonedDateTime res2 = ZonedDateTime.ofInstant(instant, zdt2.getZone());
        System.out.println(res1);
        System.out.println(res2);
        System.out.println("-------------");
        Instant instant2 = Instant.now();
        boolean isBefore = instant.isBefore(instant2);
        System.out.println(isBefore);
        System.out.println("--------------------------------------");

    }

}
