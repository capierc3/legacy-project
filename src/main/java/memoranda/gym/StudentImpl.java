package memoranda.gym;

import main.java.memoranda.Note;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.Student;
import main.java.memoranda.gym.UserImpl;
import nu.xom.Attribute;
import nu.xom.Element;

import java.io.File;
import java.util.Collection;

/**
 * Interface for the methods used for the Student class
 * @author Daimi Mussey
 */
abstract public class StudentImpl extends UserImpl implements Student {

    private ClassList list;

    public StudentImpl(String name, String id, String userName, String password, Belt belt, File newPicture, Collection<Note> newNoteList, ClassList newUserClasses) {
        super(name, id, userName, password, belt, newPicture, newNoteList, newUserClasses, "Student");
        list = newUserClasses;
    }


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
        this.setClassList(list);
    }

    public Element getContent() {
        return this.getContent();
    }
}
