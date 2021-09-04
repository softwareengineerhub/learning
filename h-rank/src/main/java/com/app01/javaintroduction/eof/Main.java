package com.app01.javaintroduction.eof;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);;
        int i=0;
        while(scanner.hasNext()){
            String line = scanner.next();
            System.out.println(++i+" "+line);
        }
    }
}
