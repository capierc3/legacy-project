package main.java.memoranda;
import java.util.Collection;

import main.java.memoranda.date.CalendarDate;
/*$Id: TaskList.java,v 1.8 2005/12/01 08:12:26 alexeya Exp $*/
/**
 * Interface to be used for the TaskList
 */
public interface TaskList {

    /**
     * getter that returns the task's project
     * @return Project
     */
	Project getProject();

    /**
     * getter that returns a Task biased on an inputted ID
     * @param id String
     * @return Task
     */
    Task getTask(String id);

    /**
     * creates a new task object with inputted values
     * @param startDate CalendarDate
     * @param endDate CalendarDate
     * @param text String
     * @param priority int
     * @param effort int
     * @param description String
     * @param parentTaskId String
     * @return Task
     */
    Task createTask(CalendarDate startDate, CalendarDate endDate, String text, int priority, long effort, String description, String parentTaskId);

    /**
     * removes a task from the list
     * @param task
     */
    void removeTask(Task task);

    /**
     * boolean to show if there is subtasks within a task
     * @param id String
     * @return boolean
     */
    boolean hasSubTasks(String id);

    /**
     * boolean to show if a task is a subtask of a parent
     * @param id String
     * @return boolean
     */
	boolean hasParentTask(String id);

	Collection getTopLevelTasks();
	
    Collection getAllSubTasks(String taskId);
    Collection getActiveSubTasks(String taskId,CalendarDate date);
    

    long calculateTotalEffortFromSubTasks(Task t);
    CalendarDate getLatestEndDateFromSubTasks(Task t);
    CalendarDate getEarliestStartDateFromSubTasks(Task t);
    long[] calculateCompletionFromSubTasks(Task t);

    nu.xom.Document getXMLContent();

//    public void adjustParentTasks(Task t);
}
