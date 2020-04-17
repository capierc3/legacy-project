package main.java.memoranda.gym;
import main.java.memoranda.date.CalendarDate;
import java.util.Collection;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.Room;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;

/**
 * Interface for the Room methods
 * @author Daimi Mussey
 */
public class RoomImpl implements Room{
    private ClassList list;
    private Collection<CalendarDate> classDates;

    private Element el;

    /**
     * Constructor for Room Element
     */
    public RoomImpl(int roomNum, ClassList newList, Collection<CalendarDate> newClassDates) {
        el = new Element("Room");
        setAttr("RoomNumber", String.valueOf(roomNum));
        list = newList;
        classDates = newClassDates;
        setClassList(list);
        setClassDates(classDates);

    }

    public void setClassList(ClassList list) {
        el.appendChild(list.getContent());
    }

    public void setClassDates(Collection<CalendarDate> classDates) {
        Element classList = new Element("ClassDates");
        for (CalendarDate date : classDates) {
            classList.appendChild(date.toString());
        }
        el.appendChild(classList);
    }

    /**
     * Gets a ClassList object of all the classes that are currently scheduled for the room
     * @return ClassList
     */
    public ClassList getClasses() {
        return list;
    }

    /**
     * Add a class to the room's ClassList
     * @param gymClass GymClass
     */
    public void addClass(GymClass gymClass) {
        list.addClass(gymClass);
        classDates.add(gymClass.getStartDate());
        setClassList(list);
        setClassDates(classDates);
    }

    /**
     * Getter for the room number
     * @return int
     */
    public int getRoomNum() {
        return Integer.parseInt(el.getAttributeValue("RoomNumber"));
    }

    /**
     * Setter for the room number
     * @param num int
     */
    public void setRoomNum(int num) {
        setAttr("RoomNumber", String.valueOf(num));
    }

    /**
     * Lets you know if a room is available on a given date and time.
     * @param date CalendarDate
     * @return boolean
     */
    public boolean isAvailable(CalendarDate date) {
        boolean result = false;

        if (!classDates.isEmpty()) {
            if(classDates.contains(date)) {
                result = true;
            }
        }
        return result;
    }

    public Element getContent() {
        return el;
    }

    private void setAttr(String a, String value) {
        Attribute attr = el.getAttribute(a);
        if (attr == null)
            el.addAttribute(new Attribute(a, value));
        else
            attr.setValue(value);
    }

}
