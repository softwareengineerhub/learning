package com.app.ch03.generics.mylinkedlist;

import com.app.ch03.generics.MyList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MyLinkedListImpl<T> implements MyList<T> {

    private int n;
    private Node<T> root;
    private long version;

    @Override
    public void add(T item) {
        version++;
        if(root==null){
            root = new Node(item);
            n++;
            return;
        }
        Node tmp = root;
        /*while(true){
            if(tmp.next==null){
                break;
            }
            tmp = tmp.next;
        }*/

        while(tmp.next!=null){
            tmp=tmp.next;
        }

        Node nodeItem = new Node(item);
        tmp.next = nodeItem;
        nodeItem.prev = tmp;
        n++;
    }

    @Override
    public T get(int index) {
        Node<T> tmp = root;
        int step = 0;
        while(step!=index && tmp.next!=null){
            tmp = tmp.next;
            step++;
        }
        return tmp.value;
    }

    @Override
    public T set(int index, T value) {
        Node<T> tmp = root;
        int step = 0;
        while(step!=index && tmp.next!=null){
            tmp = tmp.next;
            step++;
        }
        T oldValue = tmp.value;
        tmp.value = value;
        return oldValue;
    }

    @Override
    public void remove(int index) {
        version++;
        Node tmp = root;
        int step = 0;
        while(step!=index && tmp!=null){
            tmp = tmp.next;
            step++;
        }

        Node prev=tmp.prev;
        Node next=tmp.next;

        if(next==null){
            prev.next=null;
            tmp.prev=null;
            n--;
            return;
        }

        if(prev==null){
            //root.next = null;
            tmp.prev=null;
            root = tmp.next;
            n--;
            return;
        }

        prev.next=next;
        next.prev=prev;
        n--;
        return;
    }

    @Override
    public boolean isEmpty() {
        return n==0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int n;
            private Node<T> itrRoot = root;
            private long version;

            @Override
            public boolean hasNext() {
                return n < MyLinkedListImpl.this.n;
            }

            @Override
            public T next() {
                if(version!= MyLinkedListImpl.this.version){
                    throw new ConcurrentModificationException();
                }
                T value = itrRoot.value;
                n++;
                itrRoot = itrRoot.next;
                return value;
            }
        };
    }



    private class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
