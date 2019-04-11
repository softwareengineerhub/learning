package com.app.ch04.sorter;

public class MergeSorter extends Sorter {

    @Override
    public void sort(int[] data) {
        merge(data);
    }

    private int[] merge(int[] data){
        if(data.length<=1){
            return data;
        }
        int[] leftArray = new int[data.length/2];
        int[] rightArray = new int[data.length-leftArray.length];

        System.arraycopy(data, 0, leftArray, 0, leftArray.length);
        System.arraycopy(data, leftArray.length, rightArray, 0, rightArray.length);

        leftArray = merge(leftArray);
        rightArray = merge(rightArray);

        merge(leftArray, rightArray, data);
        return data;
    }

    private void merge(int[] leftArray, int[] rightArray, int[] data){
        int resultIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;

        while(leftIndex<leftArray.length && rightIndex<rightArray.length){
            int a = leftArray[leftIndex];
            int b = rightArray[rightIndex];
            if(a<b){
                data[resultIndex++] = a;
                leftIndex++;
            }else{
                data[resultIndex++] = b;
                rightIndex++;
            }
        }

        System.arraycopy(leftArray, leftIndex, data, resultIndex, leftArray.length-leftIndex);
        System.arraycopy(rightArray, rightIndex, data, resultIndex, rightArray.length-rightIndex);

    }



}
