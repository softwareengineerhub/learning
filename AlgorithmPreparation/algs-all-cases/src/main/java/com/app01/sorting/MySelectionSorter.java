package com.app01.sorting;

public class MySelectionSorter implements MySorter {
    @Override
    public void sort(int[] data) {
        for(int i=0;i<data.length-1;i++){
            int min = data[i];
            int index = i;
            for(int j=i+1; j<data.length;j++){
                if(data[j]<min){
                    min=data[j];
                    index = j;
                }
            }
            if(index!=i){
                MySorter.swap(data, i, index);
            }
        }
    }
}
