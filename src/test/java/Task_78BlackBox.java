package test.java;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Black box test for the room class
 *
 * @author Chase
  */
public class Task_78BlackBox {

    RoomImpl room1;
    GymClass gymClass1;
    CalendarDate startDate1;
    CalendarDate endDate1;

    /**
     * sets up the variables for the tests.
     */
    @Before
    public void setUp() {
        room1 = new RoomImpl(1,new ClassListImpl(new ArrayList<>()),new ArrayList<>());
        startDate1 = new CalendarDate(17,4,2020,8,30,true);
        endDate1 = new CalendarDate(17,4,2020,9,30,true);
        gymClass1 = new GymClassImpl("Test1","Public", Belt.WHITE,startDate1,endDate1);
    }

    /**
     * basic tests of the room1 object
     */
    @Test
    public void test(){
        assertEquals(0,room1.getClasses().getSize());
        room1.addClass(gymClass1);
        assertEquals(1,room1.getClasses().getSize());
        assertEquals(1,room1.getRoomNum());
        assertFalse(room1.isAvailable(startDate1));
        assertTrue(room1.isAvailable(new CalendarDate(20,4,2020,4,30,false)));
    }
}
