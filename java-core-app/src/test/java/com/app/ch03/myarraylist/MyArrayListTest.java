package com.app.ch03.myarraylist;

import com.app.ch03.MyList;
import org.junit.*;

import java.util.ConcurrentModificationException;

import static org.junit.Assert.*;

public class MyArrayListTest {

    private MyList myList = new MyArrayList();

    @BeforeClass
    public static void setUp(){

    }

    @Before
    public void init(){
        myList.add("A");
        myList.add("B");
        myList.add("C");
        myList.add("D");
        myList.add("E");
    }


    @Test(expected = ConcurrentModificationException.class)
    //@Test
    public void concurrentModification(){
        for(Object item: myList){
            if("C".equals(item)){
                myList.add("QQQQ");
            }
        }
    }

    @After
    public void destroy(){

    }

    @AfterClass
    public static void tearDown(){

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
        //myList.add("A");
        //myList.add("B");
        //myList.add("C");
        assertEquals("A", myList.get(0));
        assertTrue(myList.size()==5);
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
