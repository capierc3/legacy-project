package main.java.memoranda;

import java.util.Collection;
import java.util.Vector;

/**
 * Holds the information of the currently selected note.
 */
public class CurrentNote {

	private static Note currentNote = null;
    private static Vector noteListeners = new Vector();

    /**
     * Basic getter method
     * @return Note
     */
    public static Note get() {
        return currentNote;
    }

    /**
     * Sets the inputted note to be the current note.
     * @param note Note object
     * @param toSaveCurrentNote Boolean
     */
    public static void set(Note note, boolean toSaveCurrentNote) {
        noteChanged(note, toSaveCurrentNote);
        currentNote = note;
    }

    /**
     * Sets the current note to a null object
     */
    public static void reset() {
//    	 set toSave to true to mimic status quo behaviour only. the appropriate setting could be false
        set(null, true);
    }

    /**
     * Adds a new listener to the vectors
     * @param nl NoteListener object]
     */
    public static void addNoteListener(NoteListener nl) {
        noteListeners.add(nl);
    }

    /**
     * Getter for the noteListener vector
     * @return Vector
     */
    public static Collection getChangeListeners() {
        return noteListeners;
    }

    /**
     * Informs the vectors in noteListeners that the current note has changed
     * @param note Note object
     * @param toSaveCurrentNote boolean
     */
    private static void noteChanged(Note note, boolean toSaveCurrentNote) {
        for (int i = 0; i < noteListeners.size(); i++) {
            ((NoteListener)noteListeners.get(i)).noteChange(note,toSaveCurrentNote);
		}
    }
}
