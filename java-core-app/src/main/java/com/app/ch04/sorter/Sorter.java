package com.app.ch04.sorter;

public abstract class Sorter {

    public abstract void sort(int[] data);


    public void swap(int i, int j, int[] data){
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void swap2(int i, int j, int[] data){
        data[i] = data[i] + data[j];
        data[j] = data[i] - data[j];
        data[i] = data[i] - data[j];
    }

}
