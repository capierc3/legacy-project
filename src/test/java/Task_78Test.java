import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.Room;
import main.java.memoranda.gym.RoomImpl;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.GymClassImpl;
import main.java.memoranda.gym.ClassListImpl;
import main.java.memoranda.date.CalendarDate;
import nu.xom.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class Task_78Test {

    Room room1;
    Element element;
    ClassList classList;
    Collection<CalendarDate> classDates;
    CalendarDate date1;
    CalendarDate date2;
    GymClass class2;
    Date today = new Date();
    boolean isAvailable;

    /**
     * Test setup for Room.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        classList = new ClassListImpl(new ArrayList<>());
        classDates = new ArrayList<>();
        date1 = new CalendarDate(11, 4, 2020, 4, 0, false);
        classDates.add(date1);
        room1 = new RoomImpl(321, classList, classDates);
        element = room1.getContent();

        date2 = new CalendarDate(today);
        class2 = new GymClassImpl("Kicking 102", "Public", Belt.WHITE,
                date2, date2);
    }

    /**
     * Test of getters and setters for Room
     */
    @Test
    public void classTest(){

        room1.addClass(class2);
        assertEquals(1, room1.getClasses().getSize());
        assertEquals(321, room1.getRoomNum());
        room1.setRoomNum(123);
        assertEquals(123, room1.getRoomNum());
        assertEquals(classList, room1.getClasses());
        Assert.assertNotEquals(null, element);
        isAvailable = room1.isAvailable(date2);
        assertEquals(false, isAvailable);
    }
}
