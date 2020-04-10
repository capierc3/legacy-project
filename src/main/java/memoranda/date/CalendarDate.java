package main.java.memoranda.date;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import main.java.memoranda.util.Local;
import main.java.memoranda.util.Util;

/*$Id: CalendarDate.java,v 1.3 2004/01/30 12:17:41 alexeya Exp $*/
/**
 * Class that holds the information for dates; year,month,day.
 *
 * WhiteBox test notes:
 * Added the ability to add hours and mins for a 12 hour clock; new CalendarDate(day,month,year,hour,min,isAM)
 *      A all day event would just be new CalendarDate(day,month,year)
 * Example: CalendarDate class1 = new CalendarDate(4,10,2020,3,30,false) = 4/10/2020 3:30 PM
 *          CalendarDate availability = new CalendarDate(4,10,2020) = all day on 4/10/2020
 * You should be able to find if one gym class is before or after another by comparing start times of the class.
 * Example: class1.before(class2)     class1.after(class2)
 * Or if a class is during another class;  class1.inPeriod(class2Start,class2End)
 * All comparable should work with or without time of day set; if a class is before or after an all day date
 *          class1.equals(availability) -> or if class is held on a given date.
 */
public class CalendarDate {

    private int _year;
    private int _month;
    private int _day;
    private int _hour;
    private int _min;
    private boolean _isAM;
    private boolean hourSet;


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
        if (day <= dmax) {
            _day = day;
        }else {
            _day = dmax;
        }
        hourSet = false;
    }

    public CalendarDate(int day, int month, int year,int hour, int min, boolean isAM){
        this(day,month,year);
        _hour = hour;
        _min = min;
        _isAM = isAM;
        hourSet = true;
    }

    /**
     * Constructor that sets the Calendar using a Calendar object
     * @param cal
     */
    public CalendarDate(Calendar cal) {
        _year = cal.get(Calendar.YEAR);
        _day = cal.get(Calendar.DAY_OF_MONTH);
        _month = cal.get(Calendar.MONTH);
        _hour = cal.get(Calendar.HOUR);
        _min = cal.get(Calendar.MINUTE);
        if (cal.get(Calendar.AM_PM) == Calendar.AM){
            _isAM = true;
        } else {
            _isAM = false;
        }
        hourSet = true;
    }

    /**
     * Constructor that sets the Calendar using a Date object
     * @param date
     */
    public CalendarDate(Date date) {
        this(dateToCalendar(date));
        hourSet = false;
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
        hourSet = false;
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

    public static Calendar toCalendar(int day, int month, int year, int hour, int min,boolean AM){
        Calendar cal = toCalendar(day,month,year);
        cal.set(Calendar.HOUR,hour);
        cal.set(Calendar.MINUTE,min);
        if (AM){
            cal.set(Calendar.AM_PM,Calendar.AM);
        } else {
            cal.set(Calendar.AM_PM,Calendar.PM);
        }
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

    public static Date toDate(int day, int month, int year,int hour, int min, boolean isAM) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR,hour);
        cal.set(Calendar.MINUTE,min);
        if (isAM){
            cal.set(Calendar.AM_PM,Calendar.AM);
        } else {
            cal.set(Calendar.AM_PM, Calendar.PM);
        }
        return cal.getTime();
    }

    //Basic getters
    public Calendar getCalendar() {
        if (hourSet){
            return toCalendar(_day,_month,_year,_hour,_min,_isAM);
        } else {
            return toCalendar(_day, _month, _year);
        }
    }
    public Date getDate() {
        if (hourSet){
            return toDate(_day,_month,_year,_hour,_min,_isAM);
        } else {
            return toDate(_day, _month, _year);
        }
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
    public int getHour(){return _hour;}
    public int getMin(){return _min;}
    public boolean isAM(){return _isAM;}
    public boolean isHourSet(){return hourSet;}
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
        if (object.getClass().isInstance(Calendar.class)) {
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
        if (date.isHourSet() && this.isHourSet()) {
            return ((date.getDay() == getDay()) && (date.getMonth() == getMonth()) && (date.getYear() == getYear())
                    && (date.getHour() == getHour()) && (date.getMin() == getMin()) && (date.isAM() == isAM()));
        } else {
            return ((date.getDay() == getDay()) && (date.getMonth() == getMonth()) && (date.getYear() == getYear()));
        }
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
        if (isHourSet()) {
            return Util.getDateStamp(this)+"/"+Util.getTimeStamp(this);
        } else {
            return Util.getDateStamp(this);
        }
    }



}
