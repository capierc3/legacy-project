/* 
 * This class is designed to display a panel and show the appropriate info 
 * when the Trainer Profiles button is clicked. 
 * It used the resources panel as an original Source
 * 
 * @author Justin Oliver
 * 
 * 
 */
package main.java.memoranda.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import main.java.memoranda.gym.Owner;
import main.java.memoranda.util.Local;





public class TrainerProfilesPanel extends JPanel {
    TitledBorder title = BorderFactory.createTitledBorder("KAESEKUCHEN TRAINERS");
    FlowLayout flowlayout = new FlowLayout();
    TrainerCardPanel testCard = new TrainerCardPanel
            ("Justin Oliver", "Blue", "I use ketchup on everything!");
    TrainerCardPanel testCard2 = new TrainerCardPanel
            ("Cameron Howe", "Green Stripe", "What the hell is this Yahoo");
    TrainerCardPanel testCard3 = new TrainerCardPanel
            ("Joe MacMillan", "Orange", "The thing that gets you to the thing");
    TrainerCardPanel testCard4 = new TrainerCardPanel
            ("Gordon Clark", "Black2", "We had a problem. Now we have a product.");
    TrainerCardPanel testCard5 = new TrainerCardPanel
            ("Donna Clark", "Black3", "Software comes and goes. Hardware is forever.");
    TrainerCardPanel testCard6 = new TrainerCardPanel
            ("John Bosworth", "White", "Innovation is a risk.");
    JButton newUser = new JButton("New Trainer");

    
    

    public TrainerProfilesPanel() {
        try {
            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    void jbInit() throws Exception {
        
        this.setLayout(flowlayout);

        this.add(testCard); 
        this.add(testCard2); 
        this.add(testCard3); 
        this.add(testCard4); 
        this.add(testCard5); 
        this.add(testCard6);
        if (App.appUsers.getActiveUser() instanceof Owner) {
            this.add(newUser);
        }
        this.setBorder(title);
        
        newUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });
        
         
    }
    /*
     * Method to add new user to system and card to this panel
     */
    
    void addUser() {
        String name = "";
        String belt = "";
        String fact = "";

        
        // Creates and renders an edit box
        TrainerDialog dlg = new TrainerDialog(App.getFrame(), 
                Local.getString("Add New Trainer"), name, belt, fact);
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, 
                (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED) {
            return;
        }
        
        // Set new values based on results from edit box
        name = dlg.trainerNameText.getText();
        belt = dlg.trainerBeltText.getText();
        fact = dlg.trainerFactText.getText();
  
        // Add components to new card
        JOptionPane.showMessageDialog(null, "New User Successfully Created!");
        TrainerCardPanel newTrainer = new TrainerCardPanel(name, belt, fact);
        // Remove and read button so it appears in correct spot
        this.remove(this.newUser);
        this.add(newTrainer);
        this.add(this.newUser);
        repaint();
        revalidate();
    }








     
}