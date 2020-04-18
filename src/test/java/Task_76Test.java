package test.java;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.Belt;
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
 * Static analysis of the class done on April 16th. 
 */
public class Task_76Test {

    GymClass class1;
    GymClass class2;
    GymClass class3;
    GymClass class4;
    CalendarDate startDate;
    CalendarDate endDate;
    Element el;

    GymClass constructor1;
    GymClass constructor2;
    GymClass constructor3;

    /**
     * sets up the vars for testing
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        startDate = new CalendarDate(11, 4, 2020, 4, 0, false);
        endDate = new CalendarDate(11, 4, 2020, 5, 0, false);
        class1 = new GymClassImpl("Kicking 101", "Public", Belt.WHITE);
        class1.setStartDate(startDate);
        class1.setEndDate(endDate);
        class1.setSize(20);
        class2 = new GymClassImpl("Kicking 102", "Public", Belt.WHITE,
                new CalendarDate(11, 4, 2020, 4, 45, false),
                new CalendarDate(11, 4, 2020, 5, 0, false));
        class3 = new GymClassImpl("Kicking 102", "Public", Belt.WHITE,
                new CalendarDate(11, 4, 2020, 11, 45, true),
                new CalendarDate(11, 4, 2020, 1, 45, false));
        class4 =  new GymClassImpl("Kicking 102", "Public", Belt.WHITE,
                new CalendarDate(11, 4, 2020, 11, 45, true),
                new CalendarDate(11, 4, 2020, 12, 15, false));
        el = class1.getContent();

        /*
        * Blackbox Setup
        */
        constructor1 = new GymClassImpl("Kung Foo Fighting Advanced", "private");
        constructor2 = new GymClassImpl("Kung Foo Fighting Intro", "public", Belt.BLUE);
        constructor3 = new GymClassImpl("Kung Foo Fighting Double Advanced", "public", Belt.BLACK1, startDate, endDate);
    }

    /**
     * Tests that everything is saved properly in the GymClass Element
     */
    @Test
    public void classTest(){
        assertEquals("Kicking 101",class1.getName());
        class1.setName("Kicking 202");
        assertEquals("Kicking 202",class1.getName());
        assertEquals("Public",class1.getClassType());
        assertEquals(Belt.WHITE,class1.getRank());
        assertEquals(20,class1.getMaxSize());
        assertTrue(startDate.equals(class1.getStartDate()));
        assertTrue(endDate.equals(class1.getEndDate()));
        assertEquals(0,class1.getSize());
        assertEquals(Util.getTimeStamp(startDate),class1.getStartTime());
        assertFalse(class1.isFull());
        assertEquals(el.getAttribute("Name").getValue(),class1.getName());
    }

    /**
     * Test to make sure the class length is calculated properly.
     */
    @Test
    public void ClassLengthTest(){
        assertEquals(60,class1.getClassLength());
        assertEquals(15,class2.getClassLength());
        assertEquals(120,class3.getClassLength());
        assertEquals(30,class4.getClassLength());
    }

    /**
    *Blackbox testing
    * Most of this is tested above, but I'll test again just for the practice of blackbox testing
    */
    @Test
    public void classExists(){
        assertNotNull(constructor1);
        assertNotNull(constructor2);
        assertNotNull(constructor3);
    }

    @Test
    public void correctName(){
        assertEquals("Kung Foo Fighting Advanced",constructor1.getName());
        assertEquals("Kung Foo Fighting Intro",constructor2.getName());
        assertEquals("Kung Foo Fighting Double Advanced",constructor3.getName());

    }

    public void setName(){
        constructor1.setName("KFFA");
        constructor2.setName("KFFI");
        constructor3.setName("KFFDA");
        assertEquals("KFFA",constructor1.getName());
        assertEquals("KFFI",constructor2.getName());
        assertEquals("KFFDA",constructor3.getName());
    }

}
