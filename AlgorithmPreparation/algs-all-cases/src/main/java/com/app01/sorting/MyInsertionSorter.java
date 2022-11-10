package com.app01.sorting;

public class MyInsertionSorter implements MySorter {

    @Override
    public void sort(int[] data) {
       for(int i=0;i<data.length-1;i++){
           int k=i+1;
           while(k>0 && data[k]<data[k-1]){
               MySorter.swap(data, k, k-1);
               k--;
           }
       }
    }
}
