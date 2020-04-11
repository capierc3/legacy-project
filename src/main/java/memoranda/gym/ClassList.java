package main.java.memoranda.gym;

import main.java.memoranda.date.CalendarDate;

import java.util.Collection;

/**
 * Interface for the ClassList methods.
 * @author Chase
 */
public interface ClassList{

    /**
     * Searches the list for a gym class by using the inputted ID
     * @param id String
     * @return User
     */
    GymClass getClass(String id);

    /**
     * Adds a class to the list
     * @param gymClass GymClass
     */
    void addClass(GymClass gymClass);

    /**
     * removes a class from a list with matching ID number
     * @param id String
     */
    void removeClass(String id);

    /**
     * finds the size of list.
     * @return int
     */
    int getSize();

    /**
     * returns the entire list
     * @return Collection
     */
    Collection<GymClass> getAllClasses();

    /**
     * returns a ClassList filled with classes of inputted rank
     * @param rank String
     * @return ClassList
     */
    ClassList getListByRank(String rank);

    /**
     * returns a ClassList filled with classes on an inputted date
     * @param date CalendarDate
     * @return ClassList
     */
    ClassList getListByDate(CalendarDate date);


}
