package main.java.memoranda.gym;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.User;
/**
 * Interface for the methods used for the Student class
 * @author Chase
 */
public interface Student extends User{

    /**
     * Getter for the collection of classes
     * @return Collection
     */
    ClassList getClasses();

    /**
     * Adds a class to the list of classes for the student
     */
    void addClass(GymClass gymClass);

}
