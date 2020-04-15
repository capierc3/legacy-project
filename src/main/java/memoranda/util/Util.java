/**
 * Util.java
 * Created on 11.02.2003, 23:59:21 Alex
 * Package: net.sf.memoranda.util
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda team: http://memoranda.sf.net
 */
package main.java.memoranda.util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

import javax.swing.JFileChooser;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.ui.App;
import main.java.memoranda.ui.AppFrame;
import main.java.memoranda.ui.ExceptionDialog;

import java.util.Random;

/**
 *
 */
/*$Id: Util.java,v 1.13 2007/03/20 08:22:41 alexeya Exp $*/
public class Util {

	static long seed = 0;

    /**
     * Method to generate Id
     * @return String
     */
    public static String generateId() {
        long seed1 = System.currentTimeMillis();
        while (seed1 == seed) 
        	seed1 = System.currentTimeMillis(); // Make sure we'll don't get the same seed twice		  
    	seed = seed1;        	
    	Random r = new Random(seed); 
    	return Integer.toString(r.nextInt(), 16) +
				"-"+Integer.toString(r.nextInt(65535), 16) +
				"-"+Integer.toString(r.nextInt(65535), 16) +
				"-"+Integer.toString(r.nextInt(65535), 16);
                    
    }

    /**
     * Method to return the DateStamp from Calendar passed in
     * @param cal
     * @return String
     */
    public static String getDateStamp(Calendar cal) {
        return cal.get(Calendar.DAY_OF_MONTH)
            + "/"
            + (cal.get(Calendar.MONTH))
            + "/"
            + (cal.get(Calendar.YEAR));

    }

    public static String getTimeStamp(Object cal){
        String amPM = "PM";
        if (cal instanceof Calendar){
            Calendar calendar = (Calendar) cal;
            if (calendar.get(Calendar.AM_PM) == Calendar.AM){
                amPM = "AM";
            }
            return calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+"_"+amPM;
        } else if (cal instanceof CalendarDate){
            CalendarDate calendarDate = (CalendarDate) cal;
            if (calendarDate.isAM()){
                amPM = "AM";
            }
            return calendarDate.getHour()+":"+calendarDate.getMin()+"_"+amPM;
        }
        return "Not Set";
    }

    /**
     * Method to return the DateStamp from a CalendarDate passed in
     * @param date
     * @return String
     */
    public static String getDateStamp(CalendarDate date) {
        return Util.getDateStamp(date.getCalendar());
    }

    /**
     * Method to parse a DateStamp passed into int[] for day, month, and year
     * @param s
     * @return int[]
     */
    public static int[] parseDateStamp(String s) {
        s = s.trim();
        int i1 = s.indexOf("/");
        int i2 = s.indexOf("/", i1 + 1);
        int[] date = new int[3];
        date[0] = Integer.parseInt(s.substring(0, i1));
        date[1] = Integer.parseInt(s.substring(i1 + 1, i2));
        date[2] = Integer.parseInt(s.substring(i2 + 1));
        return date;
    }

    public static int[] parseDateStampNew(String s){
        String[] dateArray = s.split("/");
        int[] date;
        if (dateArray.length==3){
            date = new int[3];
        } else {
            date = new int[6];
            date[3] = Integer.parseInt(dateArray[3].split(":")[0]);
            date[4] = Integer.parseInt(dateArray[3].split(":")[1].split("_")[0]);
            if (dateArray[3].split(":")[1].split("_")[1].equalsIgnoreCase("AM")) {
                date[5] = 0;
            } else {
                date[5] = 1;
            }
        }
        date[0] = Integer.parseInt(dateArray[0]);
        date[1] = Integer.parseInt(dateArray[1]);
        date[2] = Integer.parseInt(dateArray[2]);
        return date;

    }

    /**
     * Method to get the environment director
     * @return String
     */
    public static String getEnvDir() {
    	// Changed static building of getEnvDir
    	// Now system-related path-separator is used
		String p = System.getProperty("user.home") + File.separator 
			+ ".jnotes2" + File.separator;
        if (new File(p).isDirectory()) return p;
        return System.getProperty("user.home") + File.separator 
        	+ ".memoranda" + File.separator;
    }

