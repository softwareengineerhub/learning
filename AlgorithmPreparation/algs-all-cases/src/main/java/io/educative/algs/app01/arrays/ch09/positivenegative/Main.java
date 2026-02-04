package io.educative.algs.app01.arrays.ch09.positivenegative;

import java.util.Arrays;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        //2b5fb3f4-6bf4-4d54-91c0-90f85d2eb163
        System.out.println(UUID.fromString(""));

        int[] arr = {10, -1, 20, 4, 5, -9, -6};
        arr = reArrange(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] reArrange(int[] arr) {
        int[] res = new int[arr.length];
        int j=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]<0){
                res[j++] = arr[i];
            }
        }
        for(int i=0;i<arr.length;i++){
            if(arr[i]>=0){
                res[j++] = arr[i];
            }
        }
        return res;
    }

}
