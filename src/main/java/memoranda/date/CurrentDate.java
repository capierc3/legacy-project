/**
 * CurrentDate.java
 * Created on 13.02.2003, 2:11:03 Alex
 * Package: net.sf.memoranda.date
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda.date;
import java.util.Collection;
import java.util.Vector;

/**
 *
 */
/*$Id: CurrentDate.java,v 1.4 2004/10/06 16:00:12 ivanrise Exp $*/
public class CurrentDate {

    private static CalendarDate _date = new CalendarDate();
    private static Vector dateListeners = new Vector();

    // Method to return the current CalendarDate selected
    public static CalendarDate get() {
        return _date;
    }

    // Void method that sets the current CalendarDate to the new date parameter passed in
    public static void set(CalendarDate date) {
        if (date.equals(_date)) return;
        _date = date;
        dateChanged(date);
    }

    // Method to reset the CalendarDate to today's date
    public static void reset() {
        set(new CalendarDate());
    }

    // Method to add a DateListener to the Vector dateListeners
    public static void addDateListener(DateListener dl) {
        dateListeners.add(dl);
    }

    // Method to return a Collection from the Vector dateListeners
    public static Collection getChangeListeners() {
        return dateListeners;
    }

    // Void method that gets the DateListener for the CalendarDate object passed in as a parameter, and
    // calls out to the DateListener interface that the date selected has been changed
    private static void dateChanged(CalendarDate date) {
        for (int i = 0; i < dateListeners.size(); i++)
            ((DateListener)dateListeners.get(i)).dateChange(date);
    }
}
