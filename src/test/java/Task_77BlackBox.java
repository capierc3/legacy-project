package test.java;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Black box testing for the Trainer class
 */
public class Task_77BlackBox {


    User trainer1;
    User student1;
    GymClass gymClass1;
    GymClass gymClass2;

    /**
     * sets up the vars for testing
     */
    @Before
    public void setUp() {
        trainer1 = new TrainerImpl("Marky Mark","MM2","MarkyMark89","Oh_Hi_Mark",
                Belt.BLACK3,new File(""),new ArrayList<>(),
                new ClassListImpl(new ArrayList<>()));
        gymClass1 = new GymClassImpl("Test","Public", Belt.BLUE,new CalendarDate(),new CalendarDate());
        gymClass2 = new GymClassImpl("Test","Public", Belt.BLUE,CalendarDate.tomorrow(),CalendarDate.tomorrow());
        student1 = new StudentImpl("Ben Affleck","BA1","Batfleck16","Martha!!",
                Belt.YELLOW,new File(""),new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
    }


    @Test
    public void trainerTest() {
        assertEquals("Marky Mark",trainer1.getName());
        assertEquals("MM2",trainer1.getID());
        assertEquals("MarkyMark89",trainer1.getUserName());
        assertEquals("Oh_Hi_Mark",trainer1.getPassword());
        assertEquals(Belt.BLACK3,trainer1.getBelt());
        assertEquals(0,trainer1.getTodaysEvents().getSize());
        assertEquals(0,trainer1.getAllClasses().getSize());
        trainer1.addClass(gymClass1);
        trainer1.addClass(gymClass2);
        assertEquals(1,trainer1.getTodaysEvents().getSize());
        assertEquals(2,trainer1.getAllClasses().getSize());
        trainer1.removeClass(gymClass1);
        assertEquals(0,trainer1.getTodaysEvents().getSize());
        assertEquals(1,trainer1.getAllClasses().getSize());
    }

    @Test
    public void studentTest() {
        assertEquals("Ben Affleck",student1.getName());
        assertEquals("BA1",student1.getID());
        assertEquals("Batfleck16",student1.getUserName());
        assertEquals("Martha!!",student1.getPassword());
        assertEquals(Belt.YELLOW,student1.getBelt());
        assertEquals(0,student1.getTodaysEvents().getSize());
        assertEquals(0,student1.getAllClasses().getSize());
        student1.addClass(gymClass1);
        student1.addClass(gymClass2);
        assertEquals(1,student1.getTodaysEvents().getSize());
        assertEquals(2,student1.getAllClasses().getSize());
        student1.removeClass(gymClass1);
        assertEquals(0,student1.getTodaysEvents().getSize());
        assertEquals(1,student1.getAllClasses().getSize());
    }

}
