package main.java.memoranda;
import java.util.Date;

import main.java.memoranda.date.CalendarDate;

/*$Id: Event.java,v 1.4 2004/07/21 17:51:25 ivanrise Exp $*/
/**
 * Interface for the logic of the Events
 */
public interface Event {
    /**
     * getter for the ID
     * @return String
     */
    String getId();
    

    /**
     * getter for the hour of the event
     * @return int
     */
    int getHour();

    /**
     * getter for the minute of the event
     * @return int
     */
    int getMinute();


    /**
     * getter for the text of the event
     * @return String
     */
    String getText();

    /**
     * getter for the main element of the even
     * @return Element
     */
    nu.xom.Element getContent();

    /**
     * gets the information of a repeats event
     * @return int
     */
    int getRepeat();

    /**
     * gets the start date of the event
     * @return CalendarDate
     */
    CalendarDate getStartDate();

    /**
     * gets the end date of the event
     * @return CalendarDate
     */
    CalendarDate getEndDate();

    /**
     * gets the vale of the period of the event
     * @return int
     */
    int getPeriod();

    /**
     * returns if the event is repeatable
     * @return boolean
     */
    boolean isRepeatable();

    /**
     * gets the date of the event as a Date object
     * @return Date
     */
    Date getTime();

    /**
     * Getter for time of the event as a string
     * @return String
     */
    String getTimeString();

    /**
     * gets the working days of the event
     * @return boolean
     */
	boolean getWorkingDays();

    //CalendarDate getDate();
    //Date getTime();
}
