package memoranda.gym;

import main.java.memoranda.gym.ClassList;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.Student;

/**
 * Interface for the methods used for the Student class
 * @author Daimi Mussey
 */
abstract public class StudentImpl implements Student {
    private ClassList list;

    /**
     * Getter for the collection of classes
     * @return Collection
     */
    public ClassList getClasses() {
        return list;
    }

    /**
     * Adds a class to the list of classes for the student
     */
    public void addClass(GymClass gymClass) {
        list.addClass(gymClass);
    }
}
