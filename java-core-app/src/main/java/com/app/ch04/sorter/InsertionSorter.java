package com.app.ch04.sorter;

import java.util.Arrays;

public class InsertionSorter extends Sorter {

    @Override
    public void sort(int[] data) {
        for(int i=1;i<data.length;i++){
            int j = i;
            System.out.println(i);
            while(j>0 && data[j]<data[j-1]){
                swap(j, j-1, data);
                System.out.println("\t"+Arrays.toString(data));
                j--;
            }
        }
    }
}
