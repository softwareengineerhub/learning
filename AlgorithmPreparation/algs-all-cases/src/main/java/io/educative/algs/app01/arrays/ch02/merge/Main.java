package io.educative.algs.app01.arrays.ch02.merge;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 4, 5};
        int[] arr2 = {2, 6, 7, 8};
        int[] res = mergeArrays(arr1, arr2);
        System.out.println(Arrays.toString(res));
    }

    static int[] mergeArrays(int[] left, int[] right){
        int[] res = new int[left.length+right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;
        while(leftIndex<left.length && rightIndex<right.length){
            if(left[leftIndex]<right[rightIndex]){
                res[index++] = left[leftIndex++];
            }else{
                res[index++] = right[rightIndex++];
            }
        }
        System.arraycopy(left, leftIndex, res, index, left.length-leftIndex);
        System.arraycopy(right, rightIndex, res, index, right.length-rightIndex);
        return res;
    }
}
