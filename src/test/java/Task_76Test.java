package test.java;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.GymClassImpl;
import main.java.memoranda.util.Util;
import nu.xom.Element;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Tests the GymClassImpl class is working. should be able to pull the information stored in the Element
 *
 * tests the basic building of a gymClass object and testing if returning the information is correct.
 */
public class Task_76Test {

    GymClass class1;
    CalendarDate date;
    Element el;

    @Before
    public void setUp() throws Exception {

        date = new CalendarDate(11,4,2020,4,0,false);
        class1 = new GymClassImpl("Kicking 101","Public","White");
        class1.setDate(date);
        class1.setClassLength(60);
        class1.setSize(20);
        el = class1.getContent();
    }

    @Test
    public void classTest(){
        assertEquals("Kicking 101",class1.getName());
        class1.setName("Kicking 202");
        assertEquals("Kicking 202",class1.getName());
        assertEquals("Public",class1.getClassType());
        assertEquals("White",class1.getRank());
        assertEquals(60,class1.getClassLength());
        assertEquals(20,class1.getMaxSize());
        assertEquals(date.toString(),class1.getDate().toString());
        assertEquals(0,class1.getSize());
        assertEquals(Util.getTimeStamp(date),class1.getStartTime());
        assertFalse(class1.isFull());
        assertEquals(el.getAttribute("Name").getValue(),class1.getName());
    }

}
