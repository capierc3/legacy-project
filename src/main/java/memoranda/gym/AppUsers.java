package main.java.memoranda.gym;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * The AppUsers class is a data model that houses information on all application users.
 */

public class AppUsers implements UserList {

    private final String APP_USER_FILE_PATH = "/appusers.dat";
    private HashMap<String, User> appUsers;
    private User activeUser;

    /**
     * Class constructor.  Initiates appUser collection
     * */
    public AppUsers() {

        appUsers = new HashMap<>();

    }

    /**
     * Simple getter method for User
     *
     * @param id String value of user ID
     * @return User object from collection.  Returns null if id does not exist in collection.
     */
    public User getUser(String id) {

        if(appUsers.containsKey(id)) {
            return appUsers.get(id);
        }

        return null;

    }

    /**
     * Simple setter method for adding User to collection
     *
     * @param user User object to be added to collection
     * @return void
     */
    public void addUser(User user) {

        String id = user.getID();

        if(!appUsers.containsKey(id)){
            appUsers.put(id, user);
        }

    }

    /**
     * Removed user from collection
     *
     * @param id User object to be removed from collection
     * @return void
     */
    public void removeUser(String id) {

        if(appUsers.containsKey(id)) {

            appUsers.remove(id);

        }

    }

    /**
     * Get the size of the collection
     *
     * @return int size of collection
     */
    public int getSize() {

        return appUsers.size();

    }

    /**
     * Return entire collection of users
     *
     * @return LinkedList of all users in collection.  Returns null if collection is empty.
     */
    public Collection<User> getAllUsers() {

        if(appUsers.size() > 0) {
            LinkedList<User> list = new LinkedList<User>();
            appUsers.forEach((k,v) -> list.add(v));
            return list;
        }

        return null;

    }

    /**
     *
     * @param login User's login name
     * @param password User's super-not-so-encrypted password
     * @return true if password & login match.
     */
    public Boolean verifyPassword(String login, String password){

        User user = getUser(login);

        if(user != null && user.getPassword().contentEquals(password)){
            return true;
        }

        return false;
    }

    /**
     * Returns active user
     * @return active User
     */
    public User getActiveUser(){
        return activeUser;
    }

    /**
     * Sets active user
     * @param user User object to set as active user
     */
    public void setActiveUser(User user) {
        activeUser = user;
    }
}
