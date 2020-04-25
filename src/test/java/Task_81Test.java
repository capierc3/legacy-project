package test.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.User;
import main.java.memoranda.gym.UserImpl;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.Note;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.GymClassImpl;
import main.java.memoranda.gym.ClassListImpl;
import main.java.memoranda.date.CalendarDate;
import nu.xom.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class Task_81Test {

    User user1;
    File filename;
    File secondFile;
    //Collection<Note> noteList;
    ClassList classList;
    GymClass class1;
    GymClass class2;

    Date today = new Date();

    CalendarDate date3;

    Element element;

    /**
     * Test setup for User.
     * @throws Exception if Null
     */
    @Before
    public void setUp() throws Exception {
        Collection<Note> noteList = null;
        classList = new ClassListImpl(new ArrayList<>());
        class1 = new GymClassImpl("Kicking 101", "Public", Belt.WHITE);
        filename  = new File("test");
        secondFile = new File("");

        date3 = new CalendarDate(today);
        class2 = new GymClassImpl("Kicking 102", "Public", Belt.WHITE,
                date3, date3);
        //classList.addClass(class2);
        user1 = new UserImpl("User Ted", "4325", "ted@User.com", "BestUser",
                Belt.WHITE, filename, noteList, classList, "User");
        element = user1.getContent();
    }

    /**
     * Test of getters and setters for User.
     */
    @Test
    public void classTest() {
        assertEquals("User Ted", user1.getName());
        user1.setName("The Real Ted");
        assertEquals("The Real Ted", user1.getName());

        assertEquals("4325", user1.getID());
        user1.setID("1234");
        assertEquals("1234", user1.getID());

        assertEquals("ted@User.com", user1.getUserName());
        user1.setUserName("ted@test.com");
        assertEquals("ted@test.com", user1.getUserName());

        assertEquals("BestUser", user1.getPassword());
        user1.setPassword("BestUserNA");
        assertEquals("BestUserNA", user1.getPassword());

        assertEquals(filename, user1.getPic());
        user1.setPic(secondFile);
        assertEquals(secondFile, user1.getPic());

        assertEquals(0, user1.getTodaysEvents().getSize());
        user1.addClass(class2);
        assertEquals(1, user1.getTodaysEvents().getSize());
        user1.addClass(class1);
        assertEquals(2, user1.getAllClasses().getSize());
        user1.removeClass(class1);
        assertEquals(1, user1.getAllClasses().getSize());
        Assert.assertNotEquals(null, element);
    }
}
