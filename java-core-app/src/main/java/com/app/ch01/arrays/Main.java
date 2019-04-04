package com.app.ch01.arrays;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Object[] items = null;
        items = new Object[10];

        int[] array = new int[10];
        int a = array[0];
        System.out.println("a="+a);
        a = array[1];
        System.out.println("a="+a);
        a = array[2];
        System.out.println("a="+a);
        a = array[3];
        System.out.println("a="+a);
        a = array[4];
        System.out.println("a="+a);
        a = array[5];
        System.out.println("a="+a);
        a = array[6];
        System.out.println("a="+a);
        a = array[7];
        System.out.println("a="+a);
        a = array[8];
        System.out.println("a="+a);
        a = array[9];
        System.out.println("a="+a);
        //a = array[10];
        //System.out.println("a="+a);
        System.out.println("--------------------------------");
        for(int i=0;i<array.length;i++){
            System.out.println("a["+i+"]="+array[i]);
        }
        System.out.println("--------------------------------");
        System.out.println(array);
        System.out.println("--------------------------------");
        System.out.print("[");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+",");
        }
        System.out.print("]");
        System.out.println("--------------------------------");
        for(int value: array){
            System.out.println(value);
        }
        System.out.println("--------------------------------");
        array[0]=0;
        array[1]=1;
        array[2]=2;
        for(int value: array){
            System.out.println(value);
        }
        System.out.println("--------------------------------");
        array = new int[]{0,1,2,3,4,5,6,7,8,9};
        for(int value: array){
            System.out.println(value);
        }
        System.out.println("--------------------------------");
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]*2);
        }
        System.out.println("--------------------------------");
        int[] b = {1,2,3};
        int[] c = {1,2,3};
        System.out.println("b="+b);
        System.out.println("c="+c);
        System.out.println("b="+ Arrays.toString(b));
        System.out.println("c="+ Arrays.toString(c));
        //b = null;

        System.out.println(String.format("b==c is %s", b==c));
        System.out.println(String.format("b.hashCode=%s", b.hashCode()));
        System.out.println(String.format("c.hashCode=%s", c.hashCode()));
        System.out.println(String.format("b.equals(c) is %s", b.equals(c)));

        //System.out.println(String.format("b==c is %s", b==c));
        System.out.println(String.format("Arrays.b.hashCode=%s", Arrays.hashCode(b)));
        System.out.println(String.format("Arrays.c.hashCode=%s", Arrays.hashCode(c)));
        System.out.println(String.format("b.Arrays.equals(c) is %s", Arrays.equals(b,c)));
        System.out.println("b="+ Arrays.toString(b));
        System.out.println("c="+ Arrays.toString(c));

        int[] data = {9,2,6,0,-1};
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));

    }
}
