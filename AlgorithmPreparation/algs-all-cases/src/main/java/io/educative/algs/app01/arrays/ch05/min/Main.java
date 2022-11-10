package io.educative.algs.app01.arrays.ch05.min;

public class Main {

    public static void main(String[] args) {
        int[] arr = {9, 2, 3, 6};
        int min = min(arr);
        System.out.println("min="+min);
    }

    static int min(int[] arr){
        int min = arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i]<min){
                min = arr[i];
            }
        }
        return min;
    }



}
