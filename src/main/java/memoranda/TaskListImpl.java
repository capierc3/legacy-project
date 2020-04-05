package main.java.memoranda;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;

/*$Id: TaskListImpl.java,v 1.14 2006/07/03 11:59:19 alexeya Exp $*/
/**
 * Class that implements the TaskList class
 */
public class TaskListImpl implements TaskList {

    private Project _project = null;
    private Document _doc = null;
    private Element _root = null;
	
	/**
	 * Hastable of "task" XOM elements for quick searching them by ID's
	 * (ID => element) 
	 */
	private Hashtable elements = new Hashtable();

    /**
     * Constructor for the TaskListImpl with an existing document
     * @param doc Document
     * @param prj Project
     */
    public TaskListImpl(Document doc, Project prj) {
        _doc = doc;
        _root = _doc.getRootElement();
        _project = prj;
		buildElements(_root);
    }

    /**
     * Constructor for the TaskListImpl without an existing document
     * @param prj Project
     */
    public TaskListImpl(Project prj) {            
            _root = new Element("tasklist");
            _doc = new Document(_root);
            _project = prj;
    }

    /**
     * @see TaskList#getProject()
     */
	public Project getProject() {
		return _project;
	}
		
	/**
	 * Build the hashtable recursively
	 */
	private void buildElements(Element parent) {
		Elements els = parent.getChildElements("task");
		for (int i = 0; i < els.size(); i++) {
			Element el = els.get(i);
			elements.put(el.getAttribute("id").getValue(), el);
			buildElements(el);
		}
	}
	
    /**
     * All methods to obtain list of tasks are consolidated under getAllSubTasks and getActiveSubTasks.
     * If a root task is required, just send a null taskId
     */
    public Collection getAllSubTasks(String taskId) {
    	if ((taskId == null) || (taskId.length() == 0)) {
    		return getAllRootTasks();
    	}
    	else {
            Element task = getTaskElement(taskId);
            if (task == null)
                return new Vector();
            Elements subTasks = task.getChildElements("task");
            return convertToTaskObjects(subTasks);    	    		
    	}
    }

    /**
     * gets only the root tasks
     * @return Vector
     */
    public Collection getTopLevelTasks() {
        return getAllRootTasks();
    }

    /**
     * gets all active subtasks of a inputted task
     * @param taskId String
     * @param date CalendarDate
     * @return Collection
     */
    public Collection getActiveSubTasks(String taskId,CalendarDate date) {
        Collection allTasks = getAllSubTasks(taskId);        
        return filterActiveTasks(allTasks,date);
    }

    /**
     * creates a Task object with inputted information, adds it to the list of elements and returns it.
     * @param startDate CalendarDate
     * @param endDate CalendarDate
     * @param text String
     * @param priority int
     * @param effort int
     * @param description String
     * @param parentTaskId String
     * @return Task
     */
    public Task createTask(CalendarDate startDate, CalendarDate endDate, String text, int priority, long effort, String description, String parentTaskId) {
        Element el = new Element("task");
        el.addAttribute(new Attribute("startDate", startDate.toString()));
        el.addAttribute(new Attribute("endDate", endDate != null? endDate.toString():""));
		String id = Util.generateId();
        el.addAttribute(new Attribute("id", id));
        el.addAttribute(new Attribute("progress", "0"));
        el.addAttribute(new Attribute("effort", String.valueOf(effort)));
        el.addAttribute(new Attribute("priority", String.valueOf(priority)));
                
        Element txt = new Element("text");
        txt.appendChild(text);
        el.appendChild(txt);

        Element desc = new Element("description");
        desc.appendChild(description);
        el.appendChild(desc);

        if (parentTaskId == null) {
            _root.appendChild(el);
        }
        else {
            Element parent = getTaskElement(parentTaskId);
            parent.appendChild(el);
        }

		elements.put(id, el);
		
        Util.debug("Created task with parent " + parentTaskId);
        
        return new TaskImpl(el, this);
    }

    /**
     * removes a task for the list
     * @param task Task
     */
    public void removeTask(Task task) {
        String parentTaskId = task.getParentId();
        if (parentTaskId == null) {
            _root.removeChild(task.getContent());            
        }
        else {
            Element parentNode = getTaskElement(parentTaskId);
            parentNode.removeChild(task.getContent());
        }
		elements.remove(task.getID());
    }

    /**
     * checks to see if a task has any subtasks.
     * @param id String
     * @return boolean
     */
    public boolean hasSubTasks(String id) {
        Element task = getTaskElement(id);
        if (task == null) return false;
        return task.getChildElements("task").size() > 0;
    }

    /**
     * Returns a task object for the inputted id
     * @param id String
     * @return Task
     */
    public Task getTask(String id) {
        Util.debug("Getting task " + id);          
        return new TaskImpl(getTaskElement(id), this);          
    }

    /**
     * returns true if the inputted ID has a parent task
     * @param id String
     * @return boolean
     */
    public boolean hasParentTask(String id) {
    	Element t = getTaskElement(id);
    	
    	Node parentNode = t.getParent();
    	if (parentNode instanceof Element) {
    	    Element parent = (Element) parentNode;
            return parent.getLocalName().equalsIgnoreCase("task");
    	}
    	else {
    	    return false;
    	}
    }

    /**
     * @see main.java.memoranda.TaskList#getXMLContent()
     */	 
    public Document getXMLContent() {
        return _doc;
    }
    
    /**
     * Recursively calculate total effort based on subtasks for every node in the task tree
     * The values are saved as they are calculated as well
     * 
     * @param t Task
     * @return long
     */
    public long calculateTotalEffortFromSubTasks(Task t) {
        long totalEffort = 0;
        if (hasSubTasks(t.getID())) {
            Collection subTasks = getAllSubTasks(t.getID());
            for (Object subTask : subTasks) {
                Task e = (Task) subTask;
                totalEffort = totalEffort + calculateTotalEffortFromSubTasks(e);
            }
            t.setEffort(totalEffort);
            return totalEffort;            
        }
        else {
            return t.getEffort();
        }
    }

