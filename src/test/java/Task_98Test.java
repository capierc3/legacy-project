package test.java;

import main.java.memoranda.gym.Belt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Belt enums and their values
 */
public class Task_98Test {


    /**
     * Anything that should happen before any test is ran
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * Tests that each belt is correctly assigned its value
     */
    @Test
    public void beltValuesTest(){
        for (int i = 0; i < Belt.values().length; i++) {
            Belt belt = Belt.values()[i];
            assertEquals(i,belt.getValue());
        }
    }

    /**
     * tests a the getBelt method of returning the correct enum to the inputted value.
     */
    @Test
    public void getBeltTest(){
        assertEquals(Belt.BLACK1,Belt.getBelt(11));
        assertEquals(Belt.WHITE,Belt.getBelt(0));
        assertEquals(Belt.BLUE,Belt.getBelt(4));
        assertEquals(Belt.GREEN,Belt.getBelt(6));
        assertEquals(Belt.BLACK3,Belt.getBelt(13));
    }

}
