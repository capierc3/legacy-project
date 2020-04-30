package main.java.memoranda.gym;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.regex.Matcher;

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
        if (noteList != null) {
            setNoteList(noteList);
        }
        userClasses = newUserClasses;
        if (userClasses != null) {
            setClassList(userClasses);
        }
    }

    /**
     * Method to set NotesList in Element.
     * @param noteList
     */
    public void setNoteList(Collection<Note> noteList) {

        if(!noteList.isEmpty() || noteList != null) {
            Element notes = new Element("NoteList");
            for (Note note : noteList) {
                notes.appendChild(note.getContent().copy());
            }
            element.appendChild(notes);
        }
    }

    /**
     * Method to set ClassList in Element.
     * @param classList
     */
    public void setClassList(ClassList classList) {
        if(classList != null) {
            Element e = new Element("ClassList");
            e.appendChild(userClasses.getContent().copy());
            element.appendChild(e);
        }
    }

    /**
     * Method to set the file for picture in Element.
     */
    public void setPicture(File fileName) {
        if (fileName == null) {
            try {
                fileName = new File(this.getClass().getResource("/ui/icons/nunchuckNorris.png").toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        picture = fileName;

        String[] pathArray = fileName.getPath().split(Matcher.quoteReplacement(System.getProperty("file.separator")));
        String path = "/ui/icons/" + pathArray[pathArray.length - 1];
        setAttr("Picture",path);
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

    @Override
    public void removeClass(GymClass gymClass) {
        userClasses.removeClass(gymClass.getID());
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

    @Override
    public String toString() {
        return getName();
    }
}
