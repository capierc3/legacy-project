/* 
 * @author Justin 
 * 
 * Spotbugs and checkstyle ran on all 3 classes for this user story
 * Including TrainerProfilePanel, TrainerDialog, and TrainerCardPanel
 * No Spotbugs present
 * Most checkstyle issues fixed, still a couple present
 * 
 * 
 */
package test.java;

import static org.junit.Assert.*;
import main.java.memoranda.ui.*;
import org.junit.Test;
import main.java.memoranda.ui.*;

public class US_69Test {
    String name = "Bruce Willis";
    String belt = "Dark Black";
    String fact = "I have lots of hair";
    TrainerCardPanel card = new TrainerCardPanel(name, belt, fact);
    
    @Test
    public void testCardCreation() {
        assertEquals(name, card.getTrainerName());
        assertEquals(belt, card.getBelt());
        assertEquals(fact, card.getFact());
    }
	
}





