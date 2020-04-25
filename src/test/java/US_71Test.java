package test.java;

import main.java.memoranda.date.CalendarDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;



/**
 * Class to test the US_71 branch.
 * US_71 was the retooling of CalendarDate.java 
 * to make it more of a one stop shop for all things date and time.
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
    CalendarDate shorty;

    Date toDate;
    CalendarDate testingConstructors;
    String stringDate;
    CalendarDate stringTester;
    CalendarDate maxDay;
    Date compareOne;
    Date compareTwo;
    Date shortDate;
    Boolean inPeriodTest;
    Boolean beforeNull;
    Boolean afterNull;
    Boolean equalsNull;
    CalendarDate nullBeforeDate;
    CalendarDate nullAfterDate;
    
    /**
     * Anything that needs to happen before a test is ran and before the next test
     * such as resetting a variable.
     */
    @Before
    public void setUp() throws Exception {
        am = new CalendarDate(21,6,1987,6,30,true);
        pm = new CalendarDate(21,6,1987,6,30,false);
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
        compareOne = am.getDate();
        compareTwo = pm.getDate();
        shorty = new CalendarDate(7,7,1982);

        
    }


    /*
     * Anything that needs to happen after a test is ran and before the next test
     * such as resetting a variable
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test to make sure the AM PM setting is working and 
     * .before and .after is working with time added.
     */
    @Test
    public void aMpMTest() {
        assertTrue(am.before(pm));
        assertFalse(pm.before(am));
        assertTrue(pm.after(am));
        assertFalse(am.after(pm));
    }

    /**
     * Test to check cases where times are different by a small amount.
     */
    @Test
    public void preciseBeforeAfter() {
        assertTrue(before.before(startTime));
        assertTrue(after.after(startTime));
    }

    /**
     * checks that a CalendarDate without a time set still can be compared to one with a time set.
     */
    @Test
    public void noTimeAndTimeSet() {
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
    public void inPeriodTestWithTime() {
        //appt1 should equal start time, appt2 should be in between, 
        // and appt3 should be equal to the end time.
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
     * Tests the time stamp out put with times and no times.
     */
    @Test
    public void timeStamp() {
        assertEquals(appt1Stamp,appt1.toString());
        assertEquals(noTimeStamp,noTimeEqual.toString());
    }

    /**
     * A new CalendarDate object with no inputs should be set to current time.
     */
    @Test
    public void today() {
        assertTrue(CalendarDate.yesterday().isHourSet());
        assertTrue(CalendarDate.today().isHourSet());
        assertTrue(CalendarDate.tomorrow().isHourSet());
        assertTrue(now.isHourSet());
    }
    
    /**
     * Tests to ensure if date is over the max days for the month, it defaults to max day.
     */
    @Test
    public void maxDay() {
    	maxDay = new CalendarDate(32, 11, 2024);
    	assertNotEquals(maxDay, 32);
    }
    
    /**
     * Tests the compares object function (not the date one).
     */
    @Test
    public void compareObjects() {
    	assertTrue(compareOne.equals(compareOne));
    	assertFalse(compareOne.equals(compareTwo));
    }
    
    /**
     * Tests the get date functionality.
     */
    @Test
    public void shortDateGetDate() {
    	shortDate = shorty.getDate();
    	assertNotNull(shortDate);
    }
    
    /**
     * Tests the functionality of returning false to see if date is in period.
     */
    @Test
    public void inPeriodReturn() {
    	inPeriodTest = shorty.inPeriod(startTime, endTime);
    	assertFalse(inPeriodTest);
    }
    
    /**
     * Testing the functionality of other getters in setters within the Calendar Date class.
     */
    @Test
    public void toDateTest() throws Exception {
    	toDate = am.getDate();
    	testingConstructors = new CalendarDate(toDate);
    	stringDate = testingConstructors.toString();
    	stringTester = new CalendarDate(stringDate); 
    	assertNotNull(testingConstructors);
    	assertNotNull(toDate);
    	assertNotNull(stringDate);
    	assertNotNull(stringTester);

    }
    
    /**
     * Checking to see if before and after null returns work.
     */
    @Test
    public void beforeAndAfterNull() {
    	beforeNull = am.before(nullBeforeDate);
    	afterNull = am.after(nullAfterDate);
    	equalsNull = am.equals(nullBeforeDate);
    	assertFalse(equalsNull);
    	assertTrue(beforeNull);
    	assertTrue(afterNull);
    }

}
