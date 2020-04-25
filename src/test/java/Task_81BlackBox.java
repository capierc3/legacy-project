package test.java;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.Trainer;
import main.java.memoranda.gym.TrainerImpl;
import main.java.memoranda.gym.Room;
import main.java.memoranda.gym.RoomImpl;
import main.java.memoranda.gym.Student;
import main.java.memoranda.gym.StudentImpl;
import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.ClassListImpl;
import main.java.memoranda.gym.GymClassImpl;
import main.java.memoranda.gym.UserImpl;
import main.java.memoranda.gym.ClassList;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Black box test for the UserImpl.
 *
 * @author Chase
 */
public class Task_81BlackBox {

    UserImpl user1;
    ClassList classList;
    GymClass gymClass;

    /**
     * sets up the variables for the tests.
     */
    @Before
    public void setUp() {
        classList = new ClassListImpl(new ArrayList<>());
        gymClass = new GymClassImpl("Test","Public",
                Belt.BLUE,new CalendarDate(),new CalendarDate());
        classList.addClass(gymClass);
        user1 = new UserImpl("Rex Kwon","RK001","RexKwon32","Password",
                Belt.BLACK3,new File(""),new ArrayList<>(),
                new ClassListImpl(new ArrayList<>()), "User");
    }

    @Test
    public void test() {
        assertEquals("Rex Kwon",user1.getName());
        assertEquals("RK001",user1.getID());
        assertEquals("RexKwon32",user1.getUserName());
        assertEquals("Password",user1.getPassword());
        assertEquals(Belt.BLACK3,user1.getBelt());
        assertEquals(0,user1.getNotes().size());
        assertEquals(0,user1.getTodaysEvents().getSize());
    }


}
