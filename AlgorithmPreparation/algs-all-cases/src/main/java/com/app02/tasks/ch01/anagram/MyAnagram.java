package com.app02.tasks.ch01.anagram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MyAnagram {

    public static void main(String[] args) {
        String a = "race";
        String b = "care";
        boolean res = isAnagram(a, b);
        System.out.println("res="+res);
    }

    public static boolean isAnagram(String a, String b){
        if(a.length()!=b.length()){
            return false;
        }
        //return isValidAnagram(a,b);
        return isValidAnagram3(a,b);
    }

    private static boolean isValidAnagram3(String a, String b){
        Map<Character, Long> mapA = toMapByStream(a);
        Map<Character, Long> mapB = toMapByStream(b);
        return mapA.equals(mapB);
    }
    private static Map<Character, Long> toMapByStream(String s){
        return s.chars().mapToObj(i->(char)i).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static boolean isValidAnagram2(String a, String b){
        Map<Character, Long> mapA = toMap(a);
        Map<Character, Long> mapB = toMap(b);
        return mapA.equals(mapB);
    }

    private static Map<Character, Long> toMap(String s){
        Map<Character, Long> map = new HashMap<>();
        char[] data = s.toCharArray();
        for(char c: data){
            if(!map.containsKey(c)){
                map.put(c, 1L);
            }else{
                map.put(c, map.get(c)+1);
            }
        }
        return map;
    }

    private static boolean isValidAnagram(String a, String b){
        char[] aData = a.toCharArray();
        char[] bData = b.toCharArray();
        Arrays.sort(aData);
        Arrays.sort(bData);
        return Arrays.equals(aData, bData);
    }


}
