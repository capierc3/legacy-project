package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import main.java.memoranda.util.Context;
import main.java.memoranda.util.Local;

/**
 * 
 * The main landing panel for Memoranda that contains the left nav buttons and
 * their functionality.
 *
 */

public class WorkPanel extends JPanel {
    // Creating new panels to lay on top
    BorderLayout borderLayout1 = new BorderLayout();
    JToolBar toolBar = new JToolBar();
    JPanel panel = new JPanel();
    CardLayout cardLayout1 = new CardLayout();
    public DailyItemsPanel dailyItemsPanel = new DailyItemsPanel(this);
    public ResourcesPanel filesPanel = new ResourcesPanel();
    public TrainerProfilesPanel trainerPanel = new TrainerProfilesPanel();
    public RoomInfoPanel roomPanel = new RoomInfoPanel();
    public StudentListPanel studentPanel = new StudentListPanel();

    // The buttons for the left nab
    public JButton myInfo = new JButton();
    public JButton mySchedule = new JButton();
    public JButton trainerProfiles = new JButton();
    public JButton roomInfo = new JButton();
    public JButton studentList = new JButton();
    public JButton resources = new JButton();

    JButton currentB = null;
    Border border1;

    public WorkPanel() {
	try {
	    jbInit();
	} catch (Exception ex) {
	    new ExceptionDialog(ex);
	}
    }

    void jbInit() throws Exception {
	border1 = BorderFactory
		.createCompoundBorder(
			BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white,
				new Color(124, 124, 124), new Color(178, 178, 178)),
			BorderFactory.createEmptyBorder(0, 2, 0, 0));

	this.setLayout(borderLayout1);
	toolBar.setOrientation(JToolBar.VERTICAL);
	toolBar.setBackground(Color.white);

	toolBar.setBorderPainted(false);
	toolBar.setFloatable(true);
	panel.setLayout(cardLayout1);
	myInfo.setBackground(Color.white);
	myInfo.setMaximumSize(new Dimension(100, 100));
	myInfo.setMinimumSize(new Dimension(100, 100));

