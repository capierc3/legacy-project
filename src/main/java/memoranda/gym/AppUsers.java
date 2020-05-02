package main.java.memoranda.gym;

import main.java.memoranda.ui.MyScheduleManager;
import main.java.memoranda.util.Util;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import nu.xom.*;

/**
 * The AppUsers class is a data model that houses information on all application
 * users.
 */

public class AppUsers implements UserList {

    private final String APP_USER_FILE_PATH = "server/appusers.xml";
    private HashMap<String, User> appUsers;
    private User activeUser;
    private Element element;
    private MyScheduleManager manager;

    /**
     * Class constructor.  Initiates appUser collection
     */
    public AppUsers() {
        element = new Element("AppUser");
        File usersFile = new File(APP_USER_FILE_PATH);
        if (usersFile.exists()) {
            try {
                System.out.println("Users Loaded");
                loadFromFile();
            } catch (ParsingException | IOException e) {
                e.printStackTrace();
            }
        } else {
            appUsers = new HashMap<>();
        }
    }

    /**
     * Simple getter method for User
     *
     * @param login String value of user's login
     * @return User object from collection. Returns null if id does not exist in
     *         collection.
     */

    public User getUser(String login) {
        if (appUsers.containsKey(login)) {
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
        if (!appUsers.containsKey(login)) {
            appUsers.put(login, user);
            Element e = new Element("User");
            e.appendChild(user.getContent().copy());
            element.appendChild(e);
            saveToFile();
        }
    }

    /**
     * Removed user from collection
     *
     * @param login User object to be removed from collection
     * @return void
     */
    public void removeUser(String login) {
        if (appUsers.containsKey(login)) {
            appUsers.remove(login);
            saveToFile();
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
     * @return LinkedList of all users in collection. Returns null if collection is
     *         empty.
     */
    public Collection<User> getAllUsers() {

        if (appUsers.size() > 0) {
            LinkedList<User> list = new LinkedList<User>();
            appUsers.forEach((k, v) -> list.add(v));
            return list;
        }
        return null;
    }

    /**
     *Verifies the password when logging in.
     *
     * @param login    User's login name
     * @param password User's super-not-so-encrypted password
     * @return true if password & login match.
     */

    public String verifyPassword(String login, String password) {
        User user = getUser(login);
        if(user != null && user.getPassword().contentEquals(password)){
            return true;
        }

        return false;
    }

    /**
     * Returns active user
     *
     * @return active User
     */
    public User getActiveUser() {
        return activeUser;
    }

    /**
     * Sets active user
     *
     * @param user User object to set as active user
     */
    public void setActiveUser(User user) {
        manager = new MyScheduleManager(user);
        activeUser = user;
    }

    public static AppUsers elmToUserList(Element el) {
        AppUsers appUsers = new AppUsers();
        Elements elms = el.getChildElements("User");
        for (int i = 0; i < elms.size(); i++) {
            appUsers.addUser(User.elmToUser(elms.get(i)));
        }
        return appUsers;
    }

    public Element getContext() {
        return element;
    }

    public MyScheduleManager getManager() {
        return manager;
    }

    public void setManager(MyScheduleManager manager) {
        this.manager = manager;
    }

    /**
     * Saves hashMap to file.
     */
    public void saveToFile() {
        try {
            ObjectSerializer.serializeElement(element, APP_USER_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves hashMap to file
     *
     * @param obj       object to be saved to file
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
     * Load object from file.
     */
    private void loadFromFile() throws IOException, ParsingException {
        appUsers = new HashMap<>();
        Builder parser = new Builder();
        InputStream fileInputStream = new FileInputStream(APP_USER_FILE_PATH);
        Document readDoc = parser.build(fileInputStream);
        populateLibrary(readDoc.getRootElement());
    }

    private void populateLibrary(Element element) {
        Elements elements = element.getChildElements("User");
        for (int i = 0; i < elements.size(); i++) {
            Element element1 = element.getChildElements("User").get(i).getChildElements().get(0);
            User user = User.elmToUser(element1);
            addUser(user);
        }
    }

}
