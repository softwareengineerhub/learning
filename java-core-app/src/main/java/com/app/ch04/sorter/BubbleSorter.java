package com.app.ch04.sorter;

public class BubbleSorter extends Sorter {

    //O(n^2)
    @Override
    public void sort(int[] data) {
        for(int i=0;i<data.length-1;i++){
            for(int j = i+1;j<data.length;j++){
                if(data[j]<data[i]){
                    swap(i, j, data);
                }
            }
        }
    }
}
