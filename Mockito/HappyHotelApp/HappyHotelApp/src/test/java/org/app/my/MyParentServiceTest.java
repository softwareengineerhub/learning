package org.app.my;

import com.mockitotutorial.happyhotel.booking.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyParentServiceTest {
    private MyChildService myChildService;
    private MyParentService myParentService;

    @BeforeEach
    public void setup() {
        this.myChildService = mock(MyChildService.class);
        this.myParentService = new MyParentService(myChildService);
    }

    @Test
    public void checkPositiveCase() {

        when(myChildService.makeAction()).thenReturn("A").thenReturn("B");
        Map<String, String> map = myParentService.doAction();
        System.out.println(map);
        assertEquals(map.size(), 1);
        assertTrue(map.containsKey("A"));


        map = myParentService.doAction();
        System.out.println(map);
        assertEquals(map.size(), 1);
        assertTrue(map.containsKey("B"));
    }

    @Test
    public void checkNegativeCase() {
        when(myChildService.makeAction()).thenThrow(NullPointerException.class);
        try {
            Map<String, String> map = myParentService.doAction();
            fail();
        }catch(Exception ex){
            Map<String, Long> map = myParentService.statisticsMap;
            System.out.println(map);
            assertEquals(map.size(), 1);
            assertTrue(map.containsKey("npe"));
            assertTrue(map.get("npe")==1L);
        }
    }

    @Test
    public void checkNegativeCase2() {
        when(myChildService.makeAction()).thenThrow(ArrayIndexOutOfBoundsException.class);
        try {
            Map<String, String> map = myParentService.doAction();
            fail();
        }catch(Exception ex){
            Map<String, Long> map = myParentService.statisticsMap;
            System.out.println(map);
            assertEquals(map.size(), 1);
            assertTrue(map.containsKey("some-exp"));
            assertTrue(map.get("some-exp")==1L);
        }
    }

}
