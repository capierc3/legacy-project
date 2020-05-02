package test.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.Owner;
import main.java.memoranda.gym.OwnerImpl;
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


public class Task_120Test {

    Owner owner1;
    File filename;
    File secondFile;
    ClassList classList;
    GymClass class2;

    Date today = new Date();

    CalendarDate date3;

    Element element;

    /**
     * Test setup for Owner.
     * @throws Exception if null
     */
    @Before
    public void setUp() throws Exception {
        Collection<Note> noteList = null;
        classList = new ClassListImpl(new ArrayList<>());
        filename  = new File("test");
        secondFile = new File("");

        date3 = new CalendarDate(today);
        class2 = new GymClassImpl("Kicking 102", "Public", Belt.WHITE,
                date3, date3);
        classList.addClass(class2);
        owner1 = new OwnerImpl("Owner Ted", "4325", "ted@Owner.com", "BestOwner",
                Belt.WHITE, filename, noteList, classList);
        element = owner1.getContent();
    }

    /**
     * Test of getters and setters for Owner.
     */
    @Test
    public void classTest() {
        assertEquals("Owner Ted", owner1.getName());
        owner1.setName("The Real Ted");
        assertEquals("The Real Ted", owner1.getName());

        assertEquals("4325", owner1.getID());
        owner1.setID("1234");
        assertEquals("1234", owner1.getID());

        assertEquals("ted@Owner.com", owner1.getUserName());
        owner1.setUserName("ted@test.com");
        assertEquals("ted@test.com", owner1.getUserName());

        assertEquals("BestOwner", owner1.getPassword());
        owner1.setPassword("BestOwnerNA");
        assertEquals("BestOwnerNA", owner1.getPassword());

        assertEquals(filename, owner1.getPic());
        owner1.setPic(secondFile);
        assertEquals(secondFile, owner1.getPic());

        Assert.assertNotEquals(null, element);
    }
}
