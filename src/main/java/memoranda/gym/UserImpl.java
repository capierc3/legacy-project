package main.java.memoranda.gym;

import java.io.File;
import java.util.Collection;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.Note;
import main.java.memoranda.date.CalendarDate;
import nu.xom.Attribute;
import nu.xom.Element;

/**
 * Interface for the User methods which will be extended to Trainer, Student and Owner
 * @author Daimi Mussey
 */
public class UserImpl implements User {
    private File picture;
    private Collection<Note> noteList;
    private ClassList userClasses;

    protected Element element;

    public UserImpl(String name, String id, String userName, String password,
                    Belt belt, File newPicture, Collection<Note> newNoteList, ClassList newUserClasses, String userType) {
        element = new Element(userType);
        setAttr("Name", name);
        setAttr("Id", id);
        setAttr("UserName", userName);
        setAttr("Password", password);
        setAttr("Rank", String.valueOf(belt.getValue()));
        picture = newPicture;
        setPicture(picture);
        noteList = newNoteList;
        setNoteList(noteList);
        userClasses = newUserClasses;
        setClassList(userClasses);
    }

    /**
     * Method to set NotesList in Element.
     * @param noteList
     */
    public void setNoteList(Collection<Note> noteList) {
        Element notes = new Element("NoteList");
        for (Note note : noteList) {
            notes.appendChild(note.getContent());
        }
        element.appendChild(notes);
    }

    /**
     * Method to set ClassList in Element.
     * @param classList
     */
    public void setClassList(ClassList classList) {
        element.appendChild(userClasses.getContent().copy());
    }

    /**
     * Method to set the file for picture in Element.
     */
    public void setPicture(File fileName) {
        picture = fileName;
        setAttr("Picture", fileName.getPath());
    }

    /**
     * Method to return name String
     * @return String
     */
    public String getName() {
        return element.getAttributeValue("Name");
    }

    /**
     * Method to set name String
     */
    public void setName(String newName) {
        setAttr("Name", newName);
    }

    /**
     * Method to get User Id
     * @return String
     */
    public String getID() {
        return element.getAttributeValue("Id");
    }

    /**
     * Method to set new Id on a User
     * @param newId
     */
    public void setID(String newId) {
        setAttr("Id", newId);
    }

    /**
     * Method to get User's userName
     * @return String
     */
    public String getUserName() {
        return element.getAttributeValue("UserName");
    }

    /**
     * Method to set User's userName
     * @param newUserName
     */
    public void setUserName(String newUserName) {
        setAttr("UserName", newUserName);
    }

    /**
     * Method to get User's password
     * @return String
     */
    public String getPassword() {
        return element.getAttributeValue("Password");
    }

    /**
     * Method to set a User's password to String passed in
     * @param newPassword
     */
    public void setPassword(String newPassword) {
        setAttr("Password", newPassword);
    }

    /**
     * Method to return User's belt
     * @return Belt
     */
    public Belt getBelt() {
        return Belt.getBelt(Integer.parseInt(element.getAttributeValue("Rank")));
    }

    /**
     * Method  to set a User's belt
     * @param rank Belt
     */
    public void setBelt(Belt rank) {
        setAttr("Rank",String.valueOf(rank.getValue()));
    }

    /**
     * Method to return image file for User
     * @return File
     */
    public File getPic() {
        return picture;
    }

    /**
     * Method to set the User's picture to file passed in
     * @param newPicture
     */
    public void setPic(File newPicture) {
        picture = newPicture;
        setPicture(picture);
    }

    /**
     * Method to return collection of Notes tied to the User
     * @return Collection
     */
    public Collection getNotes() {
        return noteList;
    }

    /**
     * Method to add a Note to the noteList
     * @param note
     */
    public void addNote(Note note) {
        noteList.add(note);
        setNoteList(noteList);
    }

    /**
     * Returns a ClassList filled with any events for that day, trainers and owners will see a list of classes they teach,
     * Students see ones they are registered for.
     * @return ClassList
     */
    public ClassList getTodaysEvents() {
        ClassList result = null;
        CalendarDate todaysDate = new CalendarDate();
        if (userClasses != null) {
            result = userClasses.getListByDate(todaysDate);
        }
        return result;
    }

    @Override
    public ClassList getAllClasses() {
        return userClasses;
    }

    @Override
    public void addClass(GymClass gymClass) {
        userClasses.addClass(gymClass);
    }

    public Element getContent() {
        return element;
    }

    private void setAttr(String a, String value) {
        Attribute attr = element.getAttribute(a);
        if (attr == null)
            element.addAttribute(new Attribute(a, value));
        else
            attr.setValue(value);
    }
}
