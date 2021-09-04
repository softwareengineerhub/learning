package com.app01.javaintroduction.uniquepair;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String [] pair_left = new String[t];
        String [] pair_right = new String[t];

        for (int i = 0; i < t; i++) {
            pair_left[i] = s.next();
            pair_right[i] = s.next();
        }



        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int n = Integer.parseInt(line);
        java.util.Set setKey = new java.util.HashSet();
        for(int i=0;i<n;i++){
            line = scanner.nextLine();
            //String[] array = line.split(" ");
            setKey.add(line.trim());
            System.out.println(setKey.size());
        }
    }

    private static void check(String[] left, String[] right, int n){
        java.util.Set set = new java.util.HashSet();
        for(int i=0;i<n;i++){
            String a = left[i];
            String b = right[i];
            if(set.contains(a+" "+b) || set.contains(b+" "+a)){
                System.out.println(set.size());
            }else{
                set.add(a+" "+b);
                System.out.println(set.size());
            }
        }
    }

}
