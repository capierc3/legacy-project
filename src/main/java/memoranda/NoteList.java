package main.java.memoranda;
import java.util.Collection;

import main.java.memoranda.date.CalendarDate;
/*$Id: NoteList.java,v 1.5 2004/10/07 21:33:36 ivanrise Exp $*/
/**
 * Interface used for the list of notes
 */
public interface NoteList {

    /**
     * getter for the collection of notes
     * @return Collection
     */
    Collection getAllNotes();

    /**
     * getter that returns all marked notes
     * @return Collection
     */
    Collection getMarkedNotes();

    /**
     * get all notes in a period of time
     * @param startDate CalendarDate
     * @param endDate CalendarDate
     * @return Collection
     */
    Collection getNotesForPeriod(CalendarDate startDate, CalendarDate endDate);

    /**
     * Getter for a the first note from a certain date
     * @param date CalendarDate
     * @return Note
     */
    Note getNoteForDate(CalendarDate date);

    /**
     * Creates and returns a new note for inputted date
     * @param date CalendarDate
     * @return Note
     */
    Note createNoteForDate(CalendarDate date);

    /**
     * Removes a note from the list with an inputted ID
     * @param date CalendarDate
     * @param id String
     */
	void removeNote(CalendarDate date, String id);

    /**
     * Gets the active note from the list
     * @return Note
     */
    Note getActiveNote();

    /**
     * gets the content of an XML element
     * @return nu.xom.Document
     */
    nu.xom.Document getXMLContent();

//    void removeNoteForDate(CalendarDate date);
}
