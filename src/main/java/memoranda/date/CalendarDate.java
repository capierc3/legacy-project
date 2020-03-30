/**
 * CalendarDate.java
 * Created on 11.02.2003, 18:02:02 Alex
 * Package: net.sf.memoranda
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda.date;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import main.java.memoranda.util.Local;
import main.java.memoranda.util.Util;

/**
 *
 */
/*$Id: CalendarDate.java,v 1.3 2004/01/30 12:17:41 alexeya Exp $*/
public class CalendarDate {

    private int _year;
    private int _month;
    private int _day;

    // Method to return a Calendar object based on the date passed in
    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    // Basic constructor for CalendarDate object
    public CalendarDate() {
        this(Calendar.getInstance());
    }

    // Constructor for CalendarDate with day, month, and year parameters passed in
    public CalendarDate(int day, int month, int year) {
        _year = year;
        _month = month;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, _year);
        cal.set(Calendar.MONTH, _month);cal.getTime();
        int dmax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (day <= dmax)
          _day = day;
        else
          _day = dmax;

    }

    // Constructor for CalendarDate with Calendar object passed in as parameter
    public CalendarDate(Calendar cal) {
        _year = cal.get(Calendar.YEAR);
        _day = cal.get(Calendar.DAY_OF_MONTH);
        _month = cal.get(Calendar.MONTH);
    }

    // Constructor for CalendarDate with just a Date object passed in as parameter
    public CalendarDate(Date date) {
        this(dateToCalendar(date));
    }

    // Constructor for building CalendarDate object off of String, uses Util method
    // to parse out the DateStamp, and builds CD object accordingly
    public CalendarDate(String date) {
        int[] d = Util.parseDateStamp(date);
        _day = d[0];
        _month = d[1];
        _year = d[2];

    }

    // Method to return a CalendarDate object with today's date
    public static CalendarDate today() {
        return new CalendarDate();
    }

    // Method to return a CalendarDate object with yesterday's date
    public static CalendarDate yesterday() {
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.DATE, false);
        return new CalendarDate(cal);
    }

    // Method to return a CalendarDate object with tomorrow's date
    public static CalendarDate tomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.DATE, true);
        return new CalendarDate(cal);
    }

    // Method to return a Calendar object built from day, month, and year parameters
    public static Calendar toCalendar(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.getTime();
        return cal;
    }

    // Method to return a Date object built from day, month, and year parameters
    public static Date toDate(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    // Method to return Calendar object
    public Calendar getCalendar() {
        return toCalendar(_day, _month, _year);
    }

    // Method to return Date object
    public Date getDate() {
        return toDate(_day, _month, _year);
    }

    // Method to return Day integer value
    public int getDay() {
        return _day;
    }

    // Method to return Month integer value
    public int getMonth() {
        return _month;
    }

    // Method to return Year integer value
    public int getYear() {
        return _year;
    }

    // Method that return a boolean when comparing the Date, CalendarDate, or Calendar object and the day
    // month and year of the object, and checks that it is now as a CalendarDate object
    public boolean equals(Object object) {
        if (object.getClass().isInstance(CalendarDate.class)) {
            CalendarDate d2 = (CalendarDate) object;
            return ((d2.getDay() == getDay()) && (d2.getMonth() == getMonth()) && (d2.getYear() == getYear()));
        }
        else if (object.getClass().isInstance(Calendar.class)) {
            Calendar cal = (Calendar) object;
            return this.equals(new CalendarDate(cal));
        }
        else if (object.getClass().isInstance(Date.class)) {
            Date d = (Date) object;
            return this.equals(new CalendarDate(d));
        }
        return super.equals(object);
    }

    // Method that returns a boolean if the CalendarDate object is the same
    public boolean equals(CalendarDate date) {
        if (date == null) return false;
        return ((date.getDay() == getDay()) && (date.getMonth() == getMonth()) && (date.getYear() == getYear()));
    }

    // Method that returns a boolean of true if the CalendarDate object is before the current CalendarDate
    public boolean before(CalendarDate date) {
        if (date == null) return true;
        return this.getCalendar().before(date.getCalendar());
    }

    // Method that returns a boolean of true if the CalendarDate object is after the current CalendarDate.
    public boolean after(CalendarDate date) {
        if (date == null) return true;
        return this.getCalendar().after(date.getCalendar());
    }

    // Method that returns a boolean of true if the current date is between or equal to either the startDate
    // or endDate
    public boolean inPeriod(CalendarDate startDate, CalendarDate endDate) {
        return (after(startDate) && before(endDate)) || equals(startDate) || equals(endDate);
    }

    // Method that calls the Util method converting the CalendarDate to a String
    public String toString() {
        return Util.getDateStamp(this);
    }

    // Method that returns the full CalendarDate String from the DateFormat method in Local.java class
    public String getFullDateString() {
        return Local.getDateString(this, DateFormat.FULL);
    }

    // Method that returns the medium length CalendarDate String from the DateFormat method in Local.java class
    public String getMediumDateString() {
        return Local.getDateString(this, DateFormat.MEDIUM);
    }

    // Method that returns the long CalendarDate String from the DateFormat method in Local.java class
    public String getLongDateString() {
        return Local.getDateString(this, DateFormat.LONG);
    }

    // Method that returns the short CalendarDate String from the DateFormat method in Local.java class
    public String getShortDateString() {
        return Local.getDateString(this, DateFormat.SHORT);
    }
    

}
