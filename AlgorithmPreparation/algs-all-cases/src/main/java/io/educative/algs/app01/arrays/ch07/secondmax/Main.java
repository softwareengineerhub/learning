package io.educative.algs.app01.arrays.ch07.secondmax;

public class Main {

    public static void main(String[] args) {
        int[] arr = {9,2,3,6};
        int secondMax = findSecondMaximum(arr);
        System.out.println("secondMax="+secondMax);
    }

    static int findSecondMaximum(int[] arr){
        int maxA = arr[0];
        int maxB = arr[1];
        if(maxA<maxB){
            maxA = arr[1];
            maxB = arr[0];
        }
        for(int i=2;i<arr.length;i++){
            if(arr[i]>maxA){
                maxB = maxA;
                maxA=arr[i];
            } else if(arr[i] > maxB){
                maxB = arr[i];
            }
        }
        return maxB;
    }

}
