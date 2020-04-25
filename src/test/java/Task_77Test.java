import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.Trainer;
import main.java.memoranda.gym.TrainerImpl;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.Note;
import main.java.memoranda.gym.ClassListImpl;
import main.java.memoranda.date.CalendarDate;
import nu.xom.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class Task_77Test {

    Trainer trainer1;
    File filename;
    File secondFile;
    Collection<Note> noteList;
    ClassList classList;

    String description;

    Collection<CalendarDate> availability1 = new ArrayList<>();

    CalendarDate date1;
    CalendarDate date2;

    Element element;

    /**
     * Test setup for Trainer.
     * @throws Exception if Null
     */
    @Before
    public void setUp() throws Exception {
        noteList = null;
        classList = new ClassListImpl(new ArrayList<>());
        filename  = new File("test");
        secondFile = new File("");
        description = "Awesome, ripped trainer who has all the at-home exercises you need.";
        date1 = new CalendarDate(11, 4, 2020, 4, 0, false);
        date2 = new CalendarDate(11, 4, 2020, 4, 0, false);
        availability1.add(date1);
        trainer1 = new TrainerImpl("Tester Ted", "4325", "ted@test.com", "bestTesterNA",
                Belt.WHITE, filename, noteList, classList);
        trainer1.setDescription(description);
        element = trainer1.getContent();
    }

    /**
     * Test of getters and setters for Trainer.
     */
    @Test
    public void classTest() {
        assertEquals("Tester Ted", trainer1.getName());
        trainer1.setName("Trainer Ted");
        assertEquals("Trainer Ted", trainer1.getName());

        assertEquals("4325", trainer1.getID());
        trainer1.setID("1234");
        assertEquals("1234", trainer1.getID());

        assertEquals("ted@test.com", trainer1.getUserName());
        trainer1.setUserName("ted@trainer.com");
        assertEquals("ted@trainer.com", trainer1.getUserName());

        assertEquals("bestTesterNA", trainer1.getPassword());
        trainer1.setPassword("bestTrainerNA");
        assertEquals("bestTrainerNA", trainer1.getPassword());

        trainer1.setTrainingRank("Blue");
        assertEquals("Blue", trainer1.getTrainingRank());

        assertEquals(description, trainer1.getDescription());
        trainer1.setDescription("I make you train yourself.");
        assertEquals("I make you train yourself.", trainer1.getDescription());

        assertEquals(filename, trainer1.getPic());
        trainer1.setPic(secondFile);
        assertEquals(secondFile, trainer1.getPic());

        trainer1.setAvailability(availability1);
        assertEquals(1, trainer1.getAvailability().size());
        assertEquals(true, trainer1.isAvailable(date1));
        assertEquals(false, trainer1.isAvailable(date2));

        Assert.assertNotEquals(null, element);
    }
}
