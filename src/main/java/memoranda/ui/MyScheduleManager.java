package main.java.memoranda.ui;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.*;

import java.util.ArrayList;
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
    private ClassList _ShownList;
    private User _user;

    /**
     * main constructor for the Manager
     */
    MyScheduleManager(ClassList list,User user){
        _user = user;
        _allClasses = list;
        fillAllClasses(0,"Ascending");
        if (_user instanceof Student) {
            fillStudentClasses(0, "Ascending");
        } else if (_user instanceof Trainer) {
            fillTeachingClasses(0, "Ascending");
        }
        _ShownList = _allClasses;

    }

    /**
     * Updates the list with new sorting method
     * @param list String
     * @param sort int
     * @param dir String
     */
    void updateLists(String list, int sort, String dir){
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
    private void fillStudentClasses(int sort,String dir) {
        if (_user instanceof Student) {
            _myStudentClasses = _user.getAllClasses();
        } else if (_user instanceof Trainer){
            _myStudentClasses = new ClassListImpl(new ArrayList<>());
            for (GymClass gymClass:_user.getAllClasses().getAllClasses()) {
                boolean notTrainer = true;
                for (User trainer:gymClass.getTrainers().getAllUsers()){
                    if (trainer.getName().equalsIgnoreCase(_user.getName())){
                        notTrainer = false;
                    }
                }
                if (notTrainer){
                    _myStudentClasses.addClass(gymClass);
                }
            }
        }
        sortList(_myStudentClasses,sort,dir);
    }

    /**
     * fills and sorts the teaching list.
     * @param sort int
     * @param dir String
     * @return ClassList
     */
    private void fillTeachingClasses(int sort,String dir) {
        _myTeachingClasses = new ClassListImpl(new ArrayList<>());
        for (GymClass gymClass:_user.getAllClasses().getAllClasses()) {
            for (User trainer:gymClass.getTrainers().getAllUsers()){
                if (trainer.getName().equalsIgnoreCase(_user.getName())){
                    _myTeachingClasses.addClass(gymClass);
                }
            }
        }
        sortList(_myTeachingClasses,sort,dir);
    }
    /**
     * fills and sorts the all classes list.
     * @param sort int
     * @param dir String
     * @return ClassList
     */
    private void fillAllClasses(int sort,String dir){
        //need some way to pull saved classes
        GymClass gymClass1 = new GymClassImpl("Kicking 101","Public",Belt.WHITE,
                new CalendarDate(18,3,2020,7,0,false),
                new CalendarDate(18,3,2020,7,30,false));
        gymClass1.setSize(3);
        GymClass gymClass2 = new GymClassImpl("Kicking 202","Public",Belt.YELLOW,
                new CalendarDate(18,3,2020,8,0,false),
                new CalendarDate(18,3,2020,8,30,false));
        gymClass2.setSize(20);
        GymClass gymClass3 = new GymClassImpl("One on One with Matt","Private",Belt.WHITE,
                new CalendarDate(18,3,2020,9,0,false),
                new CalendarDate(18,3,2020,9,30,false));
        gymClass3.setSize(0);
        _allClasses.addClass(gymClass1);
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
        if (_user instanceof Student){
            if (!gymClass.isFull()) {
                _myStudentClasses.addClass(gymClass);
                //gymClass.getStudents().addUser(_user);
                return true;
            } else {
                return false;
            }
        } else {
            _allClasses.addClass(gymClass);
            return true;
        }
    }

    /**
     * Removes the class from the schedule and drops the class for all students in the class.
     * for a student it only drops them from the class nothing else.
     * True = dropped
     * @param gymClass GymClass
     * @return boolean
     */
    public boolean removeClass(GymClass gymClass) {
        if (_allClasses.getClass(gymClass.getID()) == null) {
            return false;
        } else {
            if (_user instanceof Student) {
                if (_user.getAllClasses().getClass(gymClass.getID())!=null) {
                    _user.removeClass(gymClass);
                    //gymClass.getStudents().removeUser(_user.getID());
                    return true;
                } else {
                    return false;
                }
            } else {
                _allClasses.removeClass(gymClass.getID());
                //Todo: needs userList implemented before use.
//                for (User student:gymClass.getStudents().getAllUsers()) {
//                    student.removeClass(gymClass);
//                }
                return true;
            }
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
        if (_user instanceof Student) {
            return (ArrayList<GymClass>) _myStudentClasses.getAllClasses();
        } else if (_user instanceof Trainer){
            ArrayList<GymClass> classes = (ArrayList<GymClass>) _myStudentClasses.getAllClasses();
            classes.addAll(_myTeachingClasses.getAllClasses());
            return classes;
        } else {
            return (ArrayList<GymClass>) _allClasses.getAllClasses();
        }
    }

    ArrayList<GymClass> getDaysClasses(CalendarDate d){
        ArrayList<GymClass> classes = (ArrayList<GymClass>) _ShownList.getAllClasses();
        ArrayList<GymClass> dayClasses = new ArrayList<>();
        for (GymClass gymClass :classes) {
            if (d.equalsDay(gymClass.getStartDate())){
                dayClasses.add(gymClass);
            }
        }
        return dayClasses;
    }

    User getUser(){
        return _user;
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
     * class to sort by Class Name
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
//    /**
//     * class to sort by Trainer Name
//     */
//    private static class TrainerCompare implements Comparator<GymClass> {
//
//        private boolean as;
//
//        TrainerCompare(boolean as){
//            this.as = as;
//        }
//
//        @Override
//        public int compare(GymClass gymClass, GymClass t1) {
//            return 0;
//        }
//    }

}
