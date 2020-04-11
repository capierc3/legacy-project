package memoranda.gym;

import java.io.File;
import java.util.Collection;

/**
 * Interface for the User methods which will be extended to Trainer, Student and Owner
 * @author Daimi Mussey
 */
public class UserImpl {
    private String name;
    private String id;
    private String userName;
    private String password;
    private File picture;
    private String belt;
    private Collection<Note> noteList;
    private ClassList userClasses;

    /**
     * Method to return name String
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set name String
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Method to get User Id
     * @return String
     */
    public String getID() {
        return id;
    }

    /**
     * Method to set new Id on a User
     * @param newId
     */
    public void setID(String newId) {
        id = newId;
    }

    /**
     * Method to get User's userName
     * @return String
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Method to set User's userName
     * @param newUserName
     */
    public void setUserName(String newUserName) {
        userName = newUserName;
    }

    /**
     * Method to get User's password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to set a User's password to String passed in
     * @param newPassword
     */
    public void setPassword(String newPassword) {
        password = newPassword;
    }

    /**
     * Method to return User's belt
     * @return String
     */
    public String getBelt() {
        return belt;
    }

    /**
     * Method  to set a User's belt
     * @param newBelt
     */
    public void setBelt(String newBelt) {
        belt = newBelt;
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
    }

    /**
     * Returns a ClassList filled with any events for that day, trainers and owners will see a list of classes they teach,
     * Students see ones they are registered for.
     * @return ClassList
     */
    public ClassList getTodaysEvents() {
        ClassList result = null;
        CurrentDate todaysDate = new CurrentDate();
        if (userClasses != null) {
            result = userClasses.getListByDate(todaysDate.get());
        }
        return result;
    }
}
