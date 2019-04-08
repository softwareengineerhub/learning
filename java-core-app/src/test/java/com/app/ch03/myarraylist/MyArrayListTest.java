package com.app.ch03.myarraylist;

import com.app.ch03.MyList;
import org.junit.Test;

import java.util.ConcurrentModificationException;

import static org.junit.Assert.*;

public class MyArrayListTest {

    private MyList myList = new MyArrayList();

    @Test(expected = ConcurrentModificationException.class)
    public void concurrentModification(){

    }



    @Test(expected = NullPointerException.class, timeout = 1000L)
    public void simpleTest(){
            String s = null;
            s.trim();
    }

    @Test
    public void simpleTest2(){
        try {
            String s = null;
            s.trim();
            fail();
        }catch (NullPointerException ex){

        }
    }

    @Test
    public void addTest(){
        myList.add("A");
        myList.add("B");
        myList.add("C");
        assertEquals("A", myList.get(0));
        assertTrue(myList.size()==3);
    }

    @Test
    public void getTest(){
        myList.add("A");
        myList.add("B");
        myList.add("C");

        for(Object item: myList){
            System.out.println(item);
        }
     /*   assertEquals("A", myList.get(0));
        assertEquals("A", myList.get(0));
        assertEquals("A", myList.get(0));
        assertTrue(myList.size()==3);*/
    }

}
