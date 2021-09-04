package com.app01.javaintroduction.subarrays;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int n = Integer.parseInt(line.trim());
        int[] data = new int[n];
        line = scanner.nextLine();
        String[] tmp = line.split(" ");
        for(int i=0;i<n;i++){
            data[i] = Integer.parseInt(tmp[i].trim());
        }
        //int[] data = {1, -2, 4, -5, 1};
        int count = countNegativeSub(data);
        System.out.println(count);
    }

    public static int countNegativeSub(int[] data) {
        int count = 0;
        for(int i=0;i<data.length;i++){
            if(isNegative(data[i])){
                //System.out.println(Arrays.toString(new int[]{data[i]}));
                count++;
            }
            int[] tmp = {data[i]};
            for(int j=i+1;j<data.length;j++){
                //tmp = java.util.Arrays.copyOf(tmp, tmp.length+1);
                tmp[tmp.length-1] = data[j];
                if(isNegative(tmp)){
                    count++;

                }
            }
        }

        return count;
    }



    private static boolean isNegative(int... array){
        System.out.println(Arrays.toString(array));
        int sum = 0;
        for(int i: array){
            sum+=i;
        }
        return sum<0;
    }

}
