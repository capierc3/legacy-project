/*
 * @author Justin
 * 
 * This is a class to help with testing It is designed to be used as a template
 * for either whitebox or blackbox testing To use, create a copy of this file
 * and name accordingly. Modify/add any tests you need Please do not modify this
 * sample test file, it is to be used as a template All possible imports are
 * currently included at time this sample was created, however as this project
 * develops, new directories may be created and this list will need to be
 * updated.
 * 
 * To use junit in Gradle, run "gradle build" and "gradle test" Gradle Junit
 * test reports should populate in Kaesekuchen/build/reports/tests/test...
 * 
 * To use jacoco for code coverage report, run "gradle jacocoTestReport" Gradle
 * jacoco test report should populate in
 * Kaesekuchen/build/reports/jacoco/test/html...
 * 
 * 
 * 
 * package test.java;
 * 
 * import static org.junit.Assert.*;
 * 
 * import main.java.memoranda.*; import main.java.memoranda.date.*; import
 * main.java.memoranda.ui.*; import main.java.memoranda.ui.htmleditor.*; import
 * main.java.memoranda.ui.htmleditor.filechooser.*; import
 * main.java.memoranda.ui.htmleditor.util.*; import
 * main.java.memoranda.ui.table.*; import main.java.memoranda.ui.treetable.*;
 * import main.java.memoranda.util.*;
 * 
 * 
 * 
 * import org.junit.After; import org.junit.AfterClass; import org.junit.Before;
 * import org.junit.BeforeClass; import org.junit.Test;
 * 
 * public class sampleTest {
 * 
 * 
 * Declare any needed variables here
 * 
 * 
 * 
 * // Variable 1; // Variable 2; CalendarDate tempDate = new CalendarDate();
 * 
 * 
 * Any actions that need to happen before any test methods in the class are ran
 * 
 * @BeforeClass public static void setUpBeforeClass() throws Exception { }
 * 
 * 
 * 
 * Any actions taht need to run after all the tests in this class are ran
 * 
 * @AfterClass public static void tearDownAfterClass() throws Exception { }
 * 
 * 
 * 
 * Anything that should happen before any test is ran
 * 
 * @Before public void setUp() throws Exception { }
 * 
 * 
 * 
 * Anything that needs to happen after a test is ran and before the next test
 * such as resetting a variable
 * 
 * @After public void tearDown() throws Exception { }
 * 
 * 
 * Create the tests to use below. A few default samples are provided with info
 * from our cart projects as a reminder on how they are used
 * 
 * 
 * @Test public void ensuringNotNull() { assertNotNull(tempDate); }
 * 
 * 
 * @Test public void test() { fail("Not yet implemented"); }
 * 
 * @Test public void defaultTax() { double amount =
 * normalCart.getTax(normalCartSubtotal, "XX"); assertEquals(0, amount, .01); }
 * 
 * 
 * @Test public void addItemTest() { assertNotNull(tempDate); }
 * 
 * 
 * @Test public void removeItemTest() { Dairy item = new Dairy(); boolean
 * removeTest = singleItemCart.RemoveItem(item); assertTrue(removeTest);
 * 
 * @Test public void removeItemTestFalse() { // Item in cart should be dairy,
 * this will show as produce and return false Produce item = new Produce();
 * boolean removeTest = singleItemCart.RemoveItem(item);
 * assertFalse(removeTest); }
 * 
 * // This is meant to test if an exception is thrown
 * 
 * @Test(expected = UnderAgeException.class) public void nodeAndEdge2() throws
 * UnderAgeException { double amount = underageCart.calcCost();
 * assertEquals(normalCartExpected, amount, .01); }
 * 
 * 
 * }
 * 
 * 
 * 
 * 
 * 
 */