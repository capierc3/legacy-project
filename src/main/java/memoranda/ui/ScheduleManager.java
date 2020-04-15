package main.java.memoranda.ui;

import main.java.memoranda.gym.*;

/**
 * Class to manage adding and removing classes in the MySchedulePanel
 *
 * @author Chase
 */
public class ScheduleManager {

    private ClassList _allClasses;
    private ClassList _myStudentClasses;
    private ClassList _myTeachingClasses;
    private User _user;

    ScheduleManager(User user){
        _user = user;
        _myStudentClasses = fillStudentClasses();
        _myTeachingClasses = fillTeachingClasses();

    }

    private ClassList fillStudentClasses() {
        if (_user instanceof Student){
            return ((Student) _user).getClasses();
        }
        return null;
    }

    private ClassList fillTeachingClasses() {
        return null;
    }

    public static boolean addClass(User user, GymClass gymClass) {
        return false;
    }

    public static boolean dropClass(User user, GymClass gymClass){
        return false;
    }
}
