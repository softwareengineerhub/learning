package com.app.ch04.searcher;

public class LineraSearcher implements Searcher {

    //O(n)
    @Override
    public int search(int[] data, int target) {
        for(int i=0;i<data.length;i++){
            if(data[i]==target){
                return i;
            }
        }
        return -1;
    }
}
