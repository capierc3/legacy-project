package main.java.memoranda;

import java.util.Collection;
import java.util.Vector;

public class CurrentNote {

	private static Note currentNote = null;
    private static Vector noteListeners = new Vector();

    /**
     * Method that returns the current note
     * @return Note
     */
    public static Note get() {
        return currentNote;
    }

    /**
     * Method to set a Note when the note is changed
     * @param note
     * @param toSaveCurrentNote
     */
    public static void set(Note note, boolean toSaveCurrentNote) {
        noteChanged(note, toSaveCurrentNote);
        currentNote = note;
    }

    /**
     * Method that resets the note
     */
    public static void reset() {
//    	 set toSave to true to mimic status quo behaviour only. the appropriate setting could be false
        set(null, true);
    }

    /**
     * Method that adds a NoteListener to list of noteListeners
     * @param nl
     */
    public static void addNoteListener(NoteListener nl) {
        noteListeners.add(nl);
    }

    /**
     * Method that gets the noteListeners and returns the Collection
     * @return Collection
     */
    public static Collection getChangeListeners() {
        return noteListeners;
    }

    /**
     * Method that takes the current note and whether to save the note, then updates the NoteListneners for that
     * note
     * @param note
     * @param toSaveCurrentNote
     */
    private static void noteChanged(Note note, boolean toSaveCurrentNote) {
        for (int i = 0; i < noteListeners.size(); i++) {
            ((NoteListener)noteListeners.get(i)).noteChange(note,toSaveCurrentNote);
		}
    }
}