    /**
     * returns the earliest start date of a subtasks in a task.
     * @param t Task
     * @return CalendarDate
     */
    public CalendarDate getEarliestStartDateFromSubTasks(Task t) {
        CalendarDate d = t.getStartDate();
        if (hasSubTasks(t.getID())) {
	        Collection subTasks = getAllSubTasks(t.getID());
            for (Object subTask : subTasks) {
                Task e = (Task) subTask;
                CalendarDate dd = getEarliestStartDateFromSubTasks(e);
                if (dd.before(d)) {
                    d = dd;
                }
            }
	        t.setStartDate(d);
	        return d;
        }
        else {
            return t.getStartDate();
        }
    }

    /**
     * returns the latest end date of sub tasks in a given task
     * @param t Task
     * @return CalendarDate
     */
    public CalendarDate getLatestEndDateFromSubTasks(Task t) {
        CalendarDate d = t.getEndDate();
        if (hasSubTasks(t.getID())) {
	        Collection subTasks = getAllSubTasks(t.getID());
            for (Object subTask : subTasks) {
                Task e = (Task) subTask;
                CalendarDate dd = getLatestEndDateFromSubTasks(e);
                if (dd.after(d)) {
                    d = dd;
                }
            }
	        t.setEndDate(d);
	        return d;
        }
        else {
            return t.getEndDate();
        }
    }
    
    /**
     * recursively finds the amount of completion from all the subtasks in a task.
     * @param t Task
     * @return long[] of size 2. First long is expended effort in milliseconds, 2nd long is total effort in milliseconds
     */
    public long[] calculateCompletionFromSubTasks(Task t) {
//        Util.debug("Task " + t.getText());
        
        long[] res = new long[2];
        long expendedEffort = 0; // milliseconds
        long totalEffort = 0; // milliseconds
        if (hasSubTasks(t.getID())) {
            Collection subTasks = getAllSubTasks(t.getID());
            for (Object subTask : subTasks) {
                Task e = (Task) subTask;
                long[] subTaskCompletion = calculateCompletionFromSubTasks(e);
                expendedEffort = expendedEffort + subTaskCompletion[0];
                totalEffort = totalEffort + subTaskCompletion[1];
            }
            
            int thisProgress = (int) Math.round((((double)expendedEffort / (double)totalEffort) * 100));
            t.setProgress(thisProgress);

//            Util.debug("Expended Effort: "+ expendedEffort);
//            Util.debug("Total Effort: "+ totalEffort);
//            Util.debug("Progress: "+ t.getProgress());

            res[0] = expendedEffort;
            res[1] = totalEffort;
            return res;            
        }
        else {
            long eff = t.getEffort();
            // if effort was not filled in, it is assumed to be "1 hr" for the purpose of calculation
            if (eff == 0) {
                eff = 1;
            }
            res[0] = Math.round((double)(t.getProgress()* eff) / 100d); 
            res[1] = eff;
            return res;
        }
    }

    /**
     * Getter for an element in the list of elements by ID
     * @param id String
     * @return Element
     */
    private Element getTaskElement(String id) {

		Element el = (Element)elements.get(id);
		if (el == null) {
			Util.debug("Task " + id + " cannot be found in project " + _project.getTitle());
		}
		return el;
    }

    /**
     * gets all the task from the _root element.
     * @return Collection
     */
    private Collection getAllRootTasks() {
        Elements tasks = _root.getChildElements("task");
        return convertToTaskObjects(tasks);    	    		
    }

    /**
     * converts an Elements object to a vector of Task objects
     * @param tasks Elements
     * @return Vector
     */
    private Collection convertToTaskObjects(Elements tasks) {
        Vector v = new Vector();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = new TaskImpl(tasks.get(i), this);
            v.add(t);
        }
        return v;
    }

    /**
     * Adds every active task in an inputted collection and adds it to a Vector.
     * @param tasks Collection
     * @param date CalendarDate
     * @return Vector
     */
    private Collection filterActiveTasks(Collection tasks,CalendarDate date) {
        Vector v = new Vector();
        for (Object task : tasks) {
            Task t = (Task) task;
            if (isActive(t, date)) {
                v.add(t);
            }
        }
        return v;
    }

    /**
     * Returns true if task is active.
     * @param t Task
     * @param date CalendarDate
     * @return boolean
     */
    private boolean isActive(Task t,CalendarDate date) {
    	if ((t.getStatus(date) == Task.ACTIVE) || (t.getStatus(date) == Task.DEADLINE) || (t.getStatus(date) == Task.FAILED)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /*
     * deprecated methods below
     * 
     */
                    
//    public void adjustParentTasks(Task t) {
//    	if ((t.getParent() == null) || (t.getParent().equals(""))){
//    		return;
//    	}
//    	else {
//    		Task p = getTask(t.getParent());
//    		
//    		long totalEffort = calculateTotalEffortFromSubTasks(p);
//    		
//    		if(totalEffort > p.getEffort()) {
//    			p.setEffort(totalEffort);
//    		}
//    		if(t.getStartDate().before(p.getStartDate())) {
//    			p.setStartDate(t.getStartDate());
//    		}
//    		if(t.getEndDate().after(p.getEndDate())) {
//    			p.setEndDate(t.getEndDate());
//    		}
//    		
//        	if (!((p.getParent() == null) || (p.getParent().equals("")))){
//        		// still has parent, go up the tree
//        		adjustParentTasks(p);
//        	}    		
//    	}
//    }
}
