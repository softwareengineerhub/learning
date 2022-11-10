package io.educative.algs.app01.arrays.ch08.rightrotate;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        rotateArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void rotateArray(int[] arr){
        int tmp = arr[arr.length-1];
        for(int i=arr.length-1;i>0;i--){
            arr[i] = arr[i-1];
        }
        arr[0] = tmp;
    }

}
