package com.app.ch04.sorter;

public class SelectionSorter extends Sorter {

    @Override
    public void sort(int[] data) {
        for(int i=0;i<data.length-1;i++){
            int min = data[i];
            int minIndex = i;
            for(int j=i+1;j<data.length;j++){
                if(data[j]<min){
                    min = data[j];
                    minIndex = j;
                }
            }
            if(minIndex!=i){
                swap(minIndex, i, data);
            }
        }
    }
}
