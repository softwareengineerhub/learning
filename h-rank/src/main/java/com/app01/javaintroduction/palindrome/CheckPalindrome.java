package com.app01.javaintroduction.palindrome;

public class CheckPalindrome {

    public static void main(String[] args) {
        String s = "artra";
        System.out.println(check(s));
    }

    public static boolean check(String s){
        char[] arr = s.toCharArray();
        for(int i=0;i<arr.length/2;i++){
            if(arr[i]!=arr[arr.length-1-i]){
                return false;
            }
        }
        return true;
    }

}
