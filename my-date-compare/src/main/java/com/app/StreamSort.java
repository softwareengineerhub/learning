package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public class StreamSort {

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(2L);
        list.add(7L);
        list.add(1L);
        Optional<Long> res = list.stream().sorted().findFirst();
        long tt=res.get();
        System.out.println(tt);
    }

}
