package main.java.memoranda.gym;

import main.java.memoranda.date.CalendarDate;


import java.util.ArrayList;
import java.io.File;
import java.util.Collection;

import nu.xom.Element;
import nu.xom.Elements;

/**
 * Interface for the Room methods
 * @author Chase
 */
public interface Room {
    /**
     * Gets a ClassList object of all the classes that are currently scheduled for the room
     * @return ClassList
     */
    ClassList getClasses();

    /**
     * Sets ClassList in Room Element
     * @param list
     */
    void setClassList(ClassList list);

    /**
     * Sets ClassDates list in Room Element
     * @param classDates
     */
    void setClassDates(Collection<CalendarDate> classDates);

    /**
     * Add a class to the room's ClassList
     * @param gymClass GymClass
     */
    void addClass(GymClass gymClass);

    /**
     * Getter for the room number
     * @return int
     */
    int getRoomNum();

    /**
     * Setter for the room number
     * @param num int
     */
    void setRoomNum(int num);

    /**
     * Lets you know if a room is available on a given date and time.
     * @param date CalendarDate
     * @return boolean
     */
    boolean isAvailable(CalendarDate date);

    /**
     * Returns Room as an Element.
     * @return Element
     */
    Element getContent();

    static Room elmToRoom(Element el) {
        int num = Integer.parseInt(el.getAttributeValue("RoomNumber"));
        ClassList classList = ClassList.elmToClassList(el.getFirstChildElement("ClassList"));
        ArrayList<CalendarDate> classDates = new ArrayList<>();
        Elements elms = el.getFirstChildElement("ClassDates").getChildElements();
        for (int i = 0; i < elms.size(); i++) {
            classDates.add(new CalendarDate(elms.get(i).getLocalName()));
        }
        return new RoomImpl(num,classList,classDates);
    }

    /**
     * Method to set Picture and add picture to Element.
     * @param fileName File
     */
    void setPicture(File fileName);

    /**
     * Method to set a picture for a Room.
     * @param newPicture File
     */
    void setPic(File newPicture);

    /**
     * Method to get Picture for a Room.
     * @return File
     */
    File getPic();
}