	myInfo.setFont(new java.awt.Font("Dialog", 1, 10));
	myInfo.setPreferredSize(new Dimension(100, 100));
	myInfo.setBorderPainted(false);
	myInfo.setContentAreaFilled(false);
	myInfo.setFocusPainted(false);
	myInfo.setHorizontalTextPosition(SwingConstants.CENTER);
	myInfo.setText(Local.getString("My Info"));
	myInfo.setVerticalAlignment(SwingConstants.TOP);
	myInfo.setVerticalTextPosition(SwingConstants.BOTTOM);
	myInfo.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		myInfo_actionPerformed(e);
	    }
	});
	myInfo.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/personal_info.png")));
	myInfo.setOpaque(false);
	myInfo.setMargin(new Insets(0, 0, 0, 0));
	myInfo.setSelected(true);

	mySchedule.setSelected(false);
	mySchedule.setFont(new java.awt.Font("Dialog", 1, 10));
	mySchedule.setMargin(new Insets(0, 0, 0, 0));
	mySchedule.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/tasks.png")));
	mySchedule.setVerticalTextPosition(SwingConstants.BOTTOM);
	mySchedule.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		mySchedule_actionPerformed(e);
	    }
	});
	mySchedule.setVerticalAlignment(SwingConstants.TOP);
	mySchedule.setText(Local.getString("My Schedule"));
	mySchedule.setHorizontalTextPosition(SwingConstants.CENTER);
	mySchedule.setFocusPainted(false);
	mySchedule.setBorderPainted(false);
	mySchedule.setContentAreaFilled(false);
	mySchedule.setPreferredSize(new Dimension(100, 100));
	mySchedule.setMinimumSize(new Dimension(100, 100));
	mySchedule.setOpaque(false);
	mySchedule.setMaximumSize(new Dimension(100, 100));
	mySchedule.setBackground(Color.red);

	trainerProfiles.setSelected(false);
	trainerProfiles.setMargin(new Insets(0, 0, 0, 0));
	trainerProfiles.setIcon(
		new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/trainer-profiles.png")));
	trainerProfiles.setVerticalTextPosition(SwingConstants.BOTTOM);
	trainerProfiles.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		trainerProfiles_actionPerformed(e);
	    }
	});
	trainerProfiles.setFont(new java.awt.Font("Dialog", 1, 10));
	trainerProfiles.setVerticalAlignment(SwingConstants.TOP);
	trainerProfiles.setText(Local.getString("Trainer Profiles"));
	trainerProfiles.setHorizontalTextPosition(SwingConstants.CENTER);
	trainerProfiles.setFocusPainted(false);
	trainerProfiles.setBorderPainted(false);
	trainerProfiles.setContentAreaFilled(false);
	trainerProfiles.setPreferredSize(new Dimension(100, 100));
	trainerProfiles.setMinimumSize(new Dimension(100, 100));
	trainerProfiles.setOpaque(false);
	trainerProfiles.setMaximumSize(new Dimension(100, 100));
	trainerProfiles.setBackground(Color.white);

	roomInfo.setSelected(false);
	roomInfo.setMargin(new Insets(0, 0, 0, 0));
	roomInfo.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/room.png")));
	roomInfo.setVerticalTextPosition(SwingConstants.BOTTOM);
	roomInfo.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		roomInfo_actionPerformed(e);
	    }
	});
	roomInfo.setFont(new java.awt.Font("Dialog", 1, 10));
	roomInfo.setVerticalAlignment(SwingConstants.TOP);
	roomInfo.setText(Local.getString("Room Info"));
	roomInfo.setHorizontalTextPosition(SwingConstants.CENTER);
	roomInfo.setFocusPainted(false);
	roomInfo.setBorderPainted(false);
	roomInfo.setContentAreaFilled(false);
	roomInfo.setPreferredSize(new Dimension(100, 100));
	roomInfo.setMinimumSize(new Dimension(100, 100));
	roomInfo.setOpaque(false);
	roomInfo.setMaximumSize(new Dimension(100, 100));
	roomInfo.setBackground(Color.white);

	studentList.setMargin(new Insets(0, 0, 0, 0));
	studentList.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/agenda.png")));
	studentList.setVerticalTextPosition(SwingConstants.BOTTOM);
	studentList.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		studentList_actionPerformed(e);
	    }
	});
	studentList.setFont(new java.awt.Font("Dialog", 1, 10));
	studentList.setVerticalAlignment(SwingConstants.TOP);
	studentList.setText(Local.getString("Student List"));
	studentList.setHorizontalTextPosition(SwingConstants.CENTER);
	studentList.setFocusPainted(false);
	studentList.setBorderPainted(false);
	studentList.setContentAreaFilled(false);
	studentList.setPreferredSize(new Dimension(100, 100));
	studentList.setMinimumSize(new Dimension(100, 100));
	studentList.setOpaque(false);
	studentList.setMaximumSize(new Dimension(100, 100));
	studentList.setBackground(Color.white);
	studentList.setSelected(false);

	this.setPreferredSize(new Dimension(1073, 300));

	resources.setMargin(new Insets(0, 0, 0, 0));
	resources.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/notes.png")));
	resources.setVerticalTextPosition(SwingConstants.BOTTOM);
	resources.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		resources_actionPerformed(e);
	    }
	});
	resources.setFont(new java.awt.Font("Dialog", 1, 10));
	resources.setVerticalAlignment(SwingConstants.TOP);
	resources.setText(Local.getString("Resources"));
	resources.setHorizontalTextPosition(SwingConstants.CENTER);
	resources.setFocusPainted(false);
	resources.setBorderPainted(false);
	resources.setContentAreaFilled(false);
	resources.setPreferredSize(new Dimension(100, 100));
	resources.setMinimumSize(new Dimension(100, 100));
	resources.setOpaque(false);
	resources.setMaximumSize(new Dimension(100, 100));
	resources.setBackground(Color.white);
	resources.setSelected(false);

	// Add buttons to panel
	this.add(toolBar, BorderLayout.WEST);
	this.add(panel, BorderLayout.CENTER);
	panel.add(dailyItemsPanel, "DAILYITEMS");
	panel.add(filesPanel, "FILES");
	panel.add(trainerPanel, "TRAINERPROFILES");
	panel.add(studentPanel, "STUDENTLIST");
	panel.add(roomPanel, "ROOMLIST");
	toolBar.add(myInfo, null);
	toolBar.add(mySchedule, null);
	toolBar.add(trainerProfiles, null);
	toolBar.add(roomInfo, null);
	toolBar.add(studentList, null);
	toolBar.add(resources, null);

	currentB = myInfo;
	// Default blue color
	currentB.setBackground(new Color(215, 225, 250));
	currentB.setOpaque(true);

	toolBar.setBorder(null);
	panel.setBorder(null);
	dailyItemsPanel.setBorder(null);
	filesPanel.setBorder(null);

    }

    public void selectPanel(String pan) {
	if (pan != null) {
	    if (pan.equals("MYINFO"))
		myInfo_actionPerformed(null);
	    else if (pan.equals("MYSCHEDULE"))
		mySchedule_actionPerformed(null);
	    else if (pan.equals("TRAINERPROFILES"))
		trainerProfiles_actionPerformed(null);
	    else if (pan.equals("ROOMLIST"))
		roomInfo_actionPerformed(null);
	    else if (pan.equals("STUDENTLIST"))
		studentList_actionPerformed(null);
	    else if (pan.equals("RESOURCES"))
		resources_actionPerformed(null);
	}
    }

    public void myInfo_actionPerformed(ActionEvent e) {
	cardLayout1.show(panel, "DAILYITEMS");
	dailyItemsPanel.selectPanel("MYINFO");
	setCurrentButton(myInfo);
	Context.put("CURRENT_PANEL", "MYINFO");
    }

    public void mySchedule_actionPerformed(ActionEvent e) {
	cardLayout1.show(panel, "DAILYITEMS");
	dailyItemsPanel.selectPanel("EVENTS");
	setCurrentButton(mySchedule);
	Context.put("CURRENT_PANEL", "EVENTS");
    }

    public void trainerProfiles_actionPerformed(ActionEvent e) {
	cardLayout1.show(panel, "TRAINERPROFILES");
	setCurrentButton(trainerProfiles);
	Context.put("CURRENT_PANEL", "TRAINERPROFILES");
    }

    public void roomInfo_actionPerformed(ActionEvent e) {
	cardLayout1.show(panel, "ROOMLIST");
	setCurrentButton(roomInfo);
	Context.put("CURRENT_PANEL", "ROOMLIST");
    }

    public void studentList_actionPerformed(ActionEvent e) {
	cardLayout1.show(panel, "STUDENTLIST");
	setCurrentButton(studentList);
	Context.put("CURRENT_PANEL", "STUDENTLIST");
    }

    public void resources_actionPerformed(ActionEvent e) {
	cardLayout1.show(panel, "FILES");
	setCurrentButton(resources);
	Context.put("CURRENT_PANEL", "FILES");
    }

    void setCurrentButton(JButton cb) {
	currentB.setBackground(Color.white);
	currentB.setOpaque(false);
	currentB = cb;
	// Default color blue
	currentB.setBackground(new Color(215, 225, 250));
	currentB.setOpaque(true);
    }
}