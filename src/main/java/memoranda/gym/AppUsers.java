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
 * The AppUsers class is a data model that houses information on all application users.
 */

public class AppUsers implements UserList {

    private final String APP_USER_FILE_PATH = "server/appusers.xml";
    private HashMap<String, User> appUsers;
    private User activeUser;
    private Element element;
    private MyScheduleManager manager;

    /**
     * Class constructor.  Initiates appUser collection
     * */
    public AppUsers() {
        element = new Element("AppUser");
        File usersFile = new File(APP_USER_FILE_PATH);
        if (usersFile.exists()) {
            try {
                System.out.println("Loaded");
                loadFromFile();
            } catch (ParsingException | IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("hardCoded");
            try {
                appUsers = new HashMap<>();
                hardCodedData();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
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
        return user != null && user.getPassword().contentEquals(password);
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
            ObjectSerializer.serializeElement(element,APP_USER_FILE_PATH);
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
        addUser(student1);
        addUser(student2);
        addUser(student3);
        addUser(student4);
        addUser(user1);
        addUser(user2);
        addUser(trainer1);
        addUser(trainer2);
        addUser(trainer3);
        addUser(trainer4);
        addUser(trainer5);
        addUser(trainer6);
    }

}
