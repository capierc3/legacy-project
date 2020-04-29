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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class XMLtest {

    private Document writeDoc;
    private GymClass writeClass;
    private GymClass readClass;
    private Builder parser;

    /**
     * sets up the needed attributes for the tests.
     */
    @Before
    public void setup() {
        CalendarDate start = new CalendarDate(29, 3, 2020, 5, 30, false);
        CalendarDate end = new CalendarDate(29, 3, 2020, 6, 30, false);
        writeClass = new GymClassImpl("Test","Public", Belt.WHITE, start, end);
        Element writeEl = writeClass.getContent();
        writeDoc = new Document(writeEl);
        parser  = new Builder();
    }

    @Test
    public void testA_Write() {
        try {
            OutputStream fileOutputStream = new FileOutputStream("test.xml");
            Serializer serializer = new Serializer(fileOutputStream, "UTF-8");
            serializer.setIndent(4);
            serializer.setMaxLength(64);
            serializer.write(writeDoc);
            serializer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testB_Read() {
        try {
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
