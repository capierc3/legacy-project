package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.gym.*;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.Util;

/**
 * This is the main class for the MySchedule display in the ui.
 * @author Chase
 *
 */
public class MySchedulePanel extends JPanel {
    MyScheduleManager manager;
    BorderLayout borderLayout1 = new BorderLayout();
    JToolBar eventsToolBar = new JToolBar();
    //JButton historyBackB = new JButton();
    //JButton historyForwardB = new JButton();
    JButton newClassB = new JButton();
    JButton editClassB = new JButton();
    JButton removeClassB = new JButton();
    JButton addClassB = new JButton();
    JButton dropClassB = new JButton();
    JScrollPane scrollPane = new JScrollPane();
    MyScheduleTable classTable = new MyScheduleTable();
    JPopupMenu classPPMenu = new JPopupMenu();
    JMenuItem ppEditClass = new JMenuItem();
    JMenuItem ppRemoveClass = new JMenuItem();
    JMenuItem ppNewClass = new JMenuItem();
    JMenuItem ppAddClass = new JMenuItem();
    JMenuItem ppDropClass = new JMenuItem();
    DailyItemsPanel parentPanel = null;
    String[] sorts = new String[]{"Date","Rank","Trainer","Name"};
    JComboBox sortCombo = new JComboBox(sorts);
    JButton sortDirB = new JButton();
    String[] listTypesA;
    JComboBox listTypeBox;
    //Early setup for UI changes based on user
    //User user;


