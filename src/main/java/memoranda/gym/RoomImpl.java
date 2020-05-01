package main.java.memoranda.gym;
import main.java.memoranda.date.CalendarDate;

import java.io.File;
import java.net.URISyntaxException;
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
    private File picture;

    private Element el;

    /**
     * Constructor for Room Element.
     */
    public RoomImpl(int roomNum, ClassList newList, Collection<CalendarDate> newClassDates) {
        el = new Element("Room");
        setAttr("RoomNumber", String.valueOf(roomNum));
        list = newList;
        if (list != null) {
            setClassList(list);
        }
        classDates = newClassDates;
        if (classDates != null) {
            setClassDates(classDates);
        }
        picture = null;
        setPicture(picture);
    }

    /**
     * Method to set class list for a Room.
     * @param list ClassList
     */
    public void setClassList(ClassList list) {
        el.appendChild(list.getContent().copy());
    }

    /**
     * Method to set Class Dates for Room.
     * @param classDates Collection
     */
    public void setClassDates(Collection<CalendarDate> classDates) {
        Element classList = new Element("ClassDates");
        for (CalendarDate date : classDates) {
            classList.appendChild(date.toString());
        }
        el.appendChild(classList);
    }

    /**
     * Gets a ClassList object of all the classes that are currently scheduled for the room.
     * @return ClassList
     */
    public ClassList getClasses() {
        return list;
    }

    /**
     * Add a class to the room's ClassList.
     * @param gymClass GymClass
     */
    public void addClass(GymClass gymClass) {
        list.addClass(gymClass);
        classDates.add(gymClass.getStartDate());
        setClassList(list);
        setClassDates(classDates);
    }

    /**
     * Getter for the room number.
     * @return int
     */
    public int getRoomNum() {
        return Integer.parseInt(el.getAttributeValue("RoomNumber"));
    }

    /**
     * Setter for the room number.
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
        for (CalendarDate d:classDates) {
            if (d.equals(date)){
                return false;
            }
        }
        return true;
    }

    /**
     * Method to return Room element.
     * @return
     */
    public Element getContent() {
        return el;
    }

    /**
     * Method to set attribute of a Room on the element.
     * @param a
     * @param value
     */
    private void setAttr(String a, String value) {
        Attribute attr = el.getAttribute(a);
        if (attr == null)
            el.addAttribute(new Attribute(a, value));
        else
            attr.setValue(value);
    }

    /**
     * Method to set the file for picture in Element.
     */
    public void setPicture(File fileName) {
        if (fileName == null) {
            try {
                fileName = new File(this.getClass().getResource("/ui/icons/redRoom.jpeg").toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        picture = fileName;
        setAttr("Picture", fileName.getPath());
    }

    /**
     * Method to set the Room's picture to file passed in.
     * @param newPicture File
     */
    public void setPic(File newPicture) {
        picture = newPicture;
        setPicture(picture);
    }

    /**
     * Method to return a Room's picture.
     * @return
     */
    public File getPic() {
        return picture;
    }
}
