package com.app01.sorting;

public interface MySorter {

    public void sort(int[] data);

    public static void swap(int[] data, int i, int j){
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

}
