package main.java.memoranda.ui;

import main.java.memoranda.EventImpl;
import main.java.memoranda.EventsManager;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.*;
import main.java.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class to manage adding and removing classes in the MySchedulePanel
 *
 * @author Chase
 */
public class MyScheduleManager {

    private ClassList _allClasses;
    private ClassList _myStudentClasses;
    private ClassList _myTeachingClasses;
    //private User _user;
    boolean _isOwner; //only until User can be inputted

    /**
     * main constructor for the Manager
     */
    MyScheduleManager(ClassList list,boolean isOwner){
        _isOwner = isOwner;
        fillAllClasses(0,"Ascending");
        if (!isOwner) {
            _myStudentClasses = fillStudentClasses(0, "Ascending");
            _myTeachingClasses = fillTeachingClasses(0, "Ascending");
        }

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

    public GymClass createClass(CalendarDate startDate, CalendarDate endDate, String text, String type, Belt rank) {
        GymClassImpl gymClass = new GymClassImpl(text,type,rank,startDate,endDate);
        addClass(gymClass);
        return gymClass;
    }

    /**
     * fills and sorts the student list.
     * @param sort int
     * @param dir String
     * @return ClassList
     */
    private ClassList fillStudentClasses(int sort,String dir) {
        //_myStudentClasses = (Student) _user.getClasses();
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
        //_myTeachingClasses = (Trainer) _user.getAllClasses();
        sortList(_myTeachingClasses,sort,dir);
        return null;
    }
    /**
     * fills and sorts the all classes list.
     * @param sort int
     * @param dir String
     * @return ClassList
     */
    private void fillAllClasses(int sort,String dir){
        CalendarDate sDate = new CalendarDate(17,3,2020,5,30,true);
        CalendarDate eDate = new CalendarDate(17,3,2020,6,30,true);
        CalendarDate sDate2 = new CalendarDate(21,3,2020,5,30,false);
        CalendarDate eDate2 = new CalendarDate(21,3,2020,6,30,false);
        CalendarDate sDate3 = new CalendarDate(17,3,2020,7,30,false);
        CalendarDate eDate3 = new CalendarDate(17,3,2020,8,30,false);
        GymClass gymClass = new GymClassImpl("Test _01","Public",Belt.BLUE,sDate,eDate);
        GymClass gymClass2 = new GymClassImpl("A Test _02","Public",Belt.WHITE,sDate2,eDate2);
        GymClass gymClass3 = new GymClassImpl("Test _03","Public",Belt.BLACK1,sDate3,eDate3);
        _allClasses = new ClassListImpl(new ArrayList<>());
        _allClasses.addClass(gymClass);
        _allClasses.addClass(gymClass2);
        _allClasses.addClass(gymClass3);
        sortList(_allClasses,sort,dir);
    }

    /**
     * checks registration and adds the class to the user and updates the manager list.
     * True = class was added
     * @param gymClass GymClass
     * @return boolean
     */
    public boolean addClass(GymClass gymClass) {
        if (_isOwner){
            _allClasses.addClass(gymClass);
        } else {
            _myStudentClasses.addClass(gymClass);
        }
        return false;
    }

    /**
     * Drops a class for the user.
     * True = dropped
     * @param gymClass GymClass
     * @return boolean
     */
    public boolean dropClass(GymClass gymClass){
        return false;
    }

    public boolean removeClass(GymClass gymClass) {
        if (_allClasses.getClass(gymClass.getID()) == null) {
            return false;
        } else {
            _allClasses.removeClass(gymClass.getID());
            return true;
        }
    }

    /**
     * sorts the list based on inputted sort type and direction
     * @param list ClassList
     * @param sort int
     * @param dir String
     */
    private void sortList(ClassList list, int sort, String dir) {
        ArrayList<GymClass> classes = (ArrayList<GymClass>) list.getAllClasses();
        boolean isAscending = dir.equalsIgnoreCase("Ascending");
        if (sort == 0){//Date
            classes.sort(new DateCompare(isAscending));
        } else if (sort == 1) {//Rank
            classes.sort(new BeltCompare(isAscending));
        } else if (sort == 2){//Trainer
            //ToDo find a way to get a trainers name.
        } else {//Name
            classes.sort(new NameCompare(isAscending));
        }
        list = new ClassListImpl(classes);
    }

    public ArrayList<GymClass> getClasses(){
        if (_isOwner) {
            return (ArrayList<GymClass>) _allClasses.getAllClasses();
        } else {
            return (ArrayList<GymClass>) _myStudentClasses.getAllClasses();
        }
    }

    public ArrayList<GymClass> getDaysClasses(CalendarDate d){
        ArrayList<GymClass> classes = getClasses();
        ArrayList<GymClass> dayClasses = new ArrayList<>();
        for (GymClass gymClass :classes) {
            if (d.equalsDay(gymClass.getStartDate())){
                dayClasses.add(gymClass);
            }
        }
        return dayClasses;
    }

    /**
     * class to sort by date.
     */
    private static class DateCompare implements Comparator<GymClass> {

        private boolean as;

        DateCompare(boolean as){
            this.as = as;
        }

        @Override
        public int compare(GymClass gymClass, GymClass t1) {
            int value;
            if (gymClass.getStartDate().before(t1.getStartDate())){
                value = -1;
            } else if (gymClass.getStartDate().after(t1.getStartDate())){
                value = 1;
            } else {
                value = 0;
            }
            if (as){
                return value;
            } else {
                return value*-1;
            }
        }
    }
    /**
     * class to sort by Rank
     */
    private static class BeltCompare implements Comparator<GymClass> {

        private boolean as;

        BeltCompare(boolean as){
            this.as = as;
        }

        @Override
        public int compare(GymClass gymClass, GymClass t1) {
            int value;
            value = Integer.compare(gymClass.getRank().getValue(), t1.getRank().getValue());
            if (as){
                return value;
            } else {
                return value*-1;
            }
        }
    }
    /**
     * class to sort by Trainer Name
     */
    private static class NameCompare implements Comparator<GymClass> {

        private boolean as;

        NameCompare(boolean as){
            this.as = as;
        }

        @Override
        public int compare(GymClass gymClass, GymClass t1) {
            int value;
            value = gymClass.getName().compareToIgnoreCase(t1.getName());
            if (as){
                return value;
            } else {
                return value*-1;
            }
        }
    }
}
