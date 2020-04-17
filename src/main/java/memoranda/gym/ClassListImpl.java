package memoranda.gym;

import java.util.Collection;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.date.CalendarDate;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;
/**
 * Interface for the ClassList methods.
 * @author Daimi Mussey
 */

public class ClassListImpl {
    private Collection<GymClass> classes;
    private Element element;

    public ClassListImpl(Collection<GymClass> classes) {
        element = new Element("ClassList");
        setClassList(classes);
    }

    public void setClassList(Collection<GymClass> classes) {
        for (GymClass gymClass : classes) {
            element.appendChild(gymClass.getContent());
        }
    }

    /**
     * Searches the list for a gym class by using the inputted ID
     * @param id String
     * @return User
     */
    public GymClass getClass(String id) {
        GymClass result = null;
        if (!classes.isEmpty()) {
            for (GymClass gymClass : classes) {
                if (gymClass.getID().equals(id)) {
                    result = gymClass;
                }
            }
        }
        return result;
    }

    /**
     * Adds a class to the list
     * @param gymClass GymClass
     */
    public void addClass(GymClass gymClass) {
        classes.add(gymClass);
        element.appendChild(gymClass.getContent());
    }

    /**
     * removes a class from a list with matching ID number
     * @param id String
     */
    public void removeClass(String id) {
        GymClass classToRemove = null;
        if (!classes.isEmpty()) {
            for (GymClass gymClass : classes) {
                if (gymClass.getID().equals(id)) {
                    classToRemove = gymClass;
                }
            }

            if (classToRemove != null) {
                classes.remove(classToRemove);
            }
        }
        element.removeChildren();
        setClassList(classes);
    }

    /**
     * finds the size of list.
     * @return int
     */
    public int getSize() {
        return classes.size();
    }

    /**
     * returns the entire list
     * @return Collection
     */
    public Collection<GymClass> getAllClasses() {

        return classes;
    }

    /**
     * returns a ClassList filled with classes of inputted rank
     * @param rank String
     * @return ClassList
     */
    public ClassList getListByRank(String rank) {
        ClassList list = null;
        if (!classes.isEmpty()) {
            for (GymClass gymClass : classes) {
                if (gymClass.getRank().equals(rank)) {
                    list.addClass(gymClass);
                }
            }
        }
        return list;
    }

    /**
     * returns a ClassList filled with classes on an inputted date
     * @param date CalendarDate
     * @return ClassList
     */
    public ClassList getListByDate(CalendarDate date) {
        ClassList list = null;
        if (!classes.isEmpty()) {
            for (GymClass gymClass : classes) {
                if (gymClass.getDate().equals(date)) {
                    list.addClass(gymClass);
                }
            }
        }
        return list;
    }

    public Element getContent() {
        setClassList(classes);
        return element;
    }
}
