/**
 * Project.java
 * Created on 11.02.2003, 16:11:47 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;

import main.java.memoranda.date.CalendarDate;

public interface Project {

    public static final int SCHEDULED = 0;

    public static final int ACTIVE = 1;

    public static final int COMPLETED = 2;

    public static final int FROZEN = 4;

    public static final int FAILED = 5;

    /**
     * Method to return Project Id
     * 
     * @return String
     */
    String getID();

    /**
     * Method to get the Project's startDate CalendarDate
     * 
     * @return CalendarDate
     */
    CalendarDate getStartDate();

    /**
     * Method to set the startDate on a project
     * 
     * @param date
     */
    void setStartDate(CalendarDate date);

    /**
     * Method to get the Project's endDate CalendarDate
     * 
     * @return CalendarDate
     */
    CalendarDate getEndDate();

    /**
     * Method to set an endDate on a Project
     * 
     * @param date
     */
    void setEndDate(CalendarDate date);

    /**
     * Method to return the Project's title
     * 
     * @return String
     */
    String getTitle();

    /**
     * Method to set the Title on a Project
     * 
     * @param title
     */
    void setTitle(String title);

    /**
     * Method to set the Description on a Project
     * 
     * @param description
     */
    void setDescription(String description);

    /**
     * Method to get the Description of a Project
     * 
     * @return String
     */
    String getDescription();

    /**
     * Method to get the Int status correlated to defined final ints declared above
     * 
     * @return
     */
    int getStatus();

    // int getProgress();

    // TaskList getTaskList();

    // NoteList getNoteList();

    // ResourcesList getResourcesList();

    /**
     * Method to freeze a project
     */
    void freeze();

    /**
     * Method to unfreeze a project
     */
    void unfreeze();

}
