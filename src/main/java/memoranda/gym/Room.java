package main.java.memoranda.gym;

import main.java.memoranda.date.CalendarDate;
import java.util.Collection;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.gym.GymClass;

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

}
