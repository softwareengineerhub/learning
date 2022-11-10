package io.educative.algs.app01.arrays.ch04.findproduct;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {4,2,1,5,0};
        arr = findProduct(arr);
        System.out.println(Arrays.toString(arr));
    }

    static int[] findProduct(int[] arr){
        int count=0;
        int index = -1;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==0){
                count++;
                if(count==2){
                    return new int[arr.length];
                }
                index=i;
            }
        }

        int p=1;
        for(int i:arr){
            if(i!=0){
                p=p*i;
            }
        }

        int[] res = new int[arr.length];
        if(count==1){
            res[index] = p;
            return res;
        }

        for(int i=0;i<arr.length;i++){
            res[i] = p/arr[i];
        }
        return res;
    }
}
