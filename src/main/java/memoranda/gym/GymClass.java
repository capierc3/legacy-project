package main.java.memoranda.gym;

import main.java.memoranda.date.CalendarDate;
import nu.xom.Element;

/**
 * Interface for the GymClass methods
 * @author Chase
 */
public interface GymClass {

    //Basic getters and setters
    String getName();
    void setName(String name);
    String getRank();
    void setRank(String rank);
    int getMaxSize();
    void setSize(int size);
    Room getRoom();
    void setRoom(Room room);
    String getID();
    void setID(String id);

    /**
     * Getter for the length in time the class will run for in minutes
     * @return double
     */
    int getClassLength();

    /**
     * Sets the length of the class in minutes
     */
    void setClassLength(int length);

    /**
     * Returns a UserList object filled with trainers
     * @return UserList
     */
    UserList getTrainers();

    /**
     * Add trainer to the UserList
     * @param trainer Trainer
     */
    void addTrainer(Trainer trainer);
    /**
     * Returns a UserList object filled with trainers
     * @return UserList
     */
    UserList getStudents();

    /**
     * Adds a student to a UserList of students
     * @param student Student
     */
    void addStudent(Student student);

    /**
     * Returns the amount of students in the class
     * @return int
     */
    int getSize();

    /**
     * Finds out if the class is at its max amount of students
     * @return boolean
     */
    boolean isFull();

    /**
     * gets the type of class; Public class, personal training, ect
     * @return String
     */
    String getClassType();

    /**
     * sets the type of class; Public class, personal training, ect
     * @param type String
     */
    void setClassType(String type);

    /**
     * Sets the CalendarDate for start of class.
     * @param date CalendarDate
     */
    void setStartDate(CalendarDate date);

    /**
     * Sets the CalendarDate for the end of class.
     * @param date CalendarDate
     */
    void setEndDate(CalendarDate date);

    /**
     * returns a CalendarDate Object for the start of the class
     * @return CalendarDate
     */
    CalendarDate getStartDate();

    /**
     * returns a CalendarDate Object for the end of the class
     * @return CalendarDate
     */
    CalendarDate getEndDate();

    /**
     * returns a string of the start time 00:00_AM/PM
     * @return String
     */
    String getStartTime();

    /**
     * returns the main Element used for the class.
     */
    Element getContent();




}
