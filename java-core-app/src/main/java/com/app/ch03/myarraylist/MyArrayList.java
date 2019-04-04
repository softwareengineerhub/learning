package com.app.ch03.myarraylist;

import java.nio.charset.CoderMalfunctionError;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MyArrayList implements MyList {
    private Object[] data;
    private int n;
    private int capacity;
    private long version;

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity];
    }

    public MyArrayList() {
        this(10);
    }

    public void add(Object item) {
        if(n+1>=data.length){
            Object[] tmp = new Object[data.length+capacity];
            /*for(int i=0;i<data.length;i++){
                tmp[i] = data[i];
            }*/

            System.arraycopy(data, 0, tmp, 0, data.length);
            data = tmp;

            //data=Arrays.copyOf(data, data.length+capacity);
        }

        data[n] = item;
        n++;
        version++;
    }

    public Object get(int index) {
        return data[index];
    }

    public Object set(int index, Object value) {
        Object oldValue = data[index];
        data[index]=value;
        return oldValue;
    }

    public void remove(int index) {
        for(int i=index;i<n-1;i++){
            data[i]=data[i+1];
        }
        data[n-1]=null;
        n--;
        version++;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator iterator() {

        return new Iterator() {
            private int iteratorN;
            //private long version = MyArrayList.this.version;
            private long v = version;
            //private Object lastSelectedItem;


            @Override
            public boolean hasNext() {
                return iteratorN<n;
            }

            @Override
            public Object next() {
                if(v!=version){
                    throw new ConcurrentModificationException("iterator v:"+v+"; class version:"+version);
                }
                return data[iteratorN++];
            }

            @Override
            public void remove() {
                if(iteratorN==0){
                    throw new RuntimeException("pLEASE SELCT OBJECT  BEFORE remove");
                }
                MyArrayList.this.remove(iteratorN);
                v++;
            }
        };
    }
}
