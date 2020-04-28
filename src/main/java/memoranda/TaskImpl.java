package main.java.memoranda;

import java.util.Collection;
import java.util.Vector;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;

import java.util.Calendar;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;

/*$Id: TaskImpl.java,v 1.15 2005/12/01 08:12:26 alexeya Exp $*/
/**
 * Class that implements the Task interface.
 */
public class TaskImpl implements Task, Comparable {

    private Element _element = null;
    private TaskList _tl = null;

    /**
     * constructor for the implemented task
     * 
     * @param taskElement Element
     * @param tl          TaskList
     */
    public TaskImpl(Element taskElement, TaskList tl) {
	_element = taskElement;
	_tl = tl;
    }

    /**
     * getter for the main element of the class
     * 
     * @return Element
     */
    public Element getContent() {
	return _element;
    }

    /**
     * @see Task#getStartDate()
     */
    public CalendarDate getStartDate() {
	return new CalendarDate(_element.getAttribute("startDate").getValue());
    }

    /**
     * @see Task#setStartDate(CalendarDate)
     */
    public void setStartDate(CalendarDate date) {
	setAttr("startDate", date.toString());
    }

    /**
     * @see Task#getEndDate()
     */
    public CalendarDate getEndDate() {
	String ed = _element.getAttribute("endDate").getValue();
	if (!ed.equals(""))
	    return new CalendarDate(_element.getAttribute("endDate").getValue());
	Task parent = this.getParentTask();
	if (parent != null)
	    return parent.getEndDate();
	Project pr = this._tl.getProject();
	if (pr.getEndDate() != null)
	    return pr.getEndDate();
	return this.getStartDate();

    }

    /**
     * @see Task#setEndDate(CalendarDate)
     */
    public void setEndDate(CalendarDate date) {
	if (date == null)
	    setAttr("endDate", "");
	setAttr("endDate", date.toString());
    }

    /**
     * @see Task#getEffort()
     */
    public long getEffort() {
	Attribute attr = _element.getAttribute("effort");
	if (attr == null) {
	    return 0;
	} else {
	    try {
		return Long.parseLong(attr.getValue());
	    } catch (NumberFormatException e) {
		return 0;
	    }
	}
    }

    /**
     * @see Task#setEffort(long)
     */
    public void setEffort(long effort) {
	setAttr("effort", String.valueOf(effort));
    }

    /**
     * @see Task#getParentTask()
     */
    public Task getParentTask() {
	Node parentNode = _element.getParent();
	if (parentNode instanceof Element) {
	    Element parent = (Element) parentNode;
	    if (parent.getLocalName().equalsIgnoreCase("task"))
		return new TaskImpl(parent, _tl);
	}
	return null;
    }

    /**
     * @see Task#getParentId()
     */
    public String getParentId() {
	Task parent = this.getParentTask();
	if (parent != null)
	    return parent.getID();
	return null;
    }

    /**
     * @see Task#getDescription()
     */
    public String getDescription() {
	Element thisElement = _element.getFirstChildElement("description");
	if (thisElement == null) {
	    return null;
	} else {
	    return thisElement.getValue();
	}
    }

    /**
     * @see Task#setDescription(String)
     */
    public void setDescription(String s) {
	Element desc = _element.getFirstChildElement("description");
	if (desc == null) {
	    desc = new Element("description");
	    desc.appendChild(s);
	    _element.appendChild(desc);
	} else {
	    desc.removeChildren();
	    desc.appendChild(s);
	}
    }

    /**
     * @see Task#getStatus(CalendarDate)
     */
    public int getStatus(CalendarDate date) {
	CalendarDate start = getStartDate();
	CalendarDate end = getEndDate();
	if (isFrozen())
	    return Task.FROZEN;
	if (isCompleted())
	    return Task.COMPLETED;
	if (date.inPeriod(start, end)) {
	    if (date.equals(end))
		return Task.DEADLINE;
	    else
		return Task.ACTIVE;
	} else if (date.before(start)) {
	    return Task.SCHEDULED;
	}
	if (start.after(end)) {
	    return Task.ACTIVE;
	}
	return Task.FAILED;
    }

    /*
     * private boolean isDependsCompleted() { Vector v = (Vector) getDependsFrom();
     * boolean check = true; for (Enumeration en = v.elements();
     * en.hasMoreElements();) { Task t = (Task) en.nextElement(); if (t.getStatus()
     * != Task.COMPLETED) check = false; } return check; }
     */

    /**
     * returns if the task is frozen
     * 
     * @return boolean
     */
    private boolean isFrozen() {
	return _element.getAttribute("frozen") != null;
    }

    /**
     * checks to see if the progress is 100
     * 
     * @return boolean
     */
    private boolean isCompleted() {
	return getProgress() == 100;
    }

    /**
     * @see main.java.memoranda.Task#getID()
     */
    public String getID() {
	return _element.getAttribute("id").getValue();
    }

    /**
     * @see main.java.memoranda.Task#getText()
     */
    public String getText() {
	return _element.getFirstChildElement("text").getValue();
    }

    /**
     * ToString override
     * 
     * @return String
     */
    public String toString() {
	return getText();
    }

    /**
     * @see main.java.memoranda.Task#setText(String)
     */
    public void setText(String s) {
	_element.getFirstChildElement("text").removeChildren();
	_element.getFirstChildElement("text").appendChild(s);
    }

