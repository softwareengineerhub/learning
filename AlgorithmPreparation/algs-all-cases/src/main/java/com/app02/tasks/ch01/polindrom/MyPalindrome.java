package com.app02.tasks.ch01.polindrom;

public class MyPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalendrome("denis"));
        System.out.println(isPalendrome("abcdcba"));
        System.out.println(isPalendrome("abccba"));
    }

    public static boolean isPalendrome(String data){
        char[] arr = data.toCharArray();
        for(int i=0;i<arr.length/2;i++){
            if(arr[i]!=arr[arr.length-1-i]){
                return false;
            }
        }
        return true;
    }

}
