/**
 * HistoryItem.java
 * Created on 07.03.2003, 18:31:39 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;

import main.java.memoranda.date.CalendarDate;

/**
 * 
 */
/*$Id: HistoryItem.java,v 1.4 2004/10/06 19:15:43 ivanrise Exp $*/
public class HistoryItem {
    
    private CalendarDate _date;
    private Project _project;
    /**
     * Constructor for HistoryItem.
     */
    public HistoryItem(CalendarDate date, Project project) {
        _date = date;
        _project = project;
    }

    /**
     * Constructor for HistoryItem based on Note passed in
     * @param note
     */
    public HistoryItem(Note note) {
        _date = note.getDate();
        _project = note.getProject();
    }

    /**
     * Method to return the current Date tied to the HistoryItem
     * @return CalendarDate
     */
    public CalendarDate getDate() {
       return _date;
    }

    /**
     * Method to return the current Project tied to the HistoryItem
     * @return Project
     */
    public Project getProject() {
       return _project;
    }

    /**
     * Method that compares HistoryItem
     * @param i
     * @return boolean
     */
    public boolean equals(HistoryItem i) {
       return i.getDate().equals(_date) && i.getProject().getID().equals(_project.getID());
    } 

}
