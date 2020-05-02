package main.java.memoranda.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;

import main.java.memoranda.date.CalendarDate;

import java.io.*;

/**
 * Provides locale info
 *
 */
/*$Id: Local.java,v 1.6 2004/10/11 08:48:21 alexeya Exp $*/
public class Local {

    static Locale currentLocale = Locale.getDefault();
    static LoadableProperties messages = new LoadableProperties();
    static boolean disabled = false;

    /**
     * Initializer for Local configurations
     */
    static {
    	if (!Configuration.get("DISABLE_L10N").equals("yes")) {
	    	String fn = "messages_"
	                    + currentLocale.getLanguage()
	                    + ".properties";
	        if (Configuration.get("LOCALES_DIR") != "") {
	        	System.out.print("Look "+fn+" at: "+Configuration.get("LOCALES_DIR")+" ");
	        	try {
	        		messages.load(new FileInputStream(
	        			Configuration.get("LOCALES_DIR")+File.separator+fn));
	        		System.out.println(" - found");
	        	}
	        	catch (IOException ex) {
	        		// Do nothing ...
	        		System.out.println(" - not found");
	        		ex.printStackTrace();
	        	}
	        }
	        if (messages.size() == 0) {
		        try {
		            messages.load(
		                Local.class.getResourceAsStream(
		                    "localmessages/"+fn));            
		        }
		        catch (Exception e) {
		            // Do nothing ...
		        }
	        }
    	}
    	else {
    		currentLocale = new Locale("en", "US");
    		/*DEBUG*/
    		System.out.println("* DEBUG: Locales are disabled");
    	}       
    	if (messages.size() == 0) 
    		messages = null;
    		
        /*** DEBUG PURPOSES ***/
        System.out.println("Default locale: " + currentLocale.getDisplayName());
        if (messages != null) {
            System.out.println(
                "Use local messages: messages_"
                    + currentLocale.getLanguage()
                    + ".properties");
        }
        else {
            System.out.println(
                "* DEBUG: Locales are disabled or not found: messages_"
                    + currentLocale.getLanguage()
                    + ".properties");
        }        
        /**********************/
    }

    /**
     * Method returning the Hashtable of current LoadableProperties
     * @return Hashtable
     */
    public static Hashtable getMessages() {
        return messages;
    }

    /**
     * Method to return the current Locale
     * @return Locale
     */
    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * Initializing MonthNames String[]
     */
    static String monthnames[] =
        {
            "Jan",
            "Feb",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December" };

    static String weekdaynames[] =
        { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

    /**
     * Method to return String from Hashtable of LoadableProperties based on key passed in
     * @param key
     * @return String
     */
    public static String getString(String key) {
        if ((messages == null) || (disabled)) {
            return key;
        }
        String msg = (String) messages.get(key.trim().toUpperCase());
        if ((msg != null) && (msg.length() > 0)) {
            return msg;
        }
        else {
            return key;
        }
    }

    /**
     * Method to get Month Names based on locale
     * @return String[]
     */
    public static String[] getMonthNames() {
        String[] localmonthnames = new String[12];
        for (int i = 0; i < 12; i++) {
            localmonthnames[i] = getString(monthnames[i]);
        }
        return localmonthnames;
    }

    /**
     * Method to get Weekday Names based on locale
     * @return String[]
     */
    public static String[] getWeekdayNames() {
        String[] localwdnames = new String[7];
        String[] localnames = weekdaynames;

        if (Configuration.get("FIRST_DAY_OF_WEEK").equals("mon"))
            localnames =
                new String[] {
                    "Mon",
                    "Tue",
                    "Wed",
                    "Thu",
                    "Fri",
                    "Sat",
                    "Sun" };

        for (int i = 0; i < 7; i++) {
            localwdnames[i] = getString(localnames[i]);
        }
        return localwdnames;
    }

    /**
     * Method to return the name of the Month for int passed in
     * @param m
     * @return String
     */
    public static String getMonthName(int m) {
        return getString(monthnames[m]);
    }

    /**
     * Method to return the Weekday name based on int passed in
     * @param wd
     * @return String
     */
    public static String getWeekdayName(int wd) {
        return getString(weekdaynames[wd - 1]);
    }

    /**
     * Method to get the Date passed in as a String
     * @param d
     * @param f
     * @return String
     */
    public static String getDateString(Date d, int f) {
        DateFormat dateFormat = DateFormat.getDateInstance(f, currentLocale);
        return dateFormat.format(d);
    }

    /**
     * Method to get the Date as String based on Calendar passed in
     * @param cal
     * @param f
     * @return String
     */
    public static String getDateString(Calendar cal, int f) {
        return getDateString(cal.getTime(), f);
    }

    /**
     * Method to get Date as a String based on CalendarDate passed in
     * @param date
     * @param f
     * @return String
     */
    public static String getDateString(CalendarDate date, int f) {
        return getDateString(date.getDate(), f);
    }

    /**
     * Method to get Date as a String based on month, day, year, and format passed in
     * @param m
     * @param d
     * @param y
     * @param f
     * @return String
     */
    public static String getDateString(int m, int d, int y, int f) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, m);
        cal.set(Calendar.DAY_OF_MONTH, d);
        cal.set(Calendar.YEAR, y);

        return getDateString(cal.getTime(), f);
    }

    /**
     * Method to get Time as String based on Date passed in
     * @param d
     * @return String
     */
    public static String getTimeString(Date d) {
        DateFormat dateFormat =
            DateFormat.getTimeInstance(DateFormat.SHORT, currentLocale);
        return dateFormat.format(d);
    }

    /**
     * Method to get Time as String based on Calendar passed in
     * @param cal
     * @return String
     */
    public static String getTimeString(Calendar cal) {
        return getTimeString(cal.getTime());
    }

    /**
     * Method to get Time as a String based on hour and minutes passed in
     * @param hh
     * @param mm
     * @return String
     */
    public static String getTimeString(int hh, int mm) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hh);
        cal.set(Calendar.MINUTE, mm);
        return getTimeString(cal.getTime());
    }

    /**
     * Method to parse Time String passed in into an int[]
     * @param s
     * @return int[]
     */
    public static int[] parseTimeString(String s) {
        s = s.trim();
        String h = "";
        String m = "";
        if (s.indexOf(":") > 0) {
            h = s.substring(0, s.indexOf(":"));
            m = s.substring(s.indexOf(":") + 1);
        }
        else if (s.indexOf(":") == 0) {
            h = "0";
            m = s;
        }
        else {
            h = s;
            m = "0";
        }
        int[] time = new int[2];
        try {
            time[0] = new Integer(h).intValue();
            if ((time[0] < 0) || (time[0] > 23)) {
                time[0] = 0;
            }
        }
        catch (NumberFormatException nfe) {
            return null;
        }
        try {
            time[1] = new Integer(m).intValue();
            if ((time[1] < 0) || (time[1] > 59)) {
                time[1] = 0;
            }
        }
        catch (NumberFormatException nfe) {
            return null;
        }
        return time;
    }

}
