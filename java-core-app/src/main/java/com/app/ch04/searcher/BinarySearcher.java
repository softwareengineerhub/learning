package com.app.ch04.searcher;

public class BinarySearcher implements Searcher {
    @Override
    public int search(int[] data, int target) {
        int leftIndex = 0;
        int rightIndex = data.length;
        while(leftIndex<=rightIndex){
            int middle = (rightIndex+leftIndex)/2;
            if(data[middle]==target){
                return middle;
            }
            if(data[middle]<target){
                leftIndex = middle+1;
            }else{
                rightIndex = middle-1;
            }
        }
        return -1;
    }
}
