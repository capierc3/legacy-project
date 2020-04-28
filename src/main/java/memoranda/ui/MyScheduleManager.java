package main.java.memoranda.ui;

import java.util.ArrayList;
import java.util.Comparator;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.*;

/**
 * Class to manage adding and removing classes in the MySchedulePanel.
 *
 * @author Chase
 */
public class MyScheduleManager {

    private ClassList allClasses;
    private ClassList myStudentClasses;
    private ClassList myTeachingClasses;
    private ClassList shownList;
    private User user;

    /**
     * main constructor for the Manager.
     */
    MyScheduleManager(ClassList list, User user) {
	this.user = user;
	allClasses = list;
	fillAllClasses(0, "Ascending");
	if (this.user instanceof Student) {
	    fillStudentClasses(0, "Ascending");
	} else if (this.user instanceof Trainer) {
	    fillTeachingClasses(0, "Ascending");
	}
	shownList = allClasses;

    }

    /**
     * Updates the list with new sorting method.
     * 
     * @param list String
     * @param sort int
     * @param dir  String
     */
    void updateLists(String list, int sort, String dir) {
	if (list.equalsIgnoreCase("All Classes")) {
	    fillAllClasses(sort, dir);
	} else if (list.equalsIgnoreCase("Teaching")) {
	    fillTeachingClasses(sort, dir);
	} else {
	    fillStudentClasses(sort, dir);
	}
    }

    /**
     * fills and sorts the student list.
     * 
     * @param sort int
     * @param dir  String
     * @return ClassList
     */
    private void fillStudentClasses(int sort, String dir) {
	if (user instanceof Student) {
	    myStudentClasses = user.getAllClasses();
	} else if (user instanceof Trainer) {
	    myStudentClasses = new ClassListImpl(new ArrayList<>());
	    for (GymClass gymClass : user.getAllClasses().getAllClasses()) {
		boolean notTrainer = true;
		if (gymClass.getTrainer().getName().equalsIgnoreCase(user.getName())) {
		    notTrainer = false;
		}
		if (notTrainer) {
		    myStudentClasses.addClass(gymClass);
		}
	    }
	}
	sortList(myStudentClasses, sort, dir);
    }

    /**
     * fills and sorts the teaching list.
     * 
     * @param sort int
     * @param dir  String
     * @return ClassList
     */
    private void fillTeachingClasses(int sort, String dir) {
	myTeachingClasses = new ClassListImpl(new ArrayList<>());
	for (GymClass gymClass : user.getAllClasses().getAllClasses()) {
	    if (gymClass.getTrainer().getName().equalsIgnoreCase(user.getName())) {
		myTeachingClasses.addClass(gymClass);
	    }
	}
	sortList(myTeachingClasses, sort, dir);
    }

    /**
     * fills and sorts the all classes list.
     * 
     * @param sort int
     * @param dir  String
     * @return ClassList
     */
    private void fillAllClasses(int sort, String dir) {
	// need some way to pull saved classes
	GymClass gymClass1 = new GymClassImpl("Kicking 101", "Public", Belt.WHITE,
		new CalendarDate(18, 3, 2020, 7, 0, false), new CalendarDate(18, 3, 2020, 7, 30, false));
	gymClass1.setSize(3);
	gymClass1.addTrainer((Trainer) App.appUsers.getUser("trainer"));
	GymClass gymClass2 = new GymClassImpl("Kicking 202", "Public", Belt.YELLOW,
		new CalendarDate(18, 3, 2020, 8, 0, false), new CalendarDate(18, 3, 2020, 8, 30, false));
	gymClass2.setSize(20);
	gymClass2.addTrainer((Trainer) App.appUsers.getUser("trainer"));
	GymClass gymClass3 = new GymClassImpl("One on One with Mac", "Private", Belt.WHITE,
		new CalendarDate(18, 3, 2020, 10, 0, false), new CalendarDate(18, 3, 2020, 10, 30, false));
	gymClass3.setSize(0);
	gymClass3.addTrainer((Trainer) App.appUsers.getUser("trainer"));
	allClasses.addClass(gymClass1);
	allClasses.addClass(gymClass2);
	allClasses.addClass(gymClass3);
	sortList(allClasses, sort, dir);
    }

    /**
     * checks registration and adds the class to the user and updates the manager
     * list. True = class was added
     * 
     * @param gymClass GymClass
     * @return boolean
     */
    public boolean addClass(GymClass gymClass) {
	if (user instanceof Student) {
	    if (!gymClass.isFull()) {
		myStudentClasses.addClass(gymClass);
		gymClass.addStudent((Student) user);
		return true;
	    } else {
		return false;
	    }
	} else {
	    allClasses.addClass(gymClass);
	    return true;
	}
    }

