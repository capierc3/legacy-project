package main.java.memoranda.gym;

import main.java.memoranda.date.CalendarDate;
import java.util.Collection;
import java.util.HashMap;

/**
 * Class that implements the ClassList interface.
 *
 * Class should handle all the logic behind adding,removing, and getting classes stored inside a hashmap.
 * list by rank should return a ClassList of classes that match an inputted belt rank
 * List by date should return a ClassList of classes that match an inputted CalendarDate
 *
 * @author Chase
 */
public class ClassListImpl implements ClassList {

    private HashMap<String,GymClass> classes;

    @Override
    public GymClass getClass(String id) {
        return classes.getOrDefault(id, null);
    }

    @Override
    public void addClass(GymClass gymClass) {
        classes.put(gymClass.getID(),gymClass);
    }

    @Override
    public void removeClass(String id) {
        classes.remove(id);
    }

    @Override
    public int getSize() {
        return classes.size();
    }

    @Override
    public Collection<GymClass> getAllClasses() {
        return classes.values();
    }

    @Override
    public ClassList getListByRank(String rank) {
        ClassList rankList = new ClassListImpl();
        for (GymClass gymClass:classes.values()) {
            if (gymClass.getRank().equalsIgnoreCase(rank)){
                rankList.addClass(gymClass);
            }
        }
        return rankList;
    }

    @Override
    public ClassList getListByDate(CalendarDate date) {
        ClassList dateList = new ClassListImpl();
        for (GymClass gymClass:classes.values()) {
            if (gymClass.getDate().equals(date)){
                dateList.addClass(gymClass);
            }
        }
        return dateList;
    }
}
