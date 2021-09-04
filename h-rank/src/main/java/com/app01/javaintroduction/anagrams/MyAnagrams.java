package com.app01.javaintroduction.anagrams;

import java.util.HashMap;
import java.util.Map;

public class MyAnagrams {

    public static void main(String[] args) {
        String a = "anagramm";
        String b = "marganaa";
        System.out.println(check(a, b));
    }

    public static String check(String a, String b){
       if(a==null || b==null){
           return "Not Anagrams";
       }

       if(a.length()!=b.length()){
           return "Not Anagrams";
       }

       Map mapA = toMap(a.toLowerCase());
       Map mapB = toMap(b.toLowerCase());
       return mapA.equals(mapB) ? "Not Anagrams" : "Anagrams";
    }

    private static Map<String, Long> toMap(String s){
        Map<String, Long> map = new HashMap<String, Long>();
        for(int i=0;i<s.length();i++){
            String key = s.toCharArray()[i]+"";
            if(map.get(key)==null){
                map.put(key, 0L);
            }
            map.put(key, map.get(key)+1);
        }
        return map;
    }
}
