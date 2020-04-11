package main.java.memoranda.gym;

import main.java.memoranda.date.CalendarDate;

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

    //Ideally once we retool the CalendarDate class we will have a CalendarDate object hold the following information.
    //Year, Month, Day, Hour, Minute.
    void setDate(CalendarDate date);
    CalendarDate getDate();
    String getStartTime();





}
