package memoranda.gym;

import java.io.File;
import java.util.Collection;

/**
 * Interface for the User methods which will be extended to Trainer, Student and Owner
 * @author Daimi Mussey
 */
public class UserImpl {
    String name;
    String id;
    String userName;
    String password;
    File picture;
    String belt;
    Collection<Note> noteList;
    ClassList userClasses;

    /**
     * Method to return name String
     * @return String
     */
    String getName() {
        return name;
    }

    /**
     * Method to set name String
     */
    void setName(String newName) {
        name = newName;
    }

    /**
     * Method to get User Id
     * @return String
     */
    String getID() {
        return id;
    }

    /**
     * Method to set new Id on a User
     * @param newId
     */
    void setID(String newId) {
        id = newId;
    }

    /**
     * Method to get User's userName
     * @return String
     */
    String getUserName() {
        return userName;
    }

    /**
     * Method to set User's userName
     * @param newUserName
     */
    void setUserName(String newUserName) {
        userName = newUserName;
    }

    /**
     * Method to get User's password
     * @return String
     */
    String getPassword() {
        return password;
    }

    /**
     * Method to set a User's password to String passed in
     * @param newPassword
     */
    void setPassword(String newPassword) {
        password = newPassword;
    }

    /**
     * Method to return User's belt
     * @return String
     */
    String getBelt() {
        return belt;
    }

    /**
     * Method  to set a User's belt
     * @param newBelt
     */
    void setBelt(String newBelt) {
        belt = newBelt;
    }

    /**
     * Method to return image file for User
     * @return File
     */
    File getPic() {
        return picture;
    }

    /**
     * Method to set the User's picture to file passed in
     * @param newPicture
     */
    void setPic(File newPicture) {
        picture = newPicture;
    }

    /**
     * Method to return collection of Notes tied to the User
     * @return Collection
     */
    Collection getNotes() {
        return noteList;
    }

    /**
     * Method to add a Note to the noteList
     * @param note
     */
    void addNote(Note note) {
        noteList.add(note);
    }

    /**
     * Returns a ClassList filled with any events for that day, trainers and owners will see a list of classes they teach,
     * Students see ones they are registered for.
     * @return ClassList
     */
    ClassList getTodaysEvents() {
        ClassList result = null;
        CurrentDate todaysDate = new CurrentDate();
        if (userClasses != null) {
            result = userClasses.getListByDate(todaysDate.get());
        }
        return result;
    }
}
