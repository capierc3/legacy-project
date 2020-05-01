package main.java.memoranda.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.*;

import main.java.memoranda.gym.AppUsers;
import main.java.memoranda.gym.User;
import main.java.memoranda.util.Configuration;

/**
 * Main class that starts up the software, Builds the main frame and splash
 * screen.
 *
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

/* $Id: App.java,v 1.28 2007/03/20 06:21:46 alexeya Exp $ */
public class App {

    // keep state of AppUser
    static AppUsers appUsers = new AppUsers();

    // boolean packFrame = false;
    /** Main App frame **/
    static AppFrame frame = null;
    /** Main Urls to be used **/
    public static final String GUIDE_URL = "http://memoranda.sourceforge.net/guide.html";
    public static final String BUGS_TRACKER_URL = "http://sourceforge.net/tracker/?group_id=90997&atid=595566";
    public static final String WEBSITE_URL = "http://memoranda.sourceforge.net";
    /** Splash screen frame **/
    private JFrame splash = null;

    /**
     * version and build numbers, See comment above Note: Please DO NOT edit the
     * version/build info manually! The actual values are substituted by the Ant
     * build script using 'version' property and datestamp.
     **/
    public static final String VERSION_INFO = "@VERSION@";
    public static final String BUILD_INFO = "@BUILD@";

    /** Getter that returns the main frame **/
    public static AppFrame getFrame() {
	return frame;
    }

    /** Brings frame to front if it is visible, creates frame if not **/
    public void show() {
	if (frame.isVisible()) {
	    frame.toFront();
	    frame.requestFocus();
	} else
	    init();
    }

    /**
     * Main constructor for the app class.
     * 
     * @param fullmode boolean if app is to be started in fullscreen
     */
    public App(boolean fullmode) {
	super();
	if (fullmode)
	    fullmode = !Configuration.get("START_MINIMIZED").equals("yes");
	// debugging prints//
	if (!fullmode)
	    System.out.println("Minimized mode");
	System.out.println(VERSION_INFO);
	System.out.println(Configuration.get("LOOK_AND_FEEL"));
	// Shows the splash screen
	if (!Configuration.get("SHOW_SPLASH").equals("no"))
	    showSplash();
	// make the splash screen last a few extra seconds?
	try {
	    Thread.sleep(4000);
	} catch (Exception e) {
	}
	showLoginFrame();
	// sets the look and feel of the App
	try {
	    if (Configuration.get("LOOK_AND_FEEL").equals("system"))
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    else if (Configuration.get("LOOK_AND_FEEL").equals("default"))
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	    else if (Configuration.get("LOOK_AND_FEEL").toString().length() > 0)
		UIManager.setLookAndFeel(Configuration.get("LOOK_AND_FEEL").toString());

	} catch (Exception e) {
	    new ExceptionDialog(e, "Error when initializing a pluggable look-and-feel. Default LF will be used.",
		    "Make sure that specified look-and-feel library classes are on the CLASSPATH.");
	}
	// sets first day of the week setting, Sunday or Monday
	if (Configuration.get("FIRST_DAY_OF_WEEK").equals("")) {
	    String fdow;
	    if (Calendar.getInstance().getFirstDayOfWeek() == 2)
		fdow = "mon";
	    else
		fdow = "sun";
	    Configuration.put("FIRST_DAY_OF_WEEK", fdow);
	    Configuration.saveConfig();
	    /* DEBUG */
	    System.out.println("[DEBUG] first day of week is set to " + fdow);
	}
	// Set the frame to the main app frame
	frame = new AppFrame();
	// starts the app and kills the splash screen
	if (fullmode) {
	    init();
	}
	if (!Configuration.get("SHOW_SPLASH").equals("no"))
	    splash.dispose();

	// show login frame
    }

    /**
     * Creates the main frame for the App, packs it and sets it visible.
     */
    void init() {
	/*
	 * if (packFrame) { frame.pack(); } else { frame.validate(); }
	 * 
	 * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 * 
	 * Dimension frameSize = frame.getSize(); if (frameSize.height >
	 * screenSize.height) { frameSize.height = screenSize.height; } if
	 * (frameSize.width > screenSize.width) { frameSize.width = screenSize.width; }
	 * 
	 * 
	 * Make the window fullscreen - On Request of users This seems not to work on
	 * sun's version 1.4.1_01 Works great with 1.4.2 !!! So update your J2RE or
	 * J2SDK.
	 */
	/* Used to maximize the screen if the JVM Version if 1.4 or higher */
	/* --------------------------------------------------------------- */

	// Finds the java version used by the system and packs the frame based on the
	// version found.//
	double JVMVer = Double.parseDouble(System.getProperty("java.version").substring(0, 3));
	frame.pack();
	if (JVMVer >= 1.4) {
	    frame.setExtendedState(Frame.MAXIMIZED_BOTH);
	} else {
	    frame.setExtendedState(Frame.NORMAL);
	}
	// Not needed ???
	frame.setVisible(true);
	frame.toFront();
	frame.requestFocus();

    }

    /**
     * Closes the frame window and destroys all the resources used by the window.
     */
    public static void closeWindow() {
	if (frame == null)
	    return;
	frame.dispose();
	System.exit(0);
    }

    public static void minimizeWindow() {
	if (frame == null)
	    return;

    }

    /**
     * Creates and shows the splash screen.
     */
    private void showSplash() {
	splash = new JFrame();
	// Splash image goes here//
	ImageIcon spl = new ImageIcon(App.class.getResource("/ui/kaese-splash.png"));
	JLabel l = new JLabel();
	l.setSize(400, 300); // what is this size for, exactly? doesn't seem to affect splash size
	l.setIcon(spl);
	splash.getContentPane().add(l);
	splash.setSize(363, 420);
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	splash.setLocation((screenSize.width - 363) / 2, (screenSize.height - 420) / 2);
	splash.setUndecorated(true);
	splash.setVisible(true);
    }

    /**
     * Display login pane and verify user authentication
     */
    private void showLoginFrame() {
	LoginDialog dlg = new LoginDialog(splash, "Login", appUsers);
	Dimension frmSize = splash.getSize();
	Point loc = splash.getLocation();
	dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x,
		(frmSize.height - dlg.getSize().height) / 2 + loc.y);
	dlg.setVisible(true);
    }

    /**
     * On Click event for Submit button
     *
     * @param login    User's login credentials
     * @param password User's password credentials
     */
    private void loginSubmitButton_clicked(String login, String password) {

	Boolean verified = appUsers.verifyPassword(login, password);

	if (verified) {
	    User user = appUsers.getUser(login);
	    appUsers.setActiveUser(user);
	    splash.dispose();
	} else {
	    // notify user of incorrect credentials
	    // TODO: Change UI to reflect incorrect values
	}

    }

}