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

import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.ClassListImpl;
import main.java.memoranda.gym.Trainer;
import main.java.memoranda.gym.TrainerImpl;
import main.java.memoranda.ui.*;
import org.junit.Before;
import org.junit.Test;


import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class US_69Test {
    String name = "Bruce Willis";
    String belt = Belt.BLACK3.toString();
    String fact = "I have lots of hair";
    Trainer bruce;
    TrainerCardPanel card;

    @Before
    public void setUp(){

        try {
            bruce = new TrainerImpl(name,"",name,"", Belt.valueOf(belt),
                    new File(this.getClass().getResource("/ui/icons/nunchuckNorris.png").toURI()),
                    new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
            bruce.setDescription(fact);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        card = new TrainerCardPanel(bruce);
    }
    
    @Test
    public void testCardCreation() {
        assertEquals(name, card.getTrainerName());
        assertEquals(belt, card.getBelt());
        assertEquals(fact, card.getFact());
    }
	
}





