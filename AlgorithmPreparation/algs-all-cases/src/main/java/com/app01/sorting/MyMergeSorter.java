package com.app01.sorting;

public class MyMergeSorter implements MySorter {

    @Override
    public void sort(int[] data) {
        merge(data);
    }

    private int[] merge(int[] data){
        if(data.length<=1){
            return data;
        }

        int[] left = new int[data.length/2];
        int[] right = new int[data.length-left.length];

        System.arraycopy(data, 0, left, 0, left.length);
        System.arraycopy(data, left.length, right, 0, right.length);

        left = merge(left);
        right = merge(right);
        merge(left, right, data);
        return data;
    }

    private void merge(int[] left, int[] right, int[] data){
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;
        while(leftIndex<left.length && rightIndex<right.length){
            if(left[leftIndex]<right[rightIndex]){
                data[index++] = left[leftIndex++];
            }else{
                data[index++] = right[rightIndex++];
            }
        }
        System.arraycopy(left, leftIndex, data, index, left.length-leftIndex);
        System.arraycopy(right, rightIndex, data, index, right.length-rightIndex);
    }
}
