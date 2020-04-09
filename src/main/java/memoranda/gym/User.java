package main.java.memoranda.gym;

import java.io.File;
import java.util.Collection;

/**
 * Interface for the User methods which will be extended to Trainer, Student and Owner
 */
public interface User {

    //All pretty basic getters and setters
    String getName();
    void setName();
    String getID();
    void setID();
    String getUserName();
    void setUserName();
    String getPassword();
    void setPassword();
    String getBelt();
    void setBelt();
    File getPic();
    void setPic();
    Collection getNotes();
    void addNote();

    /**
     * Returns a ClassList filled with any events for that day, trainers and owners will see a list of classes they teach,
     * Students see ones they are registered for.
     * @return ClassList
     */
    ClassList getTodaysEvents();

}
