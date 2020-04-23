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
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import main.java.memoranda.gym.*;
import main.java.memoranda.util.Local;

public class TrainerProfilesPanel extends JPanel {
    TitledBorder title = BorderFactory.createTitledBorder("KAESEKUCHEN TRAINERS");
    FlowLayout flowlayout = new FlowLayout();
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
        for (User t:App.appUsers.getAllUsers()){
            if (t instanceof Trainer){
                this.add(new TrainerCardPanel((Trainer) t));
            }
        }
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
        Belt belt = Belt.WHITE;
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
        belt = Belt.getBelt(dlg.beltBox.getSelectedIndex());
        fact = dlg.trainerFactText.getText();
  
        // Add components to new card
        JOptionPane.showMessageDialog(null, "New User Successfully Created!");
        Trainer trainer = new TrainerImpl(name,name,name,"Password", belt,
                null,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        trainer.setDescription(fact);
        App.appUsers.addUser(trainer);
        TrainerCardPanel newTrainer = new TrainerCardPanel(trainer);
        // Remove and read button so it appears in correct spot
        this.remove(this.newUser);
        this.add(newTrainer);
        this.add(this.newUser);
        repaint();
        revalidate();
    }








     
}