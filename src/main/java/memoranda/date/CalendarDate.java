package main.java.memoranda.date;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import main.java.memoranda.util.Local;
import main.java.memoranda.util.Util;

/*$Id: CalendarDate.java,v 1.3 2004/01/30 12:17:41 alexeya Exp $*/
/**
 * Class that holds the information for dates; year,month,day.
 */
public class CalendarDate {

    private int _year;
    private int _month;
    private int _day;

    /**
     * Returns a calender object with inputted date.
     * @param date Date object
     * @return Calendar object
     */
    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * Constructor that sets the Calendar to the current date of the system
     */
    public CalendarDate() {
        this(Calendar.getInstance());
    }

    /**
     * Constructor that sets the Calendar to the inputted day,month,and year.
     * @param day int value of the day
     * @param month int value for the month
     * @param year int value for the year
     */
    public CalendarDate(int day, int month, int year) {
        _year = year;
        _month = month;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, _year);
        cal.set(Calendar.MONTH, _month);
        cal.getTime();
        //checks to see if int of the day is valid
        int dmax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (day <= dmax)
          _day = day;
        else
          _day = dmax;

    }

    /**
     * Constructor that sets the Calendar using a Calendar object
     * @param cal
     */
    public CalendarDate(Calendar cal) {
        _year = cal.get(Calendar.YEAR);
        _day = cal.get(Calendar.DAY_OF_MONTH);
        _month = cal.get(Calendar.MONTH);
    }

    /**
     * Constructor that sets the Calendar using a Date object
     * @param date
     */
    public CalendarDate(Date date) {
        this(dateToCalendar(date));
    }

    /**
     * Constructor that sets the Calendar using a String
     * @param date
     */
    public CalendarDate(String date) {
        int[] d = Util.parseDateStamp(date);
        _day = d[0];
        _month = d[1];
        _year = d[2];

    }

    /**
     * Returns a CalendarDate of the current system local time
     * @return CalendarDate with local time
     */
    public static CalendarDate today() {
        return new CalendarDate();
    }

    /**
     * Returns a CalendarDate of yesterdays date.
     * @return CalendarDate
     */
    public static CalendarDate yesterday() {
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.DATE, false);
        return new CalendarDate(cal);
    }

    /**
     * Returns a CalendarDate of tomorrows date.
     * @return CalendarDate
     */
    public static CalendarDate tomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.DATE, true);
        return new CalendarDate(cal);
    }

    /**
     * Takes ints of the day,month, and year and sets them to a new Calendar object
     * @param day int of the day value
     * @param month int of the month value
     * @param year int of the year value
     * @return Calendar object
     */
    public static Calendar toCalendar(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.getTime();
        return cal;
    }

    /**
     * Takes ints of the day,month, and year and sets them to a new Calendar object to return a Date object
     * @param day int of the day value
     * @param month int of the month value
     * @param year int of the year value
     * @return Calendar object
     */
    public static Date toDate(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    //Basic getters
    public Calendar getCalendar() {
        return toCalendar(_day, _month, _year);
    }
    public Date getDate() {
        return toDate(_day, _month, _year);
    }
    public int getDay() {
        return _day;
    }
    public int getMonth() {
        return _month;
    }
    public int getYear() {
        return _year;
    }
    public String getFullDateString() {
        return Local.getDateString(this, DateFormat.FULL);
    }
    public String getMediumDateString() {
        return Local.getDateString(this, DateFormat.MEDIUM);
    }
    public String getLongDateString() {
        return Local.getDateString(this, DateFormat.LONG);
    }
    public String getShortDateString() {
        return Local.getDateString(this, DateFormat.SHORT);
    }

    //Compare methods
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
    public boolean equals(CalendarDate date) {
        if (date == null) return false;
        return ((date.getDay() == getDay()) && (date.getMonth() == getMonth()) && (date.getYear() == getYear()));
    }

    /**
     * Determines if a date is before the inputted date
     * @param date CalendarDate
     * @return boolean
     */
    public boolean before(CalendarDate date) {
        if (date == null) return true;
        return this.getCalendar().before(date.getCalendar());
    }
    /**
     * Determines if a date is after the inputted date
     * @param date CalendarDate
     * @return boolean
     */
    public boolean after(CalendarDate date) {
        if (date == null) return true;
        return this.getCalendar().after(date.getCalendar());
    }

    /**
     * Determines if a date is currently between two dates or is one of the dates
     * @param startDate CalendarDate
     * @param endDate CalendarDate
     * @return boolean
     */
    public boolean inPeriod(CalendarDate startDate, CalendarDate endDate) {
        return (after(startDate) && before(endDate)) || equals(startDate) || equals(endDate);
    }

    /**
     * To string override
     * @return String
     */
    public String toString() {
        return Util.getDateStamp(this);
    }


}
