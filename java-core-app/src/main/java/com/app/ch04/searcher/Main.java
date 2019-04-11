package com.app.ch04.searcher;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] data = {5,1,7,2,9,0,4};
        Searcher searcher = new LineraSearcher();
        int res=searcher.search(data, 5);
        System.out.println(res);
        res=searcher.search(data, 4);
        System.out.println(res);
        res=searcher.search(data, 2);
        System.out.println(res);
        res=searcher.search(data, 44);
        System.out.println(res);
        System.out.println("---------binary search------------");
        searcher = new BinarySearcher();
        data = new int[]{0,1,2,3,4,5,6,7,8,9};
        res =searcher.search(data, 0);
        System.out.println(res);
        res =searcher.search(data, 9);
        System.out.println(res);
        res =searcher.search(data, 4);
        System.out.println(res);
        res =searcher.search(data, 5);
        System.out.println(res);
    }
}