    /**
     * Method to get CDATA with String passed in
     * @param s
     * @return String
     */
    public static String getCDATA(String s) {
      return "<![CDATA["+s+"]]>";
    }

    /**
     * Method to run browser using the url string passed in
     * @param url
     */
    public static void runBrowser(String url) {
        if (!checkBrowser())
            return;
        String commandLine = MimeTypesList.getAppList().getBrowserExec()+" "+url;
        System.out.println("Run: " + commandLine);
        try {
            /*DEBUG*/
            Runtime.getRuntime().exec(commandLine);
        }
        catch (Exception ex) {
            new ExceptionDialog(ex, "Failed to run an external web-browser application with commandline<br><code>"
                    +commandLine+"</code>", "Check the application path and command line parameters " +
                    		"(File-&gt;Preferences-&gt;Resource types).");
        }
    }

    /**
     * Method to check the browser executable
     * @return boolean
     */
    public static boolean checkBrowser() {
        AppList appList = MimeTypesList.getAppList();
        String bpath = appList.getBrowserExec();
        if (bpath != null)
            if (new File(bpath).isFile())
                return true;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileHidingEnabled(false);
        chooser.setDialogTitle(Local.getString("Select the web-browser executable"));
        chooser.setAcceptAllFileFilterUsed(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        /*java.io.File lastSel = (java.io.File) Context.get("LAST_SELECTED_RESOURCE_FILE");
        if (lastSel != null)
            chooser.setCurrentDirectory(lastSel);*/
        if (chooser.showOpenDialog(App.getFrame()) != JFileChooser.APPROVE_OPTION)
            return false;
        appList.setBrowserExec(chooser.getSelectedFile().getPath());
        CurrentStorage.get().storeMimeTypesList();
        return true;
    }

    /**
     * Method to convert milliseconds to hours and return a String
     * @param ms
     * @return String
     */
    public static String getHoursFromMillis(long ms) {
    	double numSeconds = (((double) ms) / 1000d);
    	return String.valueOf(numSeconds / 3600);
    }

    /**
     * Method to convert hours to milliseconds
     * @param hours
     * @return String
     */
    public static long getMillisFromHours(String hours) {
    	try {
        	double numHours = Double.parseDouble(hours);
        	double millisDouble = (numHours * 3600 * 1000);
        	return (long) millisDouble;
    	}
    	catch (NumberFormatException e) {
    		return 0;
    	}
    }

    static Set tempFiles = new HashSet();

    /**
     * Initializer of AppFrame and adding ExitListener
     */
    static {
    	AppFrame.addExitListener(new ActionListener() {

            /**
             * Method to clean up when the program exits
             * @param arg0
             */
			public void actionPerformed(ActionEvent arg0) {
				for (Iterator i = tempFiles.iterator(); i.hasNext();) 
					((File)i.next()).delete();				}
			});
    }

    /**
     * Method to get the TempFile
     * @return File
     * @throws IOException
     */
    public static File getTempFile() throws IOException {
    	File f = File.createTempFile("tmp", ".html", null);
    	tempFiles.add(f);
    	return f;
    }

    /**
     * Method to print line in the console a debug statement
     * @param str
     */
    public static void debug(String str) {
    	System.out.println("[DEBUG] " + str);
    }

    /**
     * Method to print out an error in the console
     * @param e
     */
    public static void error(Exception e) {
        System.out.println("[ERROR] Exception: " + e.getClass().getName());
        System.out.println("[ERROR] Exception Message: " + e.getMessage());
        
        String stackTrace = "";
        StackTraceElement[] ste = e.getStackTrace();
        for (int i = 0; i < ste.length; i++) {
            stackTrace = ste[i].toString() + "\n";
        }
        System.out.println("[ERROR] Stack Trace: " + stackTrace);
    }
}
