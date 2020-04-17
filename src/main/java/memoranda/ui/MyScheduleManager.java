package main.java.memoranda.ui;

import main.java.memoranda.EventImpl;
import main.java.memoranda.EventsManager;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.*;
import main.java.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Element;

/**
 * Class to manage adding and removing classes in the MySchedulePanel
 *
 * @author Chase
 */
public class MyScheduleManager {

    private ClassList _allClasses;
    private ClassList _myStudentClasses;
    private ClassList _myTeachingClasses;
    private User _user;

    /**
     * main constructor for the Manager
     * @param user User
     */
    MyScheduleManager(User user){
        _user = user;
        _allClasses = fillAllClasses(0,"Ascending");
        _myStudentClasses = fillStudentClasses(0,"Ascending");
        _myTeachingClasses = fillTeachingClasses(0,"Ascending");

    }

    /**
     * Updates the list with new sorting method
     * @param list String
     * @param sort int
     * @param dir String
     */
    public void updateLists(String list, int sort, String dir){
        if (list.equalsIgnoreCase("All Classes")){
            fillAllClasses(sort, dir);
        } else if (list.equalsIgnoreCase("Teaching")){
            fillTeachingClasses(sort,dir);
        } else {
            fillStudentClasses(sort,dir);
        }
    }

    public GymClass createClass(CalendarDate startDate, CalendarDate endDate, String text, String type, String rank) {
        GymClassImpl gymClass = new GymClassImpl(text,type,rank,startDate,endDate);
        addClass(null,gymClass);
        return gymClass;
    }

    /**
     * fills and sorts the student list.
     * @param sort int
     * @param dir String
     * @return ClassList
     */
    private ClassList fillStudentClasses(int sort,String dir) {
        sortList(_myStudentClasses,sort,dir);
        return null;
    }

    /**
     * fills and sorts the teaching list.
     * @param sort int
     * @param dir String
     * @return ClassList
     */
    private ClassList fillTeachingClasses(int sort,String dir) {
        sortList(_myTeachingClasses,sort,dir);
        return null;
    }
    /**
     * fills and sorts the all classes list.
     * @param sort int
     * @param dir String
     * @return ClassList
     */
    private ClassList fillAllClasses(int sort,String dir){
        sortList(_allClasses,sort,dir);
        return null;
    }

    /**
     * checks registration and adds the class to the user and updates the manager list.
     * True = class was added
     * @param user User
     * @param gymClass GymClass
     * @return boolean
     */
    public boolean addClass(User user, GymClass gymClass) {
        if (user == null){
            _allClasses.addClass(gymClass);
        } else if (user instanceof Student){
            //TODO check if class conflicts with other current classes
            _myStudentClasses.addClass(gymClass);
        }
        return false;
    }

    /**
     * Drops a class for the user.
     * True = dropped
     * @param user User
     * @param gymClass GymClass
     * @return boolean
     */
    public static boolean dropClass(User user, GymClass gymClass){
        return false;
    }

    /**
     * sorts the list based on inputted sort type and direction
     * @param list ClassList
     * @param sort int
     * @param dir String
     */
    private void sortList(ClassList list, int sort, String dir) {
        boolean isAscending = dir.equalsIgnoreCase("Ascending");
        if (sort == 0){//Date

        } else if (sort == 1) {//Rank

        } else if (sort == 2){//Trainer

        } else {//Name

        }
    }
}
