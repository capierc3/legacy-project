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
    Belt getRank();
    void setRank(Belt rank);
    int getMaxSize();
    void setSize(int size);
    Room getRoom();
    void setRoom(Room room);
    String getID();
    void setID(String id);

    /**
     * Finds the length in time the class will run for in minutes from the set start and end dates
     * @return double
     */
    int getClassLength();

    /**
     * Returns a UserList object filled with trainers
     * @return UserList
     */
    Trainer getTrainer();

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
     * removes a student from the Gym Class element
     * @param student Student
     */
    void removeStudent(Student student);

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

    static GymClass elmToGymClass(Element el) {
        String name = el.getAttributeValue("Name");
        String type = el.getAttributeValue("Type");
        Belt rank = Belt.getBelt(Integer.parseInt(el.getAttributeValue("Rank")));
        CalendarDate start = new CalendarDate(el.getAttributeValue("StartDate"));
        CalendarDate end = new CalendarDate(el.getAttributeValue("EndDate"));
        return new GymClassImpl(name,type,rank,start,end);
    }




}
