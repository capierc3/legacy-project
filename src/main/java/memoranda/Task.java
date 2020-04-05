
package main.java.memoranda;

import java.util.Collection;

import main.java.memoranda.date.CalendarDate;

/*$Id: Task.java,v 1.9 2005/06/16 04:21:32 alexeya Exp $*/
/**
 *Interface to be used for task objects
 */
public interface Task {
    
    public static final int SCHEDULED = 0;

    public static final int ACTIVE = 1;

    public static final int COMPLETED = 2;

    public static final int FROZEN = 4;

    public static final int FAILED = 5;
    
    public static final int LOCKED = 6;
    
    public static final int DEADLINE = 7;
    
    public static final int PRIORITY_LOWEST = 0;
    
    public static final int PRIORITY_LOW = 1;
    
    public static final int PRIORITY_NORMAL = 2;
    
    public static final int PRIORITY_HIGH = 3;
    
    public static final int PRIORITY_HIGHEST = 4;

    /**
     * getter for the start date of the task
     * @return CalendarDate
     */
    CalendarDate getStartDate();
    /**
     * setter for the start date of the task
     * @param date CalendarDate
     */
    void setStartDate(CalendarDate date);

    /**
     * getter for the end date of the task
     * @return CalendarDate
     */
    CalendarDate getEndDate();

    /**
     * setter for the start date of the task
     * @param date CalendarDate
     */
    void setEndDate(CalendarDate date);

    /**
     * returns the status of the task based on a date
     * @param date calendarDate
     * @return int
     */
    int getStatus(CalendarDate date);

    /**
     * getter for the progress of the task
     * @return int
     */
    int getProgress();

    /**
     * setter for the progress of the task
     * @param p int
     */
    void setProgress(int p);

    /**
     * getter for the priority of the task
     * @return int
     */
    int getPriority();

    /**
     * setter for the priority of the task
     * @param p int
     */
    void setPriority(int p);

    /**
     * getter for the task ID
     * @return String
     */
    String getID();

    /**
     * getter for the text of the task
     * @return String
     */
    String getText();

    /**
     * Setter for the text
     * @param s String
     */
    void setText(String s);

    /*Collection getDependsFrom();
    void addDependsFrom(Task task);
    void removeDependsFrom(Task task);*/

    /**
     * getter for the sub tasks collection
     * @return Collection
     */
    Collection getSubTasks();

    /**
     * getter for a subtask of the task
     * @param id String
     * @return Task
     */
    Task getSubTask(String id);

    /**
     * returns true if there are any subtasks to the task
     * @param id String
     * @return boolean
     */
    boolean hasSubTasks(String id);

    /**
     * setter for the task effort
     * @param effort long
     */
    void setEffort(long effort);

    /**
     * getter for the task effort
     * @return long
     */
    long getEffort();

    /**
     * setter for the description of the task
     * @param description String
     */
    void setDescription(String description);

    /**
     * getter for the description of the task
     * @return String
     */
    String getDescription();

    /**
     * getter for the parent task
     * @return Task
     */
    Task getParentTask();

    /**
     * getter for the parent ID
     * @return String
     */
    String getParentId();

    /**
     * sets status to frozen
     */
    void freeze();

    /**
     * removes frozen status
     */
    void unfreeze();

    /**
     * gets the current task rate of the task
     * @return long
     */
	long getRate();

    /**
     * returns the element used
     * @return Element
     */
    nu.xom.Element getContent();
}
