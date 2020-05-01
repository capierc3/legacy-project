/**
 * Javadoc Header
 */
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
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */

/* $Id: WorkPanel.java,v 1.9 2004/04/05 10:05:44 alexeya Exp $ */
public class WorkPanel extends JPanel {
    BorderLayout borderLayout1 = new BorderLayout();
    JToolBar toolBar = new JToolBar();
    JPanel panel = new JPanel();
    CardLayout cardLayout1 = new CardLayout();
    /*
     * Old Buttons public JButton notesB = new JButton(); public DailyItemsPanel
     * dailyItemsPanel = new DailyItemsPanel(this); public ResourcesPanel filesPanel
     * = new ResourcesPanel(); public JButton agendaB = new JButton(); public
     * JButton tasksB = new JButton(); public JButton eventsB = new JButton();
     * public JButton filesB = new JButton();
     */
    public DailyItemsPanel dailyItemsPanel = new DailyItemsPanel(this);
    public ResourcesPanel filesPanel = new ResourcesPanel();
    public TrainerProfilesPanel trainerPanel = new TrainerProfilesPanel();
    public RoomInfoPanel roomPanel = new RoomInfoPanel();
    public StudentListPanel studentPanel = new StudentListPanel();

    // previously agenda, agenda panel
    public JButton myInfo = new JButton();

    // new -- task panel
    public JButton mySchedule = new JButton();

    // new, -- Resources Panel
    public JButton trainerProfiles = new JButton();

    // new, resources panel
    public JButton roomInfo = new JButton();

    // ResourcesPanel
    public JButton studentList = new JButton();

    // ResourcesPanel
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
        /*
         * Setting up old buttons agendaB.setBackground(Color.white);
         * agendaB.setMaximumSize(new Dimension(60, 80)); agendaB.setMinimumSize(new
         * Dimension(30, 30));
         *
         * agendaB.setFont(new java.awt.Font("Dialog", 1, 10));
         * agendaB.setPreferredSize(new Dimension(50, 50));
         * agendaB.setBorderPainted(false); agendaB.setContentAreaFilled(false);
         * agendaB.setFocusPainted(false);
         * agendaB.setHorizontalTextPosition(SwingConstants.CENTER);
         * agendaB.setText(Local.getString("Agenda"));
         * agendaB.setVerticalAlignment(SwingConstants.TOP);
         * agendaB.setVerticalTextPosition(SwingConstants.BOTTOM);
         * agendaB.addActionListener(new java.awt.event.ActionListener() { public void
         * actionPerformed(ActionEvent e) { agendaB_actionPerformed(e); } });
         * agendaB.setIcon( new ImageIcon(
         * main.java.memoranda.ui.AppFrame.class.getResource( "/ui/icons/agenda.png")));
         * agendaB.setOpaque(false); agendaB.setMargin(new Insets(0, 0, 0, 0));
         * agendaB.setSelected(true);
         *
         * eventsB.setBackground(Color.white); eventsB.setMaximumSize(new Dimension(60,
         * 80)); eventsB.setMinimumSize(new Dimension(30, 30));
         *
         * eventsB.setFont(new java.awt.Font("Dialog", 1, 10));
         * eventsB.setPreferredSize(new Dimension(50, 50));
         * eventsB.setBorderPainted(false); eventsB.setContentAreaFilled(false);
         * eventsB.setFocusPainted(false);
         * eventsB.setHorizontalTextPosition(SwingConstants.CENTER);
         * eventsB.setText(Local.getString("Events"));
         * eventsB.setVerticalAlignment(SwingConstants.TOP);
         * eventsB.setVerticalTextPosition(SwingConstants.BOTTOM);
         * eventsB.addActionListener(new java.awt.event.ActionListener() { public void
         * actionPerformed(ActionEvent e) { eventsB_actionPerformed(e); } });
         * eventsB.setIcon( new ImageIcon(
         * main.java.memoranda.ui.AppFrame.class.getResource( "/ui/icons/events.png")));
         * eventsB.setOpaque(false); eventsB.setMargin(new Insets(0, 0, 0, 0));
         * //eventsB.setSelected(true);
         *
         * tasksB.setSelected(true); tasksB.setFont(new java.awt.Font("Dialog", 1, 10));
         * tasksB.setMargin(new Insets(0, 0, 0, 0)); tasksB.setIcon( new ImageIcon(
         * main.java.memoranda.ui.AppFrame.class.getResource( "/ui/icons/tasks.png")));
         * tasksB.setVerticalTextPosition(SwingConstants.BOTTOM);
         * tasksB.addActionListener(new java.awt.event.ActionListener() { public void
         * actionPerformed(ActionEvent e) { tasksB_actionPerformed(e); } });
         * tasksB.setVerticalAlignment(SwingConstants.TOP);
         * tasksB.setText(Local.getString("Tasks"));
         * tasksB.setHorizontalTextPosition(SwingConstants.CENTER);
         * tasksB.setFocusPainted(false); tasksB.setBorderPainted(false);
         * tasksB.setContentAreaFilled(false); tasksB.setPreferredSize(new Dimension(50,
         * 50)); tasksB.setMinimumSize(new Dimension(30, 30)); tasksB.setOpaque(false);
         * tasksB.setMaximumSize(new Dimension(60, 80));
         * tasksB.setBackground(Color.white);
         *
         * notesB.setFont(new java.awt.Font("Dialog", 1, 10));
         * notesB.setBackground(Color.white); notesB.setBorder(null);
         * notesB.setMaximumSize(new Dimension(60, 80)); notesB.setMinimumSize(new
         * Dimension(30, 30)); notesB.setOpaque(false); notesB.setPreferredSize(new
         * Dimension(60, 50)); notesB.setBorderPainted(false);
         * notesB.setContentAreaFilled(false); notesB.setFocusPainted(false);
         * notesB.setHorizontalTextPosition(SwingConstants.CENTER);
         * notesB.setText(Local.getString("Notes"));
         * notesB.setVerticalAlignment(SwingConstants.TOP);
         * notesB.setVerticalTextPosition(SwingConstants.BOTTOM);
         * notesB.addActionListener(new java.awt.event.ActionListener() { public void
         * actionPerformed(ActionEvent e) { notesB_actionPerformed(e); } });
         * notesB.setIcon( new ImageIcon(
         * main.java.memoranda.ui.AppFrame.class.getResource( "/ui/icons/notes.png")));
         * notesB.setMargin(new Insets(0, 0, 0, 0)); notesB.setSelected(true);
         * this.setPreferredSize(new Dimension(1073, 300));
         *
         * filesB.setSelected(true); filesB.setMargin(new Insets(0, 0, 0, 0));
         * filesB.setIcon( new ImageIcon(
         * main.java.memoranda.ui.AppFrame.class.getResource( "/ui/icons/files.png")));
         * filesB.setVerticalTextPosition(SwingConstants.BOTTOM);
         * filesB.addActionListener(new java.awt.event.ActionListener() { public void
         * actionPerformed(ActionEvent e) { filesB_actionPerformed(e); } });
         * filesB.setFont(new java.awt.Font("Dialog", 1, 10));
         * filesB.setVerticalAlignment(SwingConstants.TOP);
         * filesB.setText(Local.getString("Resources"));
         * filesB.setHorizontalTextPosition(SwingConstants.CENTER);
         * filesB.setFocusPainted(false); filesB.setBorderPainted(false);
         * filesB.setContentAreaFilled(false); filesB.setPreferredSize(new Dimension(50,
         * 50)); filesB.setMinimumSize(new Dimension(30, 30)); filesB.setOpaque(false);
         * filesB.setMaximumSize(new Dimension(60, 80));
         * filesB.setBackground(Color.white);
         */

