package test.java;

import main.java.memoranda.gym.AppUsers;
import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.User;
import main.java.memoranda.gym.UserImpl;
import main.java.memoranda.gym.ClassListImpl;
import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class Task93_Whitebox {

    /*
     * Any actions that need to happen before any test methods in the class are ran
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }


    /*
     * Any actions taht need to run after all the tests in this class are ran
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }


    /*
     * Anything that should happen before any test is ran
     */
    @Before
    public void setUp() throws Exception {
    }


    /*
     * Anything that needs to happen after a test is ran and before the next test
     * such as resetting a variable
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void verifyUserAddition() {
        AppUsers app = new AppUsers();

        UserImpl user = new UserImpl("John Doe","jd001","john","catsRkool64",
                Belt.BLACK3,new File(""),new ArrayList<>(),
                new ClassListImpl(new ArrayList<>()), "User");

        app.addUser(user);

        User u = app.getUser(user.getUserName());
        assertEquals(u, user);
    }

    @Test
    public void verifyUserRemoval() {

        AppUsers app = new AppUsers();

        UserImpl user = new UserImpl("John Doe","jd001","john","catsRkool64",
                Belt.BLACK3,new File(""),new ArrayList<>(),
                new ClassListImpl(new ArrayList<>()), "User");

        app.addUser(user);

        app.removeUser(user.getID());

        User result = app.getUser(user.getID());
        assertNull(result);

    }

    @Test
    public void verifyLogin() {
        AppUsers app = new AppUsers();

        UserImpl user = new UserImpl("John Doe","jd001","john","catsRkool64",
                Belt.BLACK3,new File(""),new ArrayList<>(),
                new ClassListImpl(new ArrayList<>()), "User");

        app.addUser(user);

        String actual = app.verifyPassword("john", "catsRkool64");
        assertTrue(actual.equalsIgnoreCase("found"));
        actual = app.verifyPassword("Wrong name", "catsRkool64");
        assertTrue(actual.equalsIgnoreCase("User not found"));
        actual = app.verifyPassword("john", "Wrong Password");
        assertTrue(actual.equalsIgnoreCase("Incorrect Password"));

    }

}
