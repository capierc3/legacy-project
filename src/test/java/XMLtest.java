package test.java;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.GymClassImpl;
import nu.xom.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;

/**
 * Test that shows how the XOM api can save and load a Element object.
 *
 * Runs in a set order of testA then testB.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class XMLtest {

    private GymClass writeClass;
    private GymClass readClass;

    /**
     * Creates a gymClass object to be used in the tests
     */
    @Before
    public void setup() {
        CalendarDate start = new CalendarDate(29, 3, 2020, 5, 30, false);
        CalendarDate end = new CalendarDate(29, 3, 2020, 6, 30, false);
        writeClass = new GymClassImpl("Test","Public", Belt.WHITE, start, end);
    }

    /**
     * First creates a XOM Document object from the element in the gym class object.
     * The creates an output stream with a new File called test.xml.
     * Then it creates the serializer from XOM and sets the proper indents and line length.
     * finally it writes the the XOM document to disk.
     */
    @Test
    public void testA_Write() {
        Document writeDoc = new Document(writeClass.getContent());
        try {
            OutputStream fileOutputStream = new FileOutputStream("server/test.xml");
            Serializer serializer = new Serializer(fileOutputStream, "UTF-8");
            serializer.setIndent(4);
            serializer.setMaxLength(64);
            serializer.write(writeDoc);
            serializer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * First a XOM Builder object is created then it sets up the input stream to the created file.
     * Then uses the builder to parse it to a new XOM Document. Then uses a elmTO method to create a new object.
     * Finally it tests that the read object matches the one that was written.
     */
    @Test
    public void testB_Read() {
        try {
            Builder parser = new Builder();
            InputStream fileInputStream = new FileInputStream("test.xml");
            Document readDoc = parser.build(fileInputStream);
            readClass = GymClass.elmToGymClass(readDoc.getRootElement());
        } catch (ParsingException | IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(writeClass.getName(),readClass.getName());
        Assert.assertEquals(writeClass.getClassType(),readClass.getClassType());
        Assert.assertEquals(writeClass.getRank(),readClass.getRank());
        Assert.assertTrue(writeClass.getStartDate().equals(readClass.getStartDate()));
        Assert.assertTrue(writeClass.getEndDate().equals(readClass.getEndDate()));
    }

}