    /**
     * @see main.java.memoranda.Task#freeze()
     */
    public void freeze() {
	setAttr("frozen", "yes");
    }

    /**
     * @see main.java.memoranda.Task#unfreeze()
     */
    public void unfreeze() {
	if (this.isFrozen())
	    _element.removeAttribute(new Attribute("frozen", "yes"));
    }

    /**
     * @see main.java.memoranda.Task#getProgress()
     */
    public int getProgress() {
	return Integer.parseInt(_element.getAttribute("progress").getValue());
    }

    /**
     * @see main.java.memoranda.Task#setProgress(int)
     */
    public void setProgress(int p) {
	if ((p >= 0) && (p <= 100))
	    setAttr("progress", Integer.toString(p));
    }

    /**
     * @see main.java.memoranda.Task#getPriority()
     */
    public int getPriority() {
	Attribute pa = _element.getAttribute("priority");
	if (pa == null)
	    return Task.PRIORITY_NORMAL;
	return Integer.parseInt(pa.getValue());
    }

    /**
     * @see main.java.memoranda.Task#setPriority(int)
     */
    public void setPriority(int p) {
	setAttr("priority", String.valueOf(p));
    }

    /**
     * sets a attribute "String" to the Task with a value "String"
     * 
     * @param a     String
     * @param value String
     */
    private void setAttr(String a, String value) {
	Attribute attr = _element.getAttribute(a);
	if (attr == null)
	    _element.addAttribute(new Attribute(a, value));
	else
	    attr.setValue(value);
    }

    /**
     * A "Task rate" is an informal index of importance of the task considering
     * priority, number of days to deadline and current progress. rate =
     * (100-progress) / (numOfDays+1) * (priority+1)
     * 
     * @param d CalendarDate
     * @return long
     */
    private long calcTaskRate(CalendarDate d) {
	Calendar endDateCal = getEndDate().getCalendar();
	Calendar dateCal = d.getCalendar();
	int numOfDays = (endDateCal.get(Calendar.YEAR) * 365 + endDateCal.get(Calendar.DAY_OF_YEAR))
		- (dateCal.get(Calendar.YEAR) * 365 + dateCal.get(Calendar.DAY_OF_YEAR));
	if (numOfDays < 0)
	    return -1; // Something wrong ?
	return (100 - getProgress()) / (numOfDays + 1) * (getPriority() + 1);
    }

    /**
     * @see main.java.memoranda.Task#getRate()
     */
    public long getRate() {
	return -1 * calcTaskRate(CurrentDate.get());
    }

    /**
     * Comparable interface
     */
    public int compareTo(Object o) {
	Task task = (Task) o;
	return Long.compare(getRate(), task.getRate());
    }

    /**
     * Checks to see if another class is a copy of this task.
     * 
     * @param o Task
     * @return boolean
     */
    public boolean equals(Object o) {
	return ((o instanceof Task) && (((Task) o).getID().equals(this.getID())));
    }

    /**
     * @see Task#getSubTasks()
     */
    public Collection getSubTasks() {
	Elements subTasks = _element.getChildElements("task");
	return convertToTaskObjects(subTasks);
    }

    /**
     * converts tasks elements to a vector of Task objects
     * 
     * @param tasks Elements
     * @return Vector
     */
    private Collection convertToTaskObjects(Elements tasks) {
	Vector v = new Vector();
	for (int i = 0; i < tasks.size(); i++) {
	    Task t = new TaskImpl(tasks.get(i), _tl);
	    v.add(t);
	}
	return v;
    }

    /**
     * @see Task#getSubTask(java.lang.String)
     */
    public Task getSubTask(String id) {
	Elements subTasks = _element.getChildElements("task");
	for (int i = 0; i < subTasks.size(); i++) {
	    if (subTasks.get(i).getAttribute("id").getValue().equals(id))
		return new TaskImpl(subTasks.get(i), _tl);
	}
	return null;
    }

    /**
     * @see Task#hasSubTasks(String)
     */
    public boolean hasSubTasks(String id) {
	Elements subTasks = _element.getChildElements("task");
	for (int i = 0; i < subTasks.size(); i++)
	    if (subTasks.get(i).getAttribute("id").getValue().equals(id))
		return true;
	return false;
    }

    // Unused interface methods
    public Collection getDependsFrom() {
	Vector v = new Vector();
	Elements deps = _element.getChildElements("dependsFrom");
	for (int i = 0; i < deps.size(); i++) {
	    String id = deps.get(i).getAttribute("idRef").getValue();
	    Task t = _tl.getTask(id);
	    if (t != null)
		v.add(t);
	}
	return v;
    }

    public void addDependsFrom(Task task) {
	Element dep = new Element("dependsFrom");
	dep.addAttribute(new Attribute("idRef", task.getID()));
	_element.appendChild(dep);
    }

    public void removeDependsFrom(Task task) {
	Elements deps = _element.getChildElements("dependsFrom");
	for (int i = 0; i < deps.size(); i++) {
	    String id = deps.get(i).getAttribute("idRef").getValue();
	    if (id.equals(task.getID())) {
		_element.removeChild(deps.get(i));
		return;
	    }
	}
    }

}
