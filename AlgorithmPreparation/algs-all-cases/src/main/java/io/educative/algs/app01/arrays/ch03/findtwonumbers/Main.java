package io.educative.algs.app01.arrays.ch03.findtwonumbers;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {1, 21, 3, 14, 5, 60, 7, 6};
        int value = 27;
        int[] res = findSum(arr, value);
        System.out.println(Arrays.toString(res));
    }

    static int[] findSum(int[] arr, int target){
        int[] res = new int[2];
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]+arr[j]==target){
                    return new int[]{arr[i], arr[j]};
                }
            }
        }
        return res;
    }

}
