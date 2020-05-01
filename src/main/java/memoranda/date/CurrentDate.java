package main.java.memoranda.date;

import java.util.Collection;
import java.util.Vector;

/*$Id: CurrentDate.java,v 1.4 2004/10/06 16:00:12 ivanrise Exp $*/
/**
 * Class to hold the value of the current date value used.
 */
public class CurrentDate {

    /** Current date object **/
    private static CalendarDate _date = new CalendarDate();
    /** Vector of objects that listen to changes to the date **/
    private static Vector dateListeners = new Vector();

    /**
     * getter that returns a CalendarDate object
     */
    public static CalendarDate get() {
	return _date;
    }

    /**
     * Sets the current date to the inputted date.
     * 
     * @param date CalendarDate object that holds the wanted date
     */
    public static void set(CalendarDate date) {
	if (date.equals(_date))
	    return;
	_date = date;
	dateChanged(date);
    }

    /**
     * sets the current date to the current date
     */
    public static void reset() {
	set(new CalendarDate());
    }

    /**
     * Adds a listener vector to the date listener vectors
     * 
     * @param dl DateListener
     */
    public static void addDateListener(DateListener dl) {
	dateListeners.add(dl);
    }

    /**
     * simple getter for the listeners
     * 
     * @return Vector of listeners
     */
    public static Collection getChangeListeners() {
	return dateListeners;
    }

    /**
     * Changes all the items in the listeners to change to the new current date.
     * 
     * @param date CalendarDate of the new date
     */
    private static void dateChanged(CalendarDate date) {
	for (int i = 0; i < dateListeners.size(); i++)
	    ((DateListener) dateListeners.get(i)).dateChange(date);
    }
}
