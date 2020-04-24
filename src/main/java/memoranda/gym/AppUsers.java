package main.java.memoranda.gym;



import main.java.memoranda.ui.TrainerCardPanel;
import main.java.memoranda.util.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;


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
        try {
            hardCodedData();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

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

    /**
     * All the hard coded data i've found so far. Moved here for easy deletion later.
     */
    private void hardCodedData() throws URISyntaxException {

        File defaultPic = new File(this.getClass().getResource("/ui/icons/nunchuckNorris.png").toURI());
        File mac = new File(this.getClass().getResource("/ui/icons/Mac.jpeg").toURI());
        File cameron = new File(this.getClass().getResource("/ui/icons/howe.jpg").toURI());
        File joe = new File(this.getClass().getResource("/ui/icons/joe.jpg").toURI());
        File gordon = new File(this.getClass().getResource("/ui/icons/gordon.jpg").toURI());
        File donna = new File(this.getClass().getResource("/ui/icons/donna.jpg").toURI());
        File john = new File(this.getClass().getResource("/ui/icons/john.jpg").toURI());
        User user = new StudentImpl("Johnny Karate","JK","student","Password",Belt.WHITE,
                defaultPic,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        UserImpl user1 = new OwnerImpl("Fancy Nancy","admin001","admin","Password",
                Belt.BLACK3,defaultPic,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        Trainer user2 = new TrainerImpl("Country Mac","CMac","trainer","Password",
                Belt.BLACK3,mac,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        user2.setDescription("I gave him an ocular patdown, assessed the threat level, clocked a knife in his boot.");
        Trainer trainer1 = new TrainerImpl("Justic Oliver", Util.generateId(),"Joliver",
                "Password",Belt.BLUE,defaultPic,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        trainer1.setDescription("I use ketchup on everything!");
        Trainer trainer2 = new TrainerImpl("Cameron Howe", Util.generateId(),"Chowe",
                "Password",Belt.GREEN_STRIPE,cameron,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        trainer2.setDescription("What the hell is this Yahoo");
        Trainer trainer3 = new TrainerImpl("Joe MacMillan", Util.generateId(),"JMac",
                "Password",Belt.ORANGE,joe,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        trainer3.setDescription("The thing that gets you to the thing");
        Trainer trainer4 = new TrainerImpl("Gordon Clark", Util.generateId(),"Gclark",
                "Password",Belt.BLACK2,gordon,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        trainer4.setDescription("We had a problem. Now we have a product.");
        Trainer trainer5 = new TrainerImpl("Donna Clark", Util.generateId(),"Dclark",
                "Password",Belt.BLACK3,donna,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        trainer5.setDescription("Software comes and goes. Hardware is forever.");
        Trainer trainer6 = new TrainerImpl("John Bosworth", Util.generateId(),"JBos",
                "Password",Belt.WHITE,john,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        trainer6.setDescription("Innovation is a risk.");
        appUsers.put(user.getUserName(),user);
        appUsers.put(user1.getUserName(),user1);
        appUsers.put(user2.getUserName(),user2);
        appUsers.put(trainer1.getUserName(),trainer1);
        appUsers.put(trainer2.getUserName(),trainer2);
        appUsers.put(trainer3.getUserName(),trainer3);
        appUsers.put(trainer4.getUserName(),trainer4);
        appUsers.put(trainer5.getUserName(),trainer5);
        appUsers.put(trainer6.getUserName(),trainer6);

    }

}
