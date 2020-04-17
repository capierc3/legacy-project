package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import main.java.memoranda.EventsManager;
import main.java.memoranda.EventsScheduler;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.util.Configuration;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.Util;

/**
 * This is the main class for the MySchedule display in the ui.
 * @author Chase
 *
 */
public class MySchedulePanel extends JPanel {
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
    EventsTable eventsTable = new EventsTable();
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
    boolean isOwner = true;
    boolean isStudent = false;
    boolean isTrainer = false;


    public MySchedulePanel(DailyItemsPanel _parentPanel) {

        try {
            parentPanel = _parentPanel;
            jbInit();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }
    void jbInit() throws Exception {
        eventsToolBar.setFloatable(false);

//        historyBackB.setAction(History.historyBackAction);
//        historyBackB.setFocusable(false);
//        historyBackB.setBorderPainted(false);
//        historyBackB.setToolTipText(Local.getString("History back"));
//        historyBackB.setRequestFocusEnabled(false);
//        historyBackB.setPreferredSize(new Dimension(24, 24));
//        historyBackB.setMinimumSize(new Dimension(24, 24));
//        historyBackB.setMaximumSize(new Dimension(24, 24));
//        historyBackB.setText("");
//
//        historyForwardB.setAction(History.historyForwardAction);
//        historyForwardB.setBorderPainted(false);
//        historyForwardB.setFocusable(false);
//        historyForwardB.setPreferredSize(new Dimension(24, 24));
//        historyForwardB.setRequestFocusEnabled(false);
//        historyForwardB.setToolTipText(Local.getString("History forward"));
//        historyForwardB.setMinimumSize(new Dimension(24, 24));
//        historyForwardB.setMaximumSize(new Dimension(24, 24));
//        historyForwardB.setText("");


        //Set toolbar buttons
        if (isOwner) {
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
            editClassB.addActionListener(this::editEventB_actionPerformed);
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

        } else if (isStudent) {

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
        eventsTable.setMaximumSize(new Dimension(32767, 32767));
        eventsTable.setRowHeight(24);
        classPPMenu.setFont(new java.awt.Font("Dialog", 1, 10));
        //sets right click menu
        if (isOwner) {
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
        } else if (isStudent){
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
        scrollPane.getViewport().add(eventsTable, null);
        this.add(scrollPane, BorderLayout.CENTER);
        //eventsToolBar.add(historyBackB, null);
        //eventsToolBar.add(historyForwardB, null);
        eventsToolBar.addSeparator(new Dimension(8, 24));
        //fills the toolbar
        if (isOwner) {
            eventsToolBar.add(newClassB, null);
            eventsToolBar.add(removeClassB, null);
            eventsToolBar.addSeparator(new Dimension(8, 24));
            eventsToolBar.add(editClassB, null);
        } else if (isStudent){
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
        eventsTable.addMouseListener(ppListener);

        CurrentDate.addDateListener(d -> {
            eventsTable.initTable(d);
            boolean enbl = d.after(CalendarDate.today()) || d.equals(CalendarDate.today());
            if (isOwner) {
                newClassB.setEnabled(enbl);
                ppNewClass.setEnabled(enbl);
                editClassB.setEnabled(false);
                ppEditClass.setEnabled(false);
                removeClassB.setEnabled(false);
                ppRemoveClass.setEnabled(false);
            } else if (isStudent){
                addClassB.setEnabled(enbl);
                ppAddClass.setEnabled(enbl);
                dropClassB.setEnabled(enbl);
                ppDropClass.setEnabled(enbl);
            }
        });
        eventsTable.getSelectionModel().addListSelectionListener(e -> {
            boolean enbl = eventsTable.getSelectedRow() > -1;
            if (isOwner) {
                editClassB.setEnabled(enbl);
                ppEditClass.setEnabled(enbl);
                removeClassB.setEnabled(enbl);
                ppRemoveClass.setEnabled(enbl);
            } else if (isStudent){
                addClassB.setEnabled(enbl);
                ppAddClass.setEnabled(enbl);
                dropClassB.setEnabled(enbl);
                ppDropClass.setEnabled(enbl);
            }
        });
        editClassB.setEnabled(false);
        removeClassB.setEnabled(false);
        //fills the right click menu
        if (isOwner) {
            classPPMenu.add(ppEditClass);
            classPPMenu.addSeparator();
            classPPMenu.add(ppNewClass);
            classPPMenu.add(ppRemoveClass);
        } else if (isStudent){
            classPPMenu.add(ppAddClass);
            classPPMenu.add(ppDropClass);
        }
		
		// remove events using the DEL key
//		eventsTable.addKeyListener(new KeyListener() {
//			public void keyPressed(KeyEvent e){
//				if(eventsTable.getSelectedRows().length>0
//					&& e.getKeyCode()==KeyEvent.VK_DELETE)
//					ppRemoveEvent_actionPerformed(null);
//			}
//			public void	keyReleased(KeyEvent e){}
//			public void keyTyped(KeyEvent e){}
//		});
    }

    private void editEventB_actionPerformed(ActionEvent e) {
        EventDialog dlg = new EventDialog(App.getFrame(), Local.getString("Event"));
        main.java.memoranda.Event ev =
            (main.java.memoranda.Event) eventsTable.getModel().getValueAt(
                eventsTable.getSelectedRow(),
                EventsTable.EVENT);
        
        dlg.timeSpin.getModel().setValue(ev.getTime());
        /*if (new CalendarDate(ev.getTime()).equals(CalendarDate.today()))
            ((SpinnerDateModel)dlg.timeSpin.getModel()).setStart(new Date());
        else
        ((SpinnerDateModel)dlg.timeSpin.getModel()).setStart(CalendarDate.today().getDate());
        ((SpinnerDateModel)dlg.timeSpin.getModel()).setEnd(CalendarDate.tomorrow().getDate());*/    
        dlg.textField.setText(ev.getText());
        int rep = ev.getRepeat();
        if (rep > 0) {
            dlg.startDate.getModel().setValue(ev.getStartDate().getDate());
            if (rep == EventsManager.REPEAT_DAILY) {
                dlg.dailyRepeatRB.setSelected(true);
                dlg.dailyRepeatRB_actionPerformed(null);
                dlg.daySpin.setValue(ev.getPeriod());
            }
            else if (rep == EventsManager.REPEAT_WEEKLY) {
                dlg.weeklyRepeatRB.setSelected(true);
                dlg.weeklyRepeatRB_actionPerformed(null);
		int d = ev.getPeriod() - 1;
		if(Configuration.get("FIRST_DAY_OF_WEEK").equals("mon")) {
		    d--;
		    if(d<0) d=6;
		}
                dlg.weekdaysCB.setSelectedIndex(d);
            }
            else if (rep == EventsManager.REPEAT_MONTHLY) {
                dlg.monthlyRepeatRB.setSelected(true);
                dlg.monthlyRepeatRB_actionPerformed(null);
                dlg.dayOfMonthSpin.setValue(ev.getPeriod());
            }
	    else if (rep == EventsManager.REPEAT_YEARLY) {
		dlg.yearlyRepeatRB.setSelected(true);
		dlg.yearlyRepeatRB_actionPerformed(null);
		dlg.dayOfMonthSpin.setValue(new Integer(ev.getPeriod()));
	    }
        if (ev.getEndDate() != null) {
           dlg.endDate.getModel().setValue(ev.getEndDate().getDate());
           dlg.enableEndDateCB.setSelected(true);
           dlg.enableEndDateCB_actionPerformed(null);
        }
		if(ev.getWorkingDays()) {
			dlg.workingDaysOnlyCB.setSelected(true);
		}
		
        }

        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        EventsManager.removeEvent(ev);
        
		Calendar calendar = new GregorianCalendar(Local.getCurrentLocale()); //Fix deprecated methods to get hours
		//by (jcscoobyrs) 14-Nov-2003 at 10:24:38 AM
		calendar.setTime(((Date)dlg.timeSpin.getModel().getValue()));//Fix deprecated methods to get hours
		//by (jcscoobyrs) 14-Nov-2003 at 10:24:38 AM
		int hh = calendar.get(Calendar.HOUR_OF_DAY);//Fix deprecated methods to get hours
		//by (jcscoobyrs) 14-Nov-2003 at 10:24:38 AM
		int mm = calendar.get(Calendar.MINUTE);//Fix deprecated methods to get hours
		//by (jcscoobyrs) 14-Nov-2003 at 10:24:38 AM
        
        //int hh = ((Date) dlg.timeSpin.getModel().getValue()).getHours();
        //int mm = ((Date) dlg.timeSpin.getModel().getValue()).getMinutes();
        String text = dlg.textField.getText();
        if (dlg.noRepeatRB.isSelected())
   	    EventsManager.createEvent(CurrentDate.get(), hh, mm, text);
        else {
	    updateEvents(dlg,hh,mm,text);
	}    
	saveEvents();
    }

    private void newEventB_actionPerformed(ActionEvent e) {
        Calendar cdate = CurrentDate.get().getCalendar();
        // round down to hour
        cdate.set(Calendar.MINUTE,0);  
        Util.debug("Default time is " + cdate);
        
    	newEventB_actionPerformed(e, null, cdate.getTime(), cdate.getTime());
    }
    //TODO add button click action
    private void addClassB_actionPerformed(ActionEvent e) {

    }
    //TODO add button click action
    private void dropClassB_actionPerformed(ActionEvent e) {

    }
    //TODO add sort actions
    private void sortBox_actionPerformed(ActionEvent e) {

    }
    //TODO add sort dir logic
    private void sortDirB_actionPerformed(ActionEvent e) {
        if (sortDirB.getToolTipText().equalsIgnoreCase("Descending")) {
            sortDirB.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Down.png")));
            sortDirB.setToolTipText(Local.getString("Ascending"));
        } else {
            sortDirB.setIcon(
                    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Up.png")));
            sortDirB.setToolTipText(Local.getString("Descending"));
        }
    }
    //TODO add logic
    private void listBox_actionPerformed(ActionEvent e) {

    }
    
    void newEventB_actionPerformed(ActionEvent e, String tasktext, Date startDate, Date endDate) {
    	EventDialog dlg = new EventDialog(App.getFrame(), Local.getString("Create  Class"));
    	Dimension frmSize = App.getFrame().getSize();
    	Point loc = App.getFrame().getLocation();
    	if (tasktext != null) {
    		dlg.textField.setText(tasktext);
    	}
		dlg.startDate.getModel().setValue(startDate);
		dlg.endDate.getModel().setValue(endDate);
		dlg.timeSpin.getModel().setValue(startDate);

    	dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
    	dlg.setEventDate(startDate);
		dlg.setVisible(true);
    	if (dlg.CANCELLED)
    		return;
    	Calendar calendar = new GregorianCalendar(Local.getCurrentLocale()); //Fix deprecated methods to get hours
    	//by (jcscoobyrs) 14-Nov-2003 at 10:24:38 AM
    	calendar.setTime(((Date)dlg.timeSpin.getModel().getValue()));//Fix deprecated methods to get hours
    	//by (jcscoobyrs) 14-Nov-2003 at 10:24:38 AM
    	int hh = calendar.get(Calendar.HOUR_OF_DAY);//Fix deprecated methods to get hours
    	//by (jcscoobyrs) 14-Nov-2003 at 10:24:38 AM
    	int mm = calendar.get(Calendar.MINUTE);//Fix deprecated methods to get hours
    	//by (jcscoobyrs) 14-Nov-2003 at 10:24:38 AM

    	//int hh = ((Date) dlg.timeSpin.getModel().getValue()).getHours();
    	//int mm = ((Date) dlg.timeSpin.getModel().getValue()).getMinutes();
    	String text = dlg.textField.getText();

		CalendarDate eventCalendarDate = new CalendarDate(dlg.getEventDate());
		
    	if (dlg.noRepeatRB.isSelected())
    		EventsManager.createEvent(eventCalendarDate, hh, mm, text);
    	else {
    		updateEvents(dlg,hh,mm,text);
    	}
    	saveEvents();
    }

    private void saveEvents() {
	CurrentStorage.get().storeEventsManager();
        eventsTable.refresh();
        EventsScheduler.init();
        parentPanel.calendar.jnCalendar.updateUI();
        parentPanel.updateIndicators();
    }

    private void updateEvents(EventDialog dlg, int hh, int mm, String text) {
	int rtype;
        int period;
        CalendarDate sd = new CalendarDate((Date) dlg.startDate.getModel().getValue());
        CalendarDate ed = null;
        if (dlg.enableEndDateCB.isSelected())
            ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
        if (dlg.dailyRepeatRB.isSelected()) {
            rtype = EventsManager.REPEAT_DAILY;
            period = (Integer) dlg.daySpin.getModel().getValue();
        }
        else if (dlg.weeklyRepeatRB.isSelected()) {
            rtype = EventsManager.REPEAT_WEEKLY;
            period = dlg.weekdaysCB.getSelectedIndex() + 1;
	    if (Configuration.get("FIRST_DAY_OF_WEEK").equals("mon")) {
		if(period==7) period=1;
		else period++;
	    }
        }
	else if (dlg.yearlyRepeatRB.isSelected()) {
	    rtype = EventsManager.REPEAT_YEARLY;
	    period = sd.getCalendar().get(Calendar.DAY_OF_YEAR);
	    if((sd.getYear() % 4) == 0 && sd.getCalendar().get(Calendar.DAY_OF_YEAR) > 60) period--;
	}
        else {
            rtype = EventsManager.REPEAT_MONTHLY;
            period = (Integer) dlg.dayOfMonthSpin.getModel().getValue();
        }
        EventsManager.createRepeatableEvent(rtype, sd, ed, period, hh, mm, text, dlg.workingDaysOnlyCB.isSelected());
    }

    private void removeEventB_actionPerformed(ActionEvent e) {
		String msg;
		main.java.memoranda.Event ev;

		if(eventsTable.getSelectedRows().length > 1) 
			msg = Local.getString("Remove") + " " + eventsTable.getSelectedRows().length 
				+ " " + Local.getString("events") + "\n" + Local.getString("Are you sure?");
		else {
			ev = (main.java.memoranda.Event) eventsTable.getModel().getValueAt(
                eventsTable.getSelectedRow(),
                EventsTable.EVENT);
			msg = Local.getString("Remove Class") + "\n'"
				+ ev.getText() + "'\n" + Local.getString("Are you sure?");
		}

        int n =
            JOptionPane.showConfirmDialog(
                App.getFrame(),
                msg,
                Local.getString("Remove Class"),
                JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION) return;

        for(int i=0; i< eventsTable.getSelectedRows().length;i++) {
			ev = (main.java.memoranda.Event) eventsTable.getModel().getValueAt(
                  eventsTable.getSelectedRows()[i], EventsTable.EVENT);
        EventsManager.removeEvent(ev);
		}
        eventsTable.getSelectionModel().clearSelection();
/*        CurrentStorage.get().storeEventsManager();
        eventsTable.refresh();
        EventsScheduler.init();
        parentPanel.calendar.jnCalendar.updateUI();
        parentPanel.updateIndicators();
*/ saveEvents();  
  }

    class PopupListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if ((e.getClickCount() == 2) && (eventsTable.getSelectedRow() > -1))
                editEventB_actionPerformed(null);
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
        editEventB_actionPerformed(e);
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