package com.app01.javaintroduction.substring;

public class Main {

    public static void main(String[] args) {
        System.out.println(doAction("Helloworld", 3,7));
    }

    private static String doAction(String s, int start, int end){
        char[] arr = s.toCharArray();
        String res = "";
        for(int i=start;i<end;i++){
            res+=arr[i];
        }
        return res;
    }
}
