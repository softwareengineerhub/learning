package io.educative.algs.app01.arrays.ch01.removeeven;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 10, 6, 3};
        arr = removeEven(arr);
        System.out.println(Arrays.toString(arr));
    }

    static int[] removeEven(int[] arr){
        int count = 0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]%2!=0){
                count++;
            }
        }

        int[] res = new int[count];
        int j=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]%2!=0){
                res[j++]=arr[i];
            }
        }
        return res;
    }
}
