package com.app.ch03.mylinkedlist;

import com.app.ch03.MyList;

import java.nio.charset.CoderMalfunctionError;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class MyLinkedListImpl implements MyList {

    private int n;
    private Node root;
    private long version;

    @Override
    public void add(Object item) {
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
    public Object get(int index) {
        Node tmp = root;
        int step = 0;
        while(step!=index && tmp.next!=null){
            tmp = tmp.next;
            step++;
        }
        return tmp.value;
    }

    @Override
    public Object set(int index, Object value) {
        Node tmp = root;
        int step = 0;
        while(step!=index && tmp.next!=null){
            tmp = tmp.next;
            step++;
        }
        Object oldValue = tmp.value;
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
    public Iterator iterator() {
        return new Iterator() {
            private int n;
            private Node itrRoot = root;
            private long version;

            @Override
            public boolean hasNext() {
                return n < MyLinkedListImpl.this.n;
            }

            @Override
            public Object next() {
                if(version!=MyLinkedListImpl.this.version){
                    throw new ConcurrentModificationException();
                }
                Object value = itrRoot.value;
                n++;
                itrRoot = itrRoot.next;
                return value;
            }
        };
    }



    private class Node {
        private Object value;
        private Node prev;
        private Node next;

        public Node(Object value) {
            this.value = value;
        }

        public Node(Object value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
