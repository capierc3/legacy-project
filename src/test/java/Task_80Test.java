
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.GymClassImpl;
import main.java.memoranda.gym.ClassListImpl;
import main.java.memoranda.date.CalendarDate;
import nu.xom.Element;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class Task_80Test {
    Element element;
    ClassList classList;
    Collection<CalendarDate> classDates;
    Collection<GymClass> gymClasses;
    CalendarDate date1;
    CalendarDate date2;
    GymClass class1;
    GymClass class2;
    GymClass class3;
    Date today = new Date();
    boolean isAvailable;

    /**
     * Test setup for Gym ClassList.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        gymClasses = new ArrayList<>();
        classDates = new ArrayList<>();
        date1 = new CalendarDate(11, 4, 2020, 4, 0, false);
        classDates.add(date1);
        class1 = new GymClassImpl("Kicking 102", "Public", Belt.WHITE,
                date1,
                new CalendarDate(11, 4, 2020, 1, 45, false));
        date2 = new CalendarDate(today);
        class2 = new GymClassImpl("Kicking 102", "Public", Belt.WHITE,
                date2, date2);
        class3 = new GymClassImpl("Kicking 102", "Public", Belt.WHITE,
                date1,
                new CalendarDate(11, 4, 2020, 1, 45, false));
        gymClasses.add(class1);
        gymClasses.add(class2);
        classList = new ClassListImpl(gymClasses);

    }

    /**
     * Test of getters and setters for Gym ClassList.
     */
    @Test
    public void classTest(){
        assertEquals(2, classList.getSize());
        classList.addClass(class3);
        assertEquals(3, classList.getSize());
        classList.removeClass(class3.getID());
        assertEquals(2, classList.getAllClasses().size());
        assertEquals(class2, classList.getClass(class2.getID()));
        assertEquals(2, classList.getListByRank(Belt.WHITE).getSize());
        assertEquals(1, classList.getListByDate(date2).getSize());
        assertEquals(false, isAvailable);
    }

}
