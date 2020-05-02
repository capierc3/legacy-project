package test.java;

import main.java.memoranda.gym.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Test passed on local but travis hates file creation so to test uncomment "@Test"
 */
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


    //@Test
    public void verifyUserAddition(){
        AppUsers app = new AppUsers();

        Student user = new StudentImpl("John Doe","jd001","john","catsRkool64",
                Belt.BLACK3,null,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        app.addUser(user);

        User u = app.getUser(user.getUserName());
        Assert.assertEquals(u.getID(), user.getID());
        app.removeUser(user.getID());
        User result = app.getUser(user.getID());
        assertNull(result);
    }

    //@Test
    public void verifyLogin(){
        AppUsers app = new AppUsers();

        UserImpl user = new StudentImpl("John Doe","jd001","john","catsRkool64",
                Belt.BLACK3,null,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));

        app.addUser(user);

        Boolean actual = app.verifyPassword("john", "catsRkool64");
        assertTrue(actual);

        app.removeUser(user.getID());

    }

}
