package main.java.memoranda;

import main.java.memoranda.date.CalendarDate;
import nu.xom.Element;

/*$Id: Note.java,v 1.4 2004/09/30 17:19:52 ivanrise Exp $*/
/**
 * Interface for note Objects
 */
public interface Note {
    /**
     * gets the Calendar date object from the note
     * 
     * @return CalendarDate
     */
    CalendarDate getDate();

    /**
     * getter for the title of the note
     * 
     * @return String
     */
    String getTitle();

    /**
     * setter for the title of the note
     * 
     * @param s String
     */
    void setTitle(String s);

    /**
     * getter for the note ID
     * 
     * @return String
     */
    String getId();

    /**
     * Setter for the ID of the note
     * 
     * @param s String
     */
    void setId(String s);

    /**
     * getter for that returns if the note is Marked
     * 
     * @return Boolean
     */
    boolean isMarked();

    /**
     * setter for the notes isMarked
     * 
     * @param mark boolean
     */
    void setMark(boolean mark);

    /**
     * getter that returns the project associated with the note
     * 
     * @return Project
     */
    Project getProject();

    /**
     * Get Element for Note
     */
    Element getContent();
}
