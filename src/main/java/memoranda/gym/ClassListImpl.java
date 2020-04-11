package memoranda.gym;

import java.util.Collection;

/**
 * Interface for the ClassList methods.
 * @author Daimi Mussey
 */

public class ClassListImpl {
    private Collection<GymClass> classes;

    /**
     * Searches the list for a gym class by using the inputted ID
     * @param id String
     * @return User
     */
    GymClass getClass(String id) {
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
    void addClass(GymClass gymClass) {
        classes.add(gymClass);
    }

    /**
     * removes a class from a list with matching ID number
     * @param id String
     */
    void removeClass(String id) {
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
    }

    /**
     * finds the size of list.
     * @return int
     */
    int getSize() {
        return classes.size();
    }

    /**
     * returns the entire list
     * @return Collection
     */
    Collection<GymClass> getAllClasses() {
        return classes;
    }

    /**
     * returns a ClassList filled with classes of inputted rank
     * @param rank String
     * @return ClassList
     */
    ClassList getListByRank(String rank) {
        ClassList list = new ClassList();
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
    ClassList getListByDate(CalendarDate date) {
        ClassList list = new ClassList();
        if (!classes.isEmpty()) {
            for (GymClass gymClass : classes) {
                if (gymClass.getDate().equals(date)) {
                    list.addClass(gymClass);
                }
            }
        }
        return list;
    }
}