    /**
     * Removes the class from the schedule and drops the class for all students in
     * the class. for a student it only drops them from the class nothing else. True
     * = dropped
     * 
     * @param gymClass GymClass
     * @return boolean
     */
    public boolean removeClass(GymClass gymClass) {
	if (allClasses.getClass(gymClass.getID()) == null) {
	    return false;
	} else {
	    if (user instanceof Student) {
		if (user.getAllClasses().getClass(gymClass.getID()) != null) {
		    user.removeClass(gymClass);
		    gymClass.removeStudent((Student) user);
		    return true;
		} else {
		    return false;
		}
	    } else {
		allClasses.removeClass(gymClass.getID());
		for (User student : gymClass.getStudents().getAllUsers()) {
		    student.removeClass(gymClass);
		}
		return true;
	    }
	}
    }

    /**
     * sorts the list based on inputted sort type and direction.
     * 
     * @param list ClassList
     * @param sort int
     * @param dir  String
     */
    private void sortList(ClassList list, int sort, String dir) {
	ArrayList<GymClass> classes = (ArrayList<GymClass>) list.getAllClasses();
	boolean isAscending = dir.equalsIgnoreCase("Ascending");
	if (sort == 0) { // Date
	    classes.sort(new DateCompare(isAscending));
	} else if (sort == 1) { // Rank
	    classes.sort(new BeltCompare(isAscending));
	} else if (sort == 2) { // Trainer
	    classes.sort(new TrainerCompare(isAscending));
	} else { // Name
	    classes.sort(new NameCompare(isAscending));
	}
	list = new ClassListImpl(classes);
    }

    /**
     * returns all the classes based on the user type.
     * 
     * @return ArrayList
     */
    public ArrayList<GymClass> getClasses() {
	if (user instanceof Student) {
	    return (ArrayList<GymClass>) myStudentClasses.getAllClasses();
	} else if (user instanceof Trainer) {
	    ArrayList<GymClass> classes = (ArrayList<GymClass>) myStudentClasses.getAllClasses();
	    classes.addAll(myTeachingClasses.getAllClasses());
	    return classes;
	} else {
	    return (ArrayList<GymClass>) allClasses.getAllClasses();
	}
    }

    ArrayList<GymClass> getDaysClasses(CalendarDate d) {
	ArrayList<GymClass> classes = (ArrayList<GymClass>) shownList.getAllClasses();
	ArrayList<GymClass> dayClasses = new ArrayList<>();
	for (GymClass gymClass : classes) {
	    if (d.equalsDay(gymClass.getStartDate())) {
		dayClasses.add(gymClass);
	    }
	}
	return dayClasses;
    }

    User getUser() {
	return user;
    }

    /**
     * class to sort by date.
     */
    private static class DateCompare implements Comparator<GymClass> {

	private boolean as;

	DateCompare(boolean as) {
	    this.as = as;
	}

	@Override
	public int compare(GymClass gymClass, GymClass t1) {
	    int value;
	    if (gymClass.getStartDate().before(t1.getStartDate())) {
		value = -1;
	    } else if (gymClass.getStartDate().after(t1.getStartDate())) {
		value = 1;
	    } else {
		value = 0;
	    }
	    if (as) {
		return value;
	    } else {
		return value * -1;
	    }
	}
    }

    /**
     * class to sort by Rank.
     */
    private static class BeltCompare implements Comparator<GymClass> {

	private boolean as;

	BeltCompare(boolean as) {
	    this.as = as;
	}

	@Override
	public int compare(GymClass gymClass, GymClass t1) {
	    int value;
	    value = Integer.compare(gymClass.getRank().getValue(), t1.getRank().getValue());
	    if (as) {
		return value;
	    } else {
		return value * -1;
	    }
	}
    }

    /**
     * class to sort by Class Name.
     */
    private static class NameCompare implements Comparator<GymClass> {

	private boolean as;

	NameCompare(boolean as) {
	    this.as = as;
	}

	@Override
	public int compare(GymClass gymClass, GymClass t1) {
	    int value;
	    value = gymClass.getName().compareToIgnoreCase(t1.getName());
	    if (as) {
		return value;
	    } else {
		return value * -1;
	    }
	}
    }

    /**
     * class to sort by Trainer Name.
     */
    private static class TrainerCompare implements Comparator<GymClass> {

	private boolean as;

	TrainerCompare(boolean as) {
	    this.as = as;
	}

	@Override
	public int compare(GymClass c1, GymClass c2) {
	    int value;
	    if (as) {
		value = c1.getTrainer().getName().compareTo(c2.getTrainer().getName());
	    } else {
		value = c1.getTrainer().getName().compareTo(c2.getTrainer().getName()) * -1;
	    }
	    return value;
	}
    }

}
