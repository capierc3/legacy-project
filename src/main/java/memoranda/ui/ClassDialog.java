package main.java.memoranda.ui;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.Trainer;
import main.java.memoranda.gym.User;
import main.java.memoranda.util.Local;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class ClassDialog extends JDialog implements WindowListener {
    public boolean CANCELLED = false;
    boolean ignoreStartChanged = false;
    boolean ignoreEndChanged = false;
    JPanel topPanel = new JPanel(new BorderLayout());
    JPanel bottomPanel = new JPanel(new BorderLayout());
    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JLabel header = new JLabel();
    JPanel eventPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc;
    JLabel lblStTime = new JLabel();
    JLabel lblEdTime = new JLabel();
    JSpinner timeSpin = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
    JSpinner endSpin;
    JLabel lblText = new JLabel();
    JTextField textField = new JTextField();
    JLabel beltText = new JLabel();
    JComboBox beltBox;
    JLabel sizeText = new JLabel("Class Size: ");
    JSpinner sizeSpin = new JSpinner(new SpinnerNumberModel(15, 1, 30, 1));
    JLabel trainLbl = new JLabel("Trainer: ");
    JComboBox trainerBox;

    JPanel repeatPanel = new JPanel(new GridBagLayout());
    JSpinner daySpin = new JSpinner(new SpinnerNumberModel(1, 1, 365, 1));
    JLabel lblSince = new JLabel();
    public JSpinner startDate = new JSpinner(new SpinnerDateModel());
    JButton setStartDateB = new JButton();
    JComboBox weekdaysCB = new JComboBox(Local.getWeekdayNames());
    JCheckBox enableEndDateCB = new JCheckBox();
    JCheckBox workingDaysOnlyCB = new JCheckBox();
    public JSpinner endDate = new JSpinner(new SpinnerDateModel());
    JButton setEndDateB = new JButton();
    JSpinner dayOfMonthSpin = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));

    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    JButton okB = new JButton();
    JButton cancelB = new JButton();
    CalendarFrame endCalFrame = new CalendarFrame();
    CalendarFrame startCalFrame = new CalendarFrame();
    private Date eventDate;
    JComboBox startTime;
    JComboBox endTime;

    public ClassDialog(Frame frame, String title) {
	super(frame, title, true);
	try {
	    jbInit();
	    pack();
	} catch (Exception ex) {
	    new ExceptionDialog(ex);
	}
	super.addWindowListener(this);
    }

    void jbInit() throws Exception {
	this.setResizable(false);
	// Build headerPanel
	headerPanel.setBackground(Color.WHITE);
	headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
	header.setFont(new java.awt.Font("Dialog", 0, 20));
	header.setForeground(new Color(0, 0, 124));
	header.setText(Local.getString("Create Class"));
	header.setIcon(new ImageIcon(ClassDialog.class.getResource("/ui/icons/events.png")));
	headerPanel.add(header);

	// Build eventPanel
	lblStTime.setText(Local.getString("Start Time"));
	lblStTime.setMinimumSize(new Dimension(60, 24));
	lblEdTime.setText(Local.getString("End Time"));
	lblEdTime.setMinimumSize(new Dimension(60, 24));
	gbc = new GridBagConstraints();
	gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.insets = new Insets(10, 10, 5, 10);
	gbc.anchor = GridBagConstraints.WEST;
	eventPanel.add(lblStTime, gbc);
	gbc = new GridBagConstraints();
	gbc.gridx = 2;
	gbc.gridy = 0;
	gbc.insets = new Insets(10, 10, 5, 10);
	gbc.anchor = GridBagConstraints.WEST;
	eventPanel.add(lblEdTime, gbc);
	String[] timeStrings = timeStrings();
	endTime = new JComboBox(timeStrings);
	endTime.setSelectedIndex((timeStrings.length / 2));
	startTime = new JComboBox(timeStrings);
	startTime.addActionListener(actionEvent -> {
	    int i = startTime.getSelectedIndex();
	    if ((i + 4) <= timeStrings.length - 1) {
		endTime.setSelectedIndex(i + 4);
	    } else {
		endTime.setSelectedIndex(timeStrings.length - 1);
	    }
	});
	startTime.setSelectedIndex((timeStrings.length / 2) - 4);
	gbc = new GridBagConstraints();
	gbc.gridx = 1;
	gbc.gridy = 0;
	gbc.insets = new Insets(10, 0, 5, 0);
	gbc.anchor = GridBagConstraints.WEST;
	eventPanel.add(startTime, gbc);
	endTime = new JComboBox(timeStrings);
	endTime.setSelectedIndex((timeStrings.length / 2));
	gbc = new GridBagConstraints();
	gbc.gridx = 3;
	gbc.gridy = 0;
	gbc.insets = new Insets(10, 0, 5, 0);
	gbc.anchor = GridBagConstraints.WEST;
	eventPanel.add(endTime, gbc);
	lblText.setText(Local.getString("Name"));
	lblText.setMinimumSize(new Dimension(120, 24));
	newGbc(0, 1, 6);
	eventPanel.add(lblText, gbc);
	textField.setMinimumSize(new Dimension(375, 24));
	textField.setPreferredSize(new Dimension(375, 24));
	newGbc(0, 2, 6);
	eventPanel.add(textField, gbc);
	newGbc(0, 3, 1);
	beltText.setText("Belt Rank");
	eventPanel.add(beltText, gbc);
	newGbc(1, 3, 1);
	String[] beltString = new String[Belt.values().length];
	int i = 0;
	for (Belt b : Belt.values()) {
	    beltString[i] = b.name();
	    i++;
	}
	beltBox = new JComboBox(beltString);
	beltBox.setSelectedIndex(0);
	eventPanel.add(beltBox, gbc);
	newGbc(2, 3, 1);
	eventPanel.add(sizeText, gbc);
	newGbc(3, 3, 1);
	eventPanel.add(sizeSpin, gbc);
	newGbc(0, 4, 6);
	eventPanel.add(trainLbl, gbc);
	newGbc(1, 4, 6);
	trainerBox = new JComboBox(getTrainerNames());
	eventPanel.add(trainerBox, gbc);

	// Build ButtonsPanel
	okB.setMaximumSize(new Dimension(100, 26));
	okB.setMinimumSize(new Dimension(100, 26));
	okB.setPreferredSize(new Dimension(100, 26));
	okB.setText(Local.getString("Ok"));
	okB.addActionListener(this::okB_actionPerformed);
	this.getRootPane().setDefaultButton(okB);
	cancelB.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		cancelB_actionPerformed(e);
	    }
	});
	cancelB.setText(Local.getString("Cancel"));
	cancelB.setPreferredSize(new Dimension(100, 26));
	cancelB.setMinimumSize(new Dimension(100, 26));
	cancelB.setMaximumSize(new Dimension(100, 26));
	buttonsPanel.add(okB);
	buttonsPanel.add(cancelB);

	// Finally build the Dialog
	topPanel.add(headerPanel, BorderLayout.NORTH);
	topPanel.add(eventPanel, BorderLayout.SOUTH);
	bottomPanel.add(repeatPanel, BorderLayout.NORTH);
	bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);
	this.getContentPane().add(topPanel, BorderLayout.NORTH);
	this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

	// Do final things...
	startCalFrame.cal.addSelectionListener(e -> {
	    if (ignoreStartChanged)
		return;
	    timeSpin.getModel().setValue(startCalFrame.cal.get().getCalendar().getTime());
	});
	endCalFrame.cal.addSelectionListener(e -> {
	    if (ignoreEndChanged)
		return;
	    endSpin.getModel().setValue(endCalFrame.cal.get().getCalendar().getTime());
	});
	disableElements();
	enableEndDateCB_actionPerformed(null);

    }

    private void newGbc(int x, int y, int width) {
	gbc = new GridBagConstraints();
	gbc.gridx = x;
	gbc.gridy = y;
	gbc.gridwidth = width;
	gbc.insets = new Insets(5, 10, 10, 10);
	gbc.anchor = GridBagConstraints.WEST;
	gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    private Vector<User> getTrainerNames() {
	Vector<User> trainers = new Vector<>();
	for (User user : App.appUsers.getAllUsers()) {
	    if (user instanceof Trainer) {
		trainers.add(user);
	    }
	}
	return trainers;
    }

    void disableElements() {
	dayOfMonthSpin.setEnabled(false);
	daySpin.setEnabled(false);
	weekdaysCB.setEnabled(false);
	startDate.setEnabled(false);
	setStartDateB.setEnabled(false);
	lblSince.setEnabled(false);
	endDate.setEnabled(false);
	setEndDateB.setEnabled(false);
	enableEndDateCB.setEnabled(false);
	enableEndDateCB.setSelected(false);
	workingDaysOnlyCB.setEnabled(false);
	workingDaysOnlyCB.setSelected(false);
    }

    void okB_actionPerformed(ActionEvent e) {
	this.dispose();
    }

    void cancelB_actionPerformed(ActionEvent e) {
	CANCELLED = true;
	this.dispose();
    }

    void setStartDateB_actionPerformed(ActionEvent e) {
	// startCalFrame.setLocation(setStartDateB.getLocation());
	startCalFrame.setSize(200, 190);
	startCalFrame.setTitle(Local.getString("Start date"));
	this.getLayeredPane().add(startCalFrame);
	startCalFrame.show();
    }

    void setEndDateB_actionPerformed(ActionEvent e) {
	// endCalFrame.setLocation(setEndDateB.getLocation());
	endCalFrame.setSize(200, 190);
	endCalFrame.setTitle(Local.getString("End date"));
	this.getLayeredPane().add(endCalFrame);
	endCalFrame.show();
    }

    public void enableEndDateCB_actionPerformed(ActionEvent e) {
	endDate.setEnabled(enableEndDateCB.isSelected());
	setEndDateB.setEnabled(enableEndDateCB.isSelected());
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
	CANCELLED = true;
	this.dispose();
    }

    public void setEventDate(Date d) {
	eventDate = d;
    }

    public Date getEventDate() {
	return eventDate;
    }

    public CalendarDate getEnd() {
	return new CalendarDate((Date) endSpin.getValue());
    }

    private String[] timeStrings() {
	String[] times = new String[96];
	int tNum = 0;
	String amPM = " AM";
	String hh;
	String min;
	for (int i = 0; i < 2; i++) {
	    for (int j = 0; j < 12; j++) {
		for (int k = 0; k < 4; k++) {
		    if (j == 11 && k == 0) {
			if (amPM.equalsIgnoreCase(" AM")) {
			    amPM = " PM";
			} else {
			    amPM = " AM";
			}
		    }
		    if (k == 0) {
			min = ":00";
		    } else if (k == 1) {
			min = ":15";
		    } else if (k == 2) {
			min = ":30";
		    } else {
			min = ":45";
		    }
		    hh = String.valueOf(j + 1);
		    times[tNum] = hh + min + amPM;
		    tNum++;
		}
	    }
	}
	return times;
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

}