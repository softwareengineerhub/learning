package com.app01.javaintroduction.loops2;

public class Main {

    public static void main(String[] args) {
        //String res = doAction(0,2,10);
        String res = doAction(5,3,5);
        System.out.println(res);
    }

    public static String doAction(int a, int b, int n){
        int tmp = 0;
        String s = "";
        for(int i=0;i<n;i++){
            if(i==0) {
                tmp = a + powerOfTwo(i) * b + tmp;
            }else{
                tmp = powerOfTwo(i) * b + tmp;
            }
            s=s+" "+ tmp;
        }

        return s.trim();
    }

    private static int powerOfTwo(int n){
        if(n==0){
            return 1;
        }
        int p = 2;
        for(int i=1;i<n;i++){
            p *=2;
        }
        return p;
    }
}
