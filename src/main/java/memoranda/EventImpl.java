package main.java.memoranda;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.util.Local;
import nu.xom.Attribute;
import nu.xom.Element;

/*$Id: EventImpl.java,v 1.9 2004/10/06 16:00:11 ivanrise Exp $*/
/**
 * Implementation of the Event interface
 */
public class EventImpl implements Event, Comparable {
    
    private Element _elem = null;

    /**
     * Constructor for EventImpl.
     */
    public EventImpl(Element elem) {
        _elem = elem;
    }

    /**
     * @see main.java.memoranda.Event#getHour()
     */
    public int getHour() {
        return Integer.parseInt(_elem.getAttribute("hour").getValue());
    }

    /**
     * @see main.java.memoranda.Event#getMinute()
     */
    public int getMinute() {
        return Integer.parseInt(_elem.getAttribute("min").getValue());
    }

    /**
     * gets the time string for the time of the event
     * @return String
     */
    public String getTimeString() {
        return Local.getTimeString(getHour(), getMinute());
    }

    /**
     * @see main.java.memoranda.Event#getText()
     */
    public String getText() {
        return _elem.getValue();
    }

    /**
     * @see main.java.memoranda.Event#getContent()
     */
    public Element getContent() {
        return _elem;
    }

    /**
     * @see main.java.memoranda.Event#isRepeatable()
     */
    public boolean isRepeatable() {
        return getStartDate() != null;
    }

    /**
     * @see main.java.memoranda.Event#getStartDate()
     */
    public CalendarDate getStartDate() {
        Attribute a = _elem.getAttribute("startDate");
        if (a != null) return new CalendarDate(a.getValue());
        return null;
    }

    /**
     * @see main.java.memoranda.Event#getEndDate()
     */
    public CalendarDate getEndDate() {
        Attribute a = _elem.getAttribute("endDate");
        if (a != null) return new CalendarDate(a.getValue());
        return null;
    }

    /**
     * @see main.java.memoranda.Event#getPeriod()
     */
    public int getPeriod() {
        Attribute a = _elem.getAttribute("period");
        if (a != null) return Integer.parseInt(a.getValue());
        return 0;
    }

    /**
     * @see main.java.memoranda.Event#getId()
     */
    public String getId() {
        Attribute a = _elem.getAttribute("id");
        if (a != null) return a.getValue();
        return null;
    }

    /**
     * @see main.java.memoranda.Event#getRepeat()
     */
    public int getRepeat() {
        Attribute a = _elem.getAttribute("repeat-type");
        if (a != null) return Integer.parseInt(a.getValue());
        return 0;
    }

    /**
     * @see main.java.memoranda.Event#getTime()
     */
    public Date getTime() {
		Date d = new Date(); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
		Calendar calendar = new GregorianCalendar(Local.getCurrentLocale()); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
		calendar.setTime(d); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
		calendar.set(Calendar.HOUR_OF_DAY, getHour()); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
		calendar.set(Calendar.MINUTE, getMinute()); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
		calendar.set(Calendar.SECOND, 0); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
		d = calendar.getTime(); //Revision to fix deprecated methods (jcscoobyrs) 12-NOV-2003 14:26:00
        return d;
    }
	
	/**
     * @see Event#getWorkingDays()
     */
	public boolean getWorkingDays() {
        Attribute a = _elem.getAttribute("workingDays");
        return a != null && a.getValue().equals("true");
    }

    /**
     * comparable interface
     * @param o Event
     * @return int
     */
	public int compareTo(Object o) {
		Event event = (Event) o;
		return (getHour() * 60 + getMinute()) - (event.getHour() * 60 + event.getMinute());
	}

}