        // New Buttons added on March 29, 2020 to reflect gym usages
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
            @Override
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
            @Override
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

        /*
         * upcomingClasses.setBackground(Color.white);
         * upcomingClasses.setMaximumSize(new Dimension(100, 100));
         * upcomingClasses.setMinimumSize(new Dimension(100, 100));
         *
         * upcomingClasses.setFont(new java.awt.Font("Dialog", 1, 10));
         * upcomingClasses.setPreferredSize(new Dimension(100, 100));
         * upcomingClasses.setBorderPainted(false);
         * upcomingClasses.setContentAreaFilled(false);
         * upcomingClasses.setFocusPainted(false);
         * upcomingClasses.setHorizontalTextPosition(SwingConstants.CENTER);
         * upcomingClasses.setText(Local.getString("Upcoming Classes"));
         * upcomingClasses.setVerticalAlignment(SwingConstants.TOP);
         * upcomingClasses.setVerticalTextPosition(SwingConstants.BOTTOM);
         * upcomingClasses.addActionListener(new java.awt.event.ActionListener() {
         * public void actionPerformed(ActionEvent e) {
         * upcomingClasses_actionPerformed(e); } }); upcomingClasses.setIcon( new
         * ImageIcon( main.java.memoranda.ui.AppFrame.class.getResource(
         * "/ui/icons/events.png"))); upcomingClasses.setOpaque(false);
         * upcomingClasses.setMargin(new Insets(0, 0, 0, 0));
         */

