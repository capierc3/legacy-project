package main.java.memoranda.gym;
import main.java.memoranda.gym.ClassList;
import java.io.File;
import java.util.Collection;
import main.java.memoranda.Note;
import nu.xom.Element;

/**
 * Interface for the User methods which will be extended to Trainer, Student and Owner
 * @author Chase
 */
public interface User {

    /**
     * Method to set NoteList in Element.
     * @param noteList
     */
    void setNoteList(Collection<Note> noteList);

    /**
     * Method to set ClassList in Element.
     * @param classList
     */
    void setClassList(ClassList classList);

    /**
     * Method to set picture to filename in Element.
     * @param fileName
     */
    void setPicture(File fileName);

    //All pretty basic getters and setters
    String getName();
    void setName(String newName);
    String getID();
    void setID(String newId);
    String getUserName();
    void setUserName(String newUserName);
    String getPassword();
    void setPassword(String newPassword);
    String getBelt();
    void setBelt(String newBelt);
    File getPic();
    void setPic(String newPicture);
    Collection getNotes();
    void addNote(Note note);

    /**
     * Returns a ClassList filled with any events for that day, trainers and owners will see a list of classes they teach,
     * Students see ones they are registered for.
     * @return ClassList
     */
    ClassList getTodaysEvents();
    Element getContent();

}
