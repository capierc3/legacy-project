package main.java.memoranda.gym;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.ClassList;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import main.java.memoranda.Note;
import nu.xom.Element;
import nu.xom.Elements;

/**
 * Interface for the User methods which will be extended to Trainer, Student and Owner
 * @author Chase
 */
public interface User extends Serializable {

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
    Belt getBelt();
    void setBelt(Belt newBelt);
    File getPic();
    void setPic(File newPicture);
    Collection getNotes();
    void addNote(Note note);

    /**
     * Returns a ClassList filled with any events for that day, trainers and owners will see a list of classes they teach,
     * Students see ones they are registered for.
     * @return ClassList
     */
    ClassList getTodaysEvents();
    ClassList getAllClasses();
    void addClass(GymClass gymClass);
    void removeClass(GymClass gymClass);
    Element getContent();
    static User elmToUser(Element el) {
        String name = el.getAttributeValue("Name");
        String id = el.getAttributeValue("Id");
        String userName = el.getAttributeValue("UserName");
        String password = el.getAttributeValue("Password");
        Belt belt = Belt.getBelt(Integer.parseInt(el.getAttributeValue("Rank")));
        File newPicture = new File(el.getAttributeValue("Picture"));
        Collection newNoteList = new ArrayList();
        ClassList newUserClasses = ClassList.elmToClassList(el.getFirstChildElement("ClassList"));
        String userType = el.getLocalName();
        if (userType.equalsIgnoreCase("Student")) {
            return new StudentImpl(name,id,userName,password,belt,newPicture,new ArrayList<>(),newUserClasses);
        } else if (userType.equalsIgnoreCase("Trainer")) {
            Trainer trainer = new TrainerImpl(name, id, userName, password, belt, newPicture, new ArrayList<>(), newUserClasses);
            trainer.setDescription(el.getAttributeValue("Description"));
            ArrayList<CalendarDate> dates = new ArrayList<>();
            Elements elms = el.getFirstChildElement("Available").getChildElements("Date");
            for (int i = 0; i < elms.size(); i++) {
                dates.add(new CalendarDate(elms.get(i).getAttributeValue("date")));
            }
            trainer.setAvailability(dates);
            return trainer;
        } else {
            return new OwnerImpl(name,id,userName,password,belt,newPicture,new ArrayList<>(),newUserClasses);
        }
    }

}
