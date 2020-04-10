package test.java;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.util.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Class to test the US_71 branch.
 * US_71 was the retooling of CalendarDate.java to make it more of a one stop shop for all things date and time.
 * @author Chase
 */
public class US_71Test {

    CalendarDate am;
    CalendarDate pm;
    CalendarDate before;
    CalendarDate after;
    CalendarDate startTime;
    CalendarDate endTime;
    CalendarDate noTimeEqual;
    CalendarDate noTimeBefore;
    CalendarDate noTimeAfter;
    CalendarDate appt1;
    CalendarDate appt2;
    CalendarDate appt3;
    String appt1Stamp;
    String noTimeStamp;
    String calStamp;
    Calendar cal;
    CalendarDate now;


    @Before
    public void setUp() throws Exception {
        am = new CalendarDate(6,21,1987,6,30,true);
        pm = new CalendarDate(6,21,1987,6,30,false);
        before = new CalendarDate(10,4,2020,6,29,true);
        startTime = new CalendarDate(10,4,2020,6,30,true);
        endTime = new CalendarDate(10,4,2020,7,30,true);
        after = new CalendarDate(10,4,2020,6,31,true);
        noTimeEqual = new CalendarDate(10,4,2020);
        noTimeBefore = new CalendarDate(10,3,2020);
        noTimeAfter = new CalendarDate(10,5,2020);
        appt1 = new CalendarDate(10,4,2020,6,30,true);
        appt2 = new CalendarDate(10,4,2020,7,0,true);
        appt3 = new CalendarDate(10,4,2020,7,30,true);
        appt1Stamp = "10/4/2020/6:30_AM";
        noTimeStamp = "10/4/2020";
        now = new CalendarDate();
        cal = appt1.getCalendar();
    }


    /*
     * Anything that needs to happen after a test is ran and before the next test
     * such as resetting a variable
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test to make sure the AM PM setting is working and .before and .after is working with time added.
     */
    @Test
    public void AMPMTest(){
        assertTrue(am.before(pm));
        assertFalse(pm.before(am));
        assertTrue(pm.after(am));
        assertFalse(am.after(pm));
    }

    /**
     * Test to check cases where times are different by a small amount.
     */
    @Test
    public void preciseBeforeAfter(){
        assertTrue(before.before(startTime));
        assertTrue(after.after(startTime));
    }

    /**
     * checks that a CalendarDate without a time set still can be compared to one with a time set.
     */
    @Test
    public void noTimeAndTimeSet(){
        assertTrue(startTime.after(noTimeBefore));
        assertTrue(startTime.equals(noTimeEqual));
        assertTrue(startTime.before(noTimeAfter));
        assertTrue(noTimeBefore.before(startTime));
        assertTrue(noTimeEqual.equals(startTime));
        assertTrue(noTimeAfter.after(startTime));
    }

    /**
     * Tests the inPeriod() with CalendarDate objects with times set to ones without and with time.
     */
    @Test
    public void inPeriodTestWithTime(){
        //appt1 should equal start time, appt2 should be in between, and appt3 should be equal to the end time.
        assertTrue(appt1.inPeriod(startTime,endTime));
        assertTrue(appt2.inPeriod(startTime,endTime));
        assertTrue(appt3.inPeriod(startTime,endTime));
        //noTimeEqual has no time set and would equal an all day event so it would be in the period
        assertTrue(noTimeEqual.inPeriod(startTime,endTime));
        //appt2 is a date with time set that takes place during the all day event of noTimeEqual
        //all three should be true
        assertTrue(appt2.inPeriod(noTimeBefore,noTimeEqual));
        assertTrue(appt2.inPeriod(noTimeBefore,noTimeAfter));
        assertTrue(appt2.inPeriod(noTimeEqual,noTimeAfter));
    }

    /**
     * Tests the time stamp out put with times and no times
     */
    @Test
    public void timeStamp(){
        assertEquals(appt1Stamp,appt1.toString());
        assertEquals(noTimeStamp,noTimeEqual.toString());
    }

    /**
     * A new CalendarDate object with no inputs should be set to current time.
     */
    @Test
    public void today(){
        assertTrue(CalendarDate.yesterday().isHourSet());
        assertTrue(CalendarDate.today().isHourSet());
        assertTrue(CalendarDate.tomorrow().isHourSet());
        assertTrue(now.isHourSet());
    }


}
