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

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;

/**
 * The AppUsers class is a data model that houses information on all application users.
 */

public class AppUsers implements UserList {

    private final String APP_USER_FILE_PATH = "appusers.xml";
    private HashMap<String, User> appUsers;
    private User activeUser;
    private Element element;

    /**
     * Class constructor.  Initiates appUser collection
     * */
    public AppUsers() {
        appUsers = new HashMap<>();
        try {
            hardCodedData();
            saveToFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        loadFromFile();
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

            //save to file
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

        appUsers.remove(login);

        //save to file
        saveToFile();
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
     */
    private void saveToFile() {

        try {

            Element element = convertLibraryToXML();
            ObjectSerializer.serializeObject(element, APP_USER_FILE_PATH);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    /**
     * Convert appUsers to XML Element
     * @return Element
     */
    private Element convertLibraryToXML() {

        Element element = new Element("appUsers");

        appUsers.forEach((k,v) -> {
            Element e = new Element("user");
            e.appendChild(v.getContent().copy());
            element.appendChild(e);
        });

        return element;
    }

    /**
     * Load object from file
     *
     */
    private void loadFromFile(){

        try {
            System.out.println("Loading AppUsers from " + APP_USER_FILE_PATH);
            element = (Element) ObjectSerializer.deserializeObject(APP_USER_FILE_PATH);
            populateLibrary(element);

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();

        }

    }

    private void populateLibrary(Element element) {

        AppUsers appUsers = new AppUsers();

        Elements elements = element.getChildElements("User");
        for (int i = 0; i < elements.size(); i++) {
            //appUsers.addUser(User.elementToUser(elements.get(i)));
        }

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
        File kip = new File(this.getClass().getResource("/ui/icons/kip.jpg").toURI());
        File dan = new File(this.getClass().getResource("/ui/icons/dan.jpg").toURI());
        File chosen = new File(this.getClass().getResource("/ui/icons/chosen.jpeg").toURI());
        File johnny = new File(this.getClass().getResource("/ui/icons/johnny.jpg").toURI());
        User student1 = new StudentImpl("Johnny Karate","JK","student","Password",Belt.ORANGE,
                johnny,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        User student2 = new StudentImpl("Kip Dynamite",Util.generateId(),"KDynamite","Password",Belt.WHITE,
                kip,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        User student3 = new StudentImpl("Daniel LaRusso",Util.generateId(),"LaRUu","Password",Belt.BROWN3,
                dan,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        User student4 = new StudentImpl("The Chosen One",Util.generateId(),"Chosen1","Password",Belt.BLACK3,
                chosen,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
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
        appUsers.put(student1.getUserName(),student1);
        appUsers.put(student2.getUserName(),student2);
        appUsers.put(student3.getUserName(),student3);
        appUsers.put(student4.getUserName(),student4);
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