    MySchedulePanel(DailyItemsPanel _parentPanel) {

        try {
            parentPanel = _parentPanel;
            jbInit();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }
    void jbInit() throws Exception {
        manager = App.appUsers.getManager();
        eventsToolBar.setFloatable(false);
        //Set toolbar buttons
        if (manager.getUser() instanceof Owner) {
            newClassB.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/event_new.png")));
            newClassB.setEnabled(true);
            newClassB.setMaximumSize(new Dimension(24, 24));
            newClassB.setMinimumSize(new Dimension(24, 24));
            newClassB.setToolTipText(Local.getString("New Class"));
            newClassB.setRequestFocusEnabled(false);
            newClassB.setPreferredSize(new Dimension(24, 24));
            newClassB.setFocusable(false);
            newClassB.addActionListener(this::newEventB_actionPerformed);
            newClassB.setBorderPainted(false);

            editClassB.setBorderPainted(false);
            editClassB.setFocusable(false);
            editClassB.addActionListener(this::editClassB_actionPerformed);
            editClassB.setPreferredSize(new Dimension(24, 24));
            editClassB.setRequestFocusEnabled(false);
            editClassB.setToolTipText(Local.getString("Edit Class"));
            editClassB.setMinimumSize(new Dimension(24, 24));
            editClassB.setMaximumSize(new Dimension(24, 24));
            editClassB.setEnabled(true);
            editClassB.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/event_edit.png")));

            removeClassB.setBorderPainted(false);
            removeClassB.setFocusable(false);
            removeClassB.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    removeEventB_actionPerformed(e);
                }
            });
            removeClassB.setPreferredSize(new Dimension(24, 24));
            removeClassB.setRequestFocusEnabled(false);
            removeClassB.setToolTipText(Local.getString("Remove Class"));
            removeClassB.setMinimumSize(new Dimension(24, 24));
            removeClassB.setMaximumSize(new Dimension(24, 24));
            removeClassB.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/event_remove.png")));
            listTypesA = new String[] {"All Classes","Teaching","Student"};
            listTypeBox = new JComboBox(listTypesA);
            listTypeBox.setSelectedIndex(0);
            listTypeBox.addActionListener(this::listBox_actionPerformed);
            listTypeBox.setMaximumSize(listTypeBox.getPreferredSize());

        } else if (manager.getUser() instanceof Student || manager.getUser() instanceof Trainer) {

            addClassB.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/event_new.png")));
            addClassB.setEnabled(true);
            addClassB.setMaximumSize(new Dimension(24, 24));
            addClassB.setMinimumSize(new Dimension(24, 24));
            addClassB.setToolTipText(Local.getString("Register"));
            addClassB.setRequestFocusEnabled(false);
            addClassB.setPreferredSize(new Dimension(24, 24));
            addClassB.setFocusable(false);
            addClassB.addActionListener(this::addClassB_actionPerformed);
            addClassB.setBorderPainted(false);

            dropClassB.setBorderPainted(false);
            dropClassB.setFocusable(false);
            dropClassB.addActionListener(this::dropClassB_actionPerformed);
            dropClassB.setPreferredSize(new Dimension(24, 24));
            dropClassB.setRequestFocusEnabled(false);
            dropClassB.setToolTipText(Local.getString("Drop Class"));
            dropClassB.setMinimumSize(new Dimension(24, 24));
            dropClassB.setMaximumSize(new Dimension(24, 24));
            dropClassB.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/event_remove.png")));
            listTypesA = new String[] {"All Classes","My Classes"};
            listTypeBox = new JComboBox(listTypesA);
            listTypeBox.setSelectedIndex(0);
            listTypeBox.addActionListener(this::listBox_actionPerformed);
            listTypeBox.setMaximumSize(listTypeBox.getPreferredSize());
        }
        sortCombo.setSelectedIndex(0);
        sortCombo.addActionListener(this::sortBox_actionPerformed);
        sortCombo.setMaximumSize(sortCombo.getPreferredSize());
        sortDirB.setIcon(
                new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Down.png")));
        sortDirB.setEnabled(true);
        sortDirB.setMaximumSize(new Dimension(24, 24));
        sortDirB.setMinimumSize(new Dimension(24, 24));
        sortDirB.setToolTipText(Local.getString("Ascending"));
        sortDirB.setRequestFocusEnabled(false);
        sortDirB.setPreferredSize(new Dimension(24, 24));
        sortDirB.setFocusable(false);
        sortDirB.addActionListener(this::sortDirB_actionPerformed);
        sortDirB.setBorderPainted(false);

        this.setLayout(borderLayout1);
        scrollPane.getViewport().setBackground(Color.white);
        classTable.setMaximumSize(new Dimension(32767, 32767));
        classTable.setRowHeight(24);
        classPPMenu.setFont(new java.awt.Font("Dialog", 1, 10));
        //sets right click menu
        if (manager.getUser() instanceof Owner) {
            ppEditClass.setFont(new java.awt.Font("Dialog", 1, 11));
            ppEditClass.setText(Local.getString("Edit Class") + "...");
            ppEditClass.addActionListener(this::ppEditEvent_actionPerformed);
            ppEditClass.setEnabled(false);
            ppEditClass.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/event_edit.png")));
            ppRemoveClass.setFont(new java.awt.Font("Dialog", 1, 11));
            ppRemoveClass.setText(Local.getString("Remove Class"));
            ppRemoveClass.addActionListener(this::ppRemoveEvent_actionPerformed);
            ppRemoveClass.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/event_remove.png")));
            ppRemoveClass.setEnabled(false);
            ppNewClass.setFont(new java.awt.Font("Dialog", 1, 11));
            ppNewClass.setText(Local.getString("New Class") + "...");
            ppNewClass.addActionListener(this::ppNewEvent_actionPerformed);
            ppNewClass.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/event_new.png")));
        } else if (manager.getUser() instanceof Student || manager.getUser() instanceof Trainer){
            ppAddClass.setFont(new java.awt.Font("Dialog", 1, 11));
            ppAddClass.setText(Local.getString("Register") + "...");
            ppAddClass.addActionListener(this::ppAddClass_actionPerformed);
            ppAddClass.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/event_new.png")));

            ppDropClass.setFont(new java.awt.Font("Dialog", 1, 11));
            ppDropClass.setText(Local.getString("Drop Class"));
            ppDropClass.addActionListener(this::ppDropClass_actionPerformed);
            ppDropClass.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/event_remove.png")));
            ppDropClass.setEnabled(false);
        }
        scrollPane.getViewport().add(classTable, null);
        this.add(scrollPane, BorderLayout.CENTER);
        eventsToolBar.addSeparator(new Dimension(8, 24));
        //fills the toolbar
        if (manager.getUser() instanceof Owner) {
            eventsToolBar.add(newClassB, null);
            eventsToolBar.add(removeClassB, null);
            eventsToolBar.addSeparator(new Dimension(8, 24));
            eventsToolBar.add(editClassB, null);
        } else if (manager.getUser() instanceof Student || manager.getUser() instanceof Trainer){
            eventsToolBar.add(addClassB,null);
            eventsToolBar.add(dropClassB,null);
        }
        eventsToolBar.addSeparator(new Dimension(8,24));
        eventsToolBar.add(new JLabel("Show: "));
        eventsToolBar.add(listTypeBox,null);
        eventsToolBar.addSeparator(new Dimension(8,24));
        eventsToolBar.add(new JLabel("Sort By: "),null);
        eventsToolBar.add(sortCombo,null);
        eventsToolBar.add(sortDirB,null);
        eventsToolBar.addSeparator(new Dimension(8, 24));

        this.add(eventsToolBar, BorderLayout.NORTH);

        PopupListener ppListener = new PopupListener();
        scrollPane.addMouseListener(ppListener);
        classTable.addMouseListener(ppListener);

        CurrentDate.addDateListener(d -> {
            classTable.initTable(d);
            boolean enbl = d.after(CalendarDate.today()) || d.equals(CalendarDate.today());
            if (manager.getUser() instanceof Owner) {
                newClassB.setEnabled(enbl);
                ppNewClass.setEnabled(enbl);
                editClassB.setEnabled(false);
                ppEditClass.setEnabled(false);
                removeClassB.setEnabled(false);
                ppRemoveClass.setEnabled(false);
            } else if (manager.getUser() instanceof Student || manager.getUser() instanceof Trainer){
                addClassB.setEnabled(enbl);
                ppAddClass.setEnabled(enbl);
                dropClassB.setEnabled(enbl);
                ppDropClass.setEnabled(enbl);
            }
        });
        classTable.getSelectionModel().addListSelectionListener(e -> {
            boolean enbl = classTable.getSelectedRow() > -1;
            if (manager.getUser() instanceof Owner) {
                editClassB.setEnabled(enbl);
                ppEditClass.setEnabled(enbl);
                removeClassB.setEnabled(enbl);
                ppRemoveClass.setEnabled(enbl);
            } else if (manager.getUser() instanceof Student || manager.getUser() instanceof Trainer){
                addClassB.setEnabled(enbl);
                ppAddClass.setEnabled(enbl);
                dropClassB.setEnabled(enbl);
                ppDropClass.setEnabled(enbl);
            }
        });
        editClassB.setEnabled(false);
        removeClassB.setEnabled(false);
        //fills the right click menu
        if (manager.getUser() instanceof Owner) {
            classPPMenu.add(ppEditClass);
            classPPMenu.addSeparator();
            classPPMenu.add(ppNewClass);
            classPPMenu.add(ppRemoveClass);
        } else if (manager.getUser() instanceof Student || manager.getUser() instanceof Trainer){
            classPPMenu.add(ppAddClass);
            classPPMenu.add(ppDropClass);
        }
    }

