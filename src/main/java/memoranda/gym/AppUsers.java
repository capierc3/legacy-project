package main.java.memoranda.gym;



import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * The AppUsers class is a data model that houses information on all application users.
 */

public class AppUsers implements UserList {

    private final String APP_USER_FILE_PATH = "appusers.dat";
    private HashMap<String, User> appUsers;
    private User activeUser;

    /**
     * Class constructor.  Initiates appUser collection
     * */
    public AppUsers() {
        appUsers = new HashMap<>();
        User user = new StudentImpl("Johnny Karate","JK","student","Password",Belt.WHITE,
                new File(""),new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        UserImpl user1 = new OwnerImpl("Fancy Nancy","admin001","admin","Password",
                Belt.BLACK3,new File(""),new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        UserImpl user2 = new TrainerImpl("Country Mac","CMac","trainer","Password",
                Belt.BLACK3,new File(""),new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        appUsers.put(user.getUserName(),user);
        appUsers.put(user1.getUserName(),user1);
        appUsers.put(user2.getUserName(),user2);
    }

    /**
     * Simple getter method for User
     *
     * @param login String value of user's login
     * @return User object from collection.  Returns null if id does not exist in collection.
     */

    public User getUser(String login) {

        if(appUsers.containsKey(login)) {
            return appUsers.get(login);
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

        String login = user.getUserName();

        if(!appUsers.containsKey(login)){
            appUsers.put(login, user);

            //TODO: Implement in Sprint 3
            //save to file
            //saveToFile(appUsers, APP_USER_FILE_PATH);
        }

    }

    /**
     * Removed user from collection
     *
     * @param login User object to be removed from collection
     * @return void
     */
    public void removeUser(String login) {

        appUsers.remove(login);

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

    /**
     * Saves hashMap to file
     *
     * @param obj object to be saved to file
     * @param file_path file path to save file to
     */
    private void saveToFile(Object obj, String file_path) {

        try {
            System.out.println("Saving object to file...");
            FileOutputStream fileOutputStream = new FileOutputStream(file_path);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(obj);
            outputStream.close();

            System.out.println("Object saved to " + file_path);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Load object from file
     *
     * @param file_path file location
     */
    private void loadFromFile(String file_path){
        //read from file



    }

}
