package io.educative.algs.app01.arrays.ch06.firstuniqueue;

public class Main {

    public static void main(String[] args) {
        int[] arr = {9, 2, 3, 2, 6, 6};
        int res = findFirstUnique(arr);
        System.out.println("res="+res);
    }

    static int findFirstUnique(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            boolean isUniqueue = true;
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]==arr[j]){
                    isUniqueue=false;
                    break;
                }
            }
            if(isUniqueue){
                return arr[i];
            }
        }
        return -1;
    }
}
