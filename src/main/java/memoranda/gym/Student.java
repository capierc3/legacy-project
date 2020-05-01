package main.java.memoranda.gym;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.User;
import nu.xom.Element;

import java.io.Serializable;

/**
 * Interface for the methods used for the Student class
 * @author Chase
 */
public interface Student extends User, Serializable {

    /**
     * Getter for the collection of classes
     * @return Collection
     */
    ClassList getClasses();

    /**
     * Adds a class to the list of classes for the student
     */
    void addClass(GymClass gymClass);

    /**
     * Method to return Student element
     * @return
     */
    Element getContent();

}
