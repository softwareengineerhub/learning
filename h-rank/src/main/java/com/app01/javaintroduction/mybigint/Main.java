package com.app01.javaintroduction.mybigint;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        String b = scanner.nextLine();
        //String a = "1234";
        //String b ="20";


        BigInteger x = new BigInteger(a);
        BigInteger y = new BigInteger(b);
        BigInteger tTmp = x.add(y);
        BigInteger rTmp = x.multiply(y);
        System.out.println(tTmp);
        System.out.println(rTmp);

    }
}
