import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.Student;
import main.java.memoranda.gym.StudentImpl;
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


public class Task_75Test {

    Student student1;
    File filename;
    File secondFile;
    Collection<Note> noteList;
    ClassList classList;
    GymClass class1;

    CalendarDate date1;
    CalendarDate date2;

    Element element;

    /**
     * Test setup for Student.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        noteList = null;
        classList = new ClassListImpl(new ArrayList<>());
        class1 = new GymClassImpl("Kicking 101", "Public", Belt.WHITE);
        filename  = new File("test");
        secondFile = new File("");
        date1 = new CalendarDate(11, 4, 2020, 4, 0, false);
        date2 = new CalendarDate(11, 4, 2020, 4, 0, false);
        student1 = new StudentImpl("Student Ted", "4325", "ted@Student.com", "BestStudent",
                Belt.WHITE, filename, noteList, classList);
        element = student1.getContent();
    }

    /**
     * Test of getters and setters for Trainer
     */
    @Test
    public void classTest(){
        assertEquals("Student Ted", student1.getName());
        student1.setName("The Real Ted");
        assertEquals("The Real Ted", student1.getName());

        assertEquals("4325", student1.getID());
        student1.setID("1234");
        assertEquals("1234", student1.getID());

        assertEquals("ted@Student.com", student1.getUserName());
        student1.setUserName("ted@test.com");
        assertEquals("ted@test.com", student1.getUserName());

        assertEquals("BestStudent", student1.getPassword());
        student1.setPassword("BestStudentNA");
        assertEquals("BestStudentNA", student1.getPassword());

        assertEquals(filename, student1.getPic());
        student1.setPic(secondFile);
        assertEquals(secondFile, student1.getPic());

        student1.addClass(class1);
        assertEquals(1, student1.getClasses().getSize());

        Assert.assertNotEquals(null, element);
    }
}
