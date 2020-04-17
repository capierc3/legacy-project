package test.java;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Black box test for GymClassList
 *
 * @author Chase
 */
public class Task_80BlackBox {

    GymClassImpl gymClass1;
    GymClassImpl gymClass2;
    GymClassImpl gymClass3;
    GymClassImpl gymClass4;
    GymClassImpl gymClass5;
    ClassListImpl classList;
    ArrayList<GymClass> classes;
    CalendarDate startDate1;
    CalendarDate endDate1;
    CalendarDate startDate2;
    CalendarDate endDate2;
    CalendarDate startDate3;
    CalendarDate endDate3;
    CalendarDate startDate4;
    CalendarDate endDate4;
    CalendarDate startDate5;
    CalendarDate endDate5;

    /**
     * sets up the variables for the tests.
     */
    @Before
    public void setUp() {

        startDate1 = new CalendarDate(17,4,2020,8,30,true);
        endDate1 = new CalendarDate(17,4,2020,9,30,true);
        startDate2 = new CalendarDate(18,4,2020,9,30,true);
        endDate2 = new CalendarDate(18,4,2020,9,30,true);
        startDate3 = new CalendarDate(17,4,2020,10,30,true);
        endDate3 = new CalendarDate(17,4,2020,11,30,true);
        startDate4 = new CalendarDate(19,4,2020,9,30,true);
        endDate4 = new CalendarDate(19,4,2020,9,30,true);
        startDate5 = new CalendarDate(20,4,2020,8,30,true);
        endDate5 = new CalendarDate(20,4,2020,9,30,true);

        classList = new ClassListImpl(new ArrayList<>());
        gymClass1 = new GymClassImpl("Test1","Public", Belt.WHITE,startDate1,endDate1);
        gymClass2 = new GymClassImpl("Test2","Public", Belt.YELLOW,startDate2,endDate2);
        gymClass3 = new GymClassImpl("Test3","Public", Belt.PURPLE,startDate3,endDate3);
        gymClass4 = new GymClassImpl("Test4","Public", Belt.BLUE,startDate4,endDate4);
        gymClass5 = new GymClassImpl("Test5","Public", Belt.BLUE_STRIPE,startDate5,endDate5);

        classes = new ArrayList<>();
        classes.add(gymClass1);
        classes.add(gymClass2);
        classes.add(gymClass3);
        classes.add(gymClass4);
        classes.add(gymClass5);
    }

    /**
     * checks sizes after adding and removing classes.
     */
    @Test
    public void addRemoveTest(){
        assertEquals(0,classList.getSize());
        classList.addClass(gymClass1);
        assertEquals(1,classList.getSize());
        classList.addClass(gymClass2);
        assertEquals(2,classList.getSize());
        classList.addClass(gymClass3);
        assertEquals(3,classList.getSize());
        classList.addClass(gymClass4);
        assertEquals(4,classList.getSize());
        classList.addClass(gymClass5);
        assertEquals(5,classList.getSize());

        classList.removeClass(gymClass5.getID());
        assertEquals(4,classList.getSize());
        classList.removeClass(gymClass4.getID());
        assertEquals(3,classList.getSize());
        classList.removeClass(gymClass3.getID());
        assertEquals(2,classList.getSize());
        classList.removeClass(gymClass2.getID());
        assertEquals(1,classList.getSize());
        classList.removeClass(gymClass1.getID());
        assertEquals(0,classList.getSize());
    }

    /**
     * Tests the get and set Collection methods.
     */
    @Test
    public void getSetTest(){
        classList.addClass(gymClass1);
        classList.addClass(gymClass2);
        classList.addClass(gymClass3);
        classList.addClass(gymClass4);
        classList.addClass(gymClass5);

        assertEquals(5,classList.getAllClasses().size());
        assertEquals(1,classList.getListByRank(Belt.BLUE).getSize());
        assertEquals(1,classList.getListByDate(startDate1).getSize());

        classList.setClassList(classes);
        assertEquals(5,classList.getSize());
    }

    /**
     * Makes sure nothing happens if you remove or add a class with no matching id.
     * Checks to see if it catches duplicate classes.
     * lastly
     */
    @Test
    public void errorTest(){
        classList.addClass(gymClass1);
        classList.removeClass("not class1's id");
        assertEquals(1,classList.getSize());
        assertNull(classList.getClass("not class1's id"));
        classList.addClass(gymClass1);
        assertEquals(1,classList.getSize());
    }




}