//    private void editEventB_actionPerformed(ActionEvent e) {
//        EventDialog dlg = new EventDialog(App.getFrame(), Local.getString("Event"));
//        main.java.memoranda.Event ev =
//            (main.java.memoranda.Event) eventsTable.getModel().getValueAt(
//                eventsTable.getSelectedRow(),
//                EventsTable.EVENT);
//
//        dlg.timeSpin.getModel().setValue(ev.getTime());
//        /*if (new CalendarDate(ev.getTime()).equals(CalendarDate.today()))
//            ((SpinnerDateModel)dlg.timeSpin.getModel()).setStart(new Date());
//        else
//        ((SpinnerDateModel)dlg.timeSpin.getModel()).setStart(CalendarDate.today().getDate());
//        ((SpinnerDateModel)dlg.timeSpin.getModel()).setEnd(CalendarDate.tomorrow().getDate());*/
//        dlg.textField.setText(ev.getText());
//        int rep = ev.getRepeat();
//        if (rep > 0) {
//            dlg.startDate.getModel().setValue(ev.getStartDate().getDate());
//            if (rep == EventsManager.REPEAT_DAILY) {
//                dlg.dailyRepeatRB.setSelected(true);
//                dlg.dailyRepeatRB_actionPerformed(null);
//                dlg.daySpin.setValue(ev.getPeriod());
//            }
//            else if (rep == EventsManager.REPEAT_WEEKLY) {
//                dlg.weeklyRepeatRB.setSelected(true);
//                dlg.weeklyRepeatRB_actionPerformed(null);
//		int d = ev.getPeriod() - 1;
//		if(Configuration.get("FIRST_DAY_OF_WEEK").equals("mon")) {
//		    d--;
//		    if(d<0) d=6;
//		}
//                dlg.weekdaysCB.setSelectedIndex(d);
//            }
//            else if (rep == EventsManager.REPEAT_MONTHLY) {
//                dlg.monthlyRepeatRB.setSelected(true);
//                dlg.monthlyRepeatRB_actionPerformed(null);
//                dlg.dayOfMonthSpin.setValue(ev.getPeriod());
//            }
//	    else if (rep == EventsManager.REPEAT_YEARLY) {
//		dlg.yearlyRepeatRB.setSelected(true);
//		dlg.yearlyRepeatRB_actionPerformed(null);
//		dlg.dayOfMonthSpin.setValue(new Integer(ev.getPeriod()));
//	    }
//        if (ev.getEndDate() != null) {
//           dlg.endDate.getModel().setValue(ev.getEndDate().getDate());
//           dlg.enableEndDateCB.setSelected(true);
//           dlg.enableEndDateCB_actionPerformed(null);
//        }
//		if(ev.getWorkingDays()) {
//			dlg.workingDaysOnlyCB.setSelected(true);
//		}
//
//        }
//
//        Dimension frmSize = App.getFrame().getSize();
//        Point loc = App.getFrame().getLocation();
//        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
//        dlg.setVisible(true);
//        if (dlg.CANCELLED)
//            return;
//        EventsManager.removeEvent(ev);
//
//		Calendar calendar = new GregorianCalendar(Local.getCurrentLocale()); //Fix deprecated methods to get hours
//		//by (jcscoobyrs) 14-Nov-2003 at 10:24:38 AM
//		calendar.setTime(((Date)dlg.timeSpin.getModel().getValue()));//Fix deprecated methods to get hours
//		//by (jcscoobyrs) 14-Nov-2003 at 10:24:38 AM
//		int hh = calendar.get(Calendar.HOUR_OF_DAY);//Fix deprecated methods to get hours
//		//by (jcscoobyrs) 14-Nov-2003 at 10:24:38 AM
//		int mm = calendar.get(Calendar.MINUTE);//Fix deprecated methods to get hours
//		//by (jcscoobyrs) 14-Nov-2003 at 10:24:38 AM
//
//        //int hh = ((Date) dlg.timeSpin.getModel().getValue()).getHours();
//        //int mm = ((Date) dlg.timeSpin.getModel().getValue()).getMinutes();
//        String text = dlg.textField.getText();
//        if (dlg.noRepeatRB.isSelected())
//   	    EventsManager.createEvent(CurrentDate.get(), hh, mm, text);
//        else {
//	    updateEvents(dlg,hh,mm,text);
//	}
//	saveEvents();
//    }

    private void newEventB_actionPerformed(ActionEvent e) {
        Calendar cdate1 = CurrentDate.get().getCalendar();
        Calendar cdate2 = CurrentDate.get().getCalendar();
        // round down to hour
        cdate1.set(Calendar.MINUTE,0);
        cdate2.set(Calendar.MINUTE,0);
        cdate2.add(Calendar.HOUR_OF_DAY,1);
        Util.debug("Default time is " + cdate1);
        
    	newEventB_actionPerformed(e, cdate1.getTime(), cdate2.getTime());
    }
    private void editClassB_actionPerformed(ActionEvent e){
        GymClass gymClass = (GymClass) classTable.getModel().getValueAt(
                classTable.getSelectedRow(),
                MyScheduleTable.EVENT);
        editEventB_actionPerformed(e,gymClass);
    }
    private void addClassB_actionPerformed(ActionEvent e) {
        String msg;
        GymClass gymClass;

        if(classTable.getSelectedRows().length > 1)
            msg = Local.getString("Register for") + " " + classTable.getSelectedRows().length
                    + " " + Local.getString("classes") + "\n" + Local.getString("Are you sure?");
        else {
            gymClass = (GymClass) classTable.getModel().getValueAt(
                    classTable.getSelectedRow(),
                    MyScheduleTable.EVENT);
            msg = Local.getString("Register for class") + "\n'"
                    + gymClass.getName() + "'\n" + Local.getString("Are you sure?");
        }

        int n =
                JOptionPane.showConfirmDialog(
                        App.getFrame(),
                        msg,
                        Local.getString("Register for class"),
                        JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION){
            return;
        }

        for(int i = 0; i< classTable.getSelectedRows().length; i++) {
            gymClass = (GymClass) classTable.getModel().getValueAt(
                    classTable.getSelectedRows()[i], MyScheduleTable.EVENT);
            if (gymClass.isFull()){
                addError(gymClass,0);
            } else if (gymClass.getRank().getValue()>manager.getUser().getBelt().getValue()){
                addError(gymClass,1);
            }
            manager.addClass(gymClass);
        }
        classTable.getSelectionModel().clearSelection();
        classTable.refresh();
    }
    private void dropClassB_actionPerformed(ActionEvent e) {
        String msg;
        GymClass gymClass;

        if(classTable.getSelectedRows().length > 1)
            msg = Local.getString("Drop") + " " + classTable.getSelectedRows().length
                    + " " + Local.getString("Classes") + "\n" + Local.getString("Are you sure?");
        else {
            gymClass = (GymClass) classTable.getModel().getValueAt(
                    classTable.getSelectedRow(),
                    MyScheduleTable.EVENT);
            msg = Local.getString("Drop ") + "\n'"
                    + gymClass.getName() + "'\n" + Local.getString("Are you sure?");
        }

        int n =
                JOptionPane.showConfirmDialog(
                        App.getFrame(),
                        msg,
                        Local.getString("Drop Class"),
                        JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION) return;

        for(int i = 0; i< classTable.getSelectedRows().length; i++) {
            gymClass = (GymClass) classTable.getModel().getValueAt(
                    classTable.getSelectedRows()[i], MyScheduleTable.EVENT);
            manager.removeClass(gymClass);
        }
        classTable.getSelectionModel().clearSelection();
        classTable.refresh();
    }
    private void sortBox_actionPerformed(ActionEvent e) {
        int sort = sortCombo.getSelectedIndex();
        String dir =  sortDirB.getToolTipText();
        String listName = (String) listTypeBox.getSelectedItem();
        manager.updateLists(listName,sort,dir);
        classTable.refresh();

    }
    private void sortDirB_actionPerformed(ActionEvent e) {
        if (sortDirB.getToolTipText().equalsIgnoreCase("Descending")) {
            sortDirB.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Down.png")));
            sortDirB.setToolTipText(Local.getString("Ascending"));
            sortBox_actionPerformed(e);
        } else {
            sortDirB.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Up.png")));
            sortDirB.setToolTipText(Local.getString("Descending"));
            sortBox_actionPerformed(e);
        }
    }
    private void listBox_actionPerformed(ActionEvent e) {

    }
    void newEventB_actionPerformed(ActionEvent e, Date startDate, Date endDate) {
    	ClassDialog dlg = new ClassDialog(App.getFrame(), Local.getString("Create  Class"));
    	Dimension frmSize = App.getFrame().getSize();
    	Point loc = App.getFrame().getLocation();
    	dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
    	dlg.setEventDate(startDate);
		dlg.setVisible(true);
    	if (dlg.CANCELLED)
    		return;
    	String name = dlg.textField.getText();
        Belt rank = Belt.getBelt(dlg.beltBox.getSelectedIndex());
        CalendarDate start = comboValueToDate((String) dlg.startTime.getSelectedItem());
        CalendarDate end = comboValueToDate((String) dlg.endTime.getSelectedItem());
        GymClass gymClass = new GymClassImpl(name,"Public",rank,start,end);
        gymClass.setSize((int) dlg.sizeSpin.getValue());
        gymClass.addTrainer((Trainer) dlg.trainerBox.getSelectedItem());
        manager.addClass(gymClass);
        classTable.refresh();
    }
    private void editEventB_actionPerformed(ActionEvent e, GymClass gymClass) {
        ClassDialog dlg = new ClassDialog(App.getFrame(), Local.getString("Edit Class"));
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setEventDate(gymClass.getStartDate().getDate());
        dlg.textField.setText(gymClass.getName());
        dlg.beltBox.setSelectedIndex(gymClass.getRank().getValue());
        dlg.startTime.setSelectedIndex(findIndexValue(gymClass.getStartDate()));
        dlg.endTime.setSelectedIndex(findIndexValue(gymClass.getEndDate()));
        dlg.sizeSpin.setValue(gymClass.getMaxSize());
        dlg.trainerBox.setSelectedItem(gymClass.getTrainer());
        dlg.setVisible(true);
        if (dlg.CANCELLED) return;
        CalendarDate start = comboValueToDate((String) dlg.startTime.getSelectedItem());
        CalendarDate end = comboValueToDate((String) dlg.endTime.getSelectedItem());
        gymClass.setName(dlg.textField.getText());
        gymClass.setRank(Belt.getBelt(dlg.beltBox.getSelectedIndex()));
        gymClass.setSize((Integer) dlg.sizeSpin.getValue());
        gymClass.setStartDate(start);
        gymClass.setEndDate(end);
        gymClass.addTrainer((Trainer) dlg.trainerBox.getSelectedItem());
        classTable.refresh();
    }

    private int findIndexValue(CalendarDate date){
        int value;
        if (date.getHour()<12){
            value = (date.getHour()-1)*4;
            if (!date.isAM()) {
                value += 48;
            }
        } else {
            if (date.isAM()) {
                value = 93;
            } else {
                value = 44;
            }
        }
        if (date.getMin()==15){
            value+=1;
        } else if (date.getMin()==30){
            value+=2;
        } else if (date.getMin()==45){
            value+=3;
        }
        return value;
    }

    private void removeEventB_actionPerformed(ActionEvent e) {
		String msg;
		GymClass gymClass;

		if(classTable.getSelectedRows().length > 1)
			msg = Local.getString("Remove") + " " + classTable.getSelectedRows().length
				+ " " + Local.getString("Classes") + "\n" + Local.getString("Are you sure?");
		else {
			gymClass = (GymClass) classTable.getModel().getValueAt(
                classTable.getSelectedRow(),
                MyScheduleTable.EVENT);
			msg = Local.getString("Remove Class") + "\n'"
				+ gymClass.getName() + "'\n" + Local.getString("Are you sure?");
		}

        int n =
            JOptionPane.showConfirmDialog(
                App.getFrame(),
                msg,
                Local.getString("Remove Class"),
                JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION) return;

        for(int i = 0; i< classTable.getSelectedRows().length; i++) {
			gymClass = (GymClass) classTable.getModel().getValueAt(
                  classTable.getSelectedRows()[i], MyScheduleTable.EVENT);
        manager.removeClass(gymClass);
		}
        classTable.getSelectionModel().clearSelection();
        classTable.refresh();
    }

    class PopupListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if ((e.getClickCount() == 2) && (classTable.getSelectedRow() > -1)) {
                //editEventB_actionPerformed(null);
            }
        }
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }
        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                classPPMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }

    }

    private void ppEditEvent_actionPerformed(ActionEvent e) {
        editClassB_actionPerformed(e);
    }
    private void ppRemoveEvent_actionPerformed(ActionEvent e) {
        removeEventB_actionPerformed(e);
    }
    private void ppNewEvent_actionPerformed(ActionEvent e) {
        newEventB_actionPerformed(e);
    }
    private void ppAddClass_actionPerformed(ActionEvent e){
        addClassB_actionPerformed(e);
    }
    private void ppDropClass_actionPerformed(ActionEvent e){
        dropClassB_actionPerformed(e);
    }

    private CalendarDate comboValueToDate(String s){
        CalendarDate date = new CalendarDate();
        Calendar date1 = date.getCalendar();
        String[] timeString = s.split(":");
        int hour = Integer.parseInt(timeString[0]);
        int mins = Integer.parseInt(timeString[1].split(" ")[0]);
        int amPm;
        if (timeString[1].split(" ")[1].equalsIgnoreCase("AM")){
            amPm = Calendar.AM;
        } else {
            amPm = Calendar.PM;
        }
        if (hour == 12){
            hour = 0;
        }
        date1.set(Calendar.HOUR,hour);
        date1.set(Calendar.MINUTE, mins);
        date1.set(Calendar.AM_PM,amPm);
        date = new CalendarDate(date1);
        return date;
    }

    private void addError(GymClass gymClass, int errorNum){
        String msg;
        String msg2;
        if (errorNum == 0){
            msg = "Sorry, "+gymClass.getName()+" is full.";
            msg2 = "Class Full";
        } else {
            msg = "Sorry, "+gymClass.getRank().name() + " belt needed for this class";
            msg2 = "Not high enough Belt";
        }
        JOptionPane.showMessageDialog(null,msg,msg2,JOptionPane.INFORMATION_MESSAGE);
    }

    private enum Lists {

    }

    public enum Sorts {
        DATE(0),
        RANK(1),
        TRAINER(2),
        NAME(3);

        private int value;

        private Sorts(int i){
            value = i;
        }

        public int getValue(){
            return value;
        }
    }
}