        trainerProfiles.setSelected(false);
        trainerProfiles.setMargin(new Insets(0, 0, 0, 0));
        trainerProfiles.setIcon(
                new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/trainer-profiles.png")));
        trainerProfiles.setVerticalTextPosition(SwingConstants.BOTTOM);
        trainerProfiles.addActionListener(new java.awt.event.ActionListener() {
            @Override
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
            @Override
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

        /*
         * bookTrainer.setBackground(Color.white); bookTrainer.setMaximumSize(new
         * Dimension(100, 100)); bookTrainer.setMinimumSize(new Dimension(100, 100));
         *
         * bookTrainer.setFont(new java.awt.Font("Dialog", 1, 10));
         * bookTrainer.setPreferredSize(new Dimension(100, 100));
         * bookTrainer.setBorderPainted(false); bookTrainer.setContentAreaFilled(false);
         * bookTrainer.setFocusPainted(false);
         * bookTrainer.setHorizontalTextPosition(SwingConstants.CENTER);
         * bookTrainer.setText(Local.getString("Book A Trainer"));
         * bookTrainer.setVerticalAlignment(SwingConstants.TOP);
         * bookTrainer.setVerticalTextPosition(SwingConstants.BOTTOM);
         * bookTrainer.addActionListener(new java.awt.event.ActionListener() { public
         * void actionPerformed(ActionEvent e) { bookTrainer_actionPerformed(e); } });
         * bookTrainer.setIcon( new ImageIcon(
         * main.java.memoranda.ui.AppFrame.class.getResource( "/ui/icons/agenda.png")));
         * bookTrainer.setOpaque(false); bookTrainer.setMargin(new Insets(0, 0, 0, 0));
         */

        studentList.setMargin(new Insets(0, 0, 0, 0));
        studentList.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/agenda.png")));
        studentList.setVerticalTextPosition(SwingConstants.BOTTOM);
        studentList.addActionListener(new java.awt.event.ActionListener() {
            @Override
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
            @Override
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

        /*
         * notesB.setFont(new java.awt.Font("Dialog", 1, 10));
         * notesB.setBackground(Color.white); notesB.setBorder(null);
         * notesB.setMaximumSize(new Dimension(100, 100)); notesB.setMinimumSize(new
         * Dimension(100, 100)); notesB.setOpaque(false); notesB.setPreferredSize(new
         * Dimension(100, 100)); notesB.setBorderPainted(false);
         * notesB.setContentAreaFilled(false); notesB.setFocusPainted(false);
         * notesB.setHorizontalTextPosition(SwingConstants.CENTER);
         * notesB.setText(Local.getString("My Notes"));
         * notesB.setVerticalAlignment(SwingConstants.TOP);
         * notesB.setVerticalTextPosition(SwingConstants.BOTTOM);
         * notesB.addActionListener(new java.awt.event.ActionListener() { public void
         * actionPerformed(ActionEvent e) { notesB_actionPerformed(e); } });
         * notesB.setIcon( new ImageIcon(
         * main.java.memoranda.ui.AppFrame.class.getResource( "/ui/icons/notes.png")));
         * notesB.setMargin(new Insets(0, 0, 0, 0)); notesB.setSelected(true);
         */

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

    /*
     * Not sure what this did, was causing errors and didn't seem functional so I
     * commented it out and tried to recreate below with udpated event names --
     * Justin
     *
     * public void selectPanel(String pan) { if (pan != null) { if
     * (pan.equals("NOTES")) notesB_actionPerformed(null); else if
     * (pan.equals("TASKS")) tasksB_actionPerformed(null); else if
     * (pan.equals("EVENTS")) eventsB_actionPerformed(null); else if
     * (pan.equals("FILES")) filesB_actionPerformed(null); } }
     */
    public void selectPanel(String pan) {
        if (pan != null) {
            /*
             * if (pan.equals("NOTES")) notesB_actionPerformed(null);
             *
             * else if (pan.equals("TASKS")) myCalendar_actionPerformed(null); else if
             * (pan.equals("EVENTS")) upcomingClasses_actionPerformed(null); else if
             * (pan.equals("FILES"))
             */
            if (pan.equals("MYINFO")) {
                myInfo_actionPerformed(null);
            } else if (pan.equals("MYSCHEDULE")) {
                mySchedule_actionPerformed(null);
            } else if (pan.equals("TRAINERPROFILES")) {
                trainerProfiles_actionPerformed(null);
            } else if (pan.equals("ROOMLIST")) {
                roomInfo_actionPerformed(null);
            } else if (pan.equals("STUDENTLIST")) {
                studentList_actionPerformed(null);
            } else if (pan.equals("RESOURCES")) {
                resources_actionPerformed(null);
            }
        }
    }

    public void myInfo_actionPerformed(ActionEvent e) {
        cardLayout1.show(panel, "DAILYITEMS");
        dailyItemsPanel.selectPanel("MYINFO");
        setCurrentButton(myInfo);
        Context.put("CURRENT_PANEL", "MYINFO");
    }

    public void mySchedule_actionPerformed(ActionEvent e) {
//		cardLayout1.show(panel, "DAILYITEMS");
//		dailyItemsPanel.selectPanel("TASKS");
//		setCurrentButton(myCalendar);
//		Context.put("CURRENT_PANEL", "TASKS");
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