package com.app.ch04.sorter;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] data = {5,2,8,9,6,0,1,3,4,7};
        //Sorter sorter = new BubbleSorter();
        //Sorter sorter = new SelectionSorter();
        //Sorter sorter = new InsertionSorter();
        Sorter sorter = new MergeSorter();
        sorter.sort(data);
        System.out.println(Arrays.toString(data));
    }
}
