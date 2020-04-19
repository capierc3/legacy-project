package main.java.memoranda.gym;

import java.util.ArrayList;
import java.util.Collection;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.Belt;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;
/**
 * Interface for the ClassList methods.
 * @author Daimi Mussey
 */

public class ClassListImpl implements ClassList {
    private Collection<GymClass> classes;
    private Element element;

    public ClassListImpl(Collection<GymClass> newClasses) {
        element = new Element("ClassList");
        classes = newClasses;
        if (classes != null)  {
            setClassList(classes);
        }
    }

    public void setClassList(Collection<GymClass> classes) {
        for (GymClass gymClass : classes) {
            element.appendChild(gymClass.getContent().copy());
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
        if (getClass(gymClass.getID())==null){
            classes.add(gymClass);
            element.appendChild(gymClass.getContent().copy());
        }
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
    public ClassList getListByRank(Belt rank) {
        ClassList list = new ClassListImpl(new ArrayList<>());
        if (!classes.isEmpty()) {
            for (GymClass gymClass : classes) {
                if (gymClass.getRank().getValue() == rank.getValue()) {
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
        ClassList list = new ClassListImpl(new ArrayList<>());
        if (!classes.isEmpty()) {
            for (GymClass gymClass : classes) {
                if(gymClass.getStartDate() != null) {
                    if (gymClass.getStartDate().equals(date)) {
                        list.addClass(gymClass);
                    }
                }
            }
        }
        return list;
    }

    /**
     * Method to return ClassList as an Element.
     * @return Element
     */
    public Element getContent() {
        setClassList(classes);
        return element;
    }
}
