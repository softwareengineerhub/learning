package com.app01.sorting;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] data = {1,4,0,2,9,5,3,7,8,6};
        MySorter mySorter = getInstance(3);
        mySorter.sort(data);
        System.out.println(Arrays.toString(data));
    }

    private static MySorter getInstance(int type){
        switch (type){
            case 0: return new MyMergeSorter();
            case 1: return new MyInsertionSorter();
            case 2: return new MySelectionSorter();
            case 3: return new MyBubbleSorter();
            default: return null;
        }
    }
}
