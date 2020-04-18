/* 
 * This class is designed to display a panel and show the appropriate info when the Trainer Profiles button is clicked. 
 * It used the resources panel as an original Source
 * 
 * @author Justin Oliver
 * 
 */
package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.memoranda.CurrentProject;
import main.java.memoranda.Resource;
import main.java.memoranda.util.AppList;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.MimeType;
import main.java.memoranda.util.MimeTypesList;
import main.java.memoranda.util.Util;

import java.io.*;


public class TrainerProfilesPanel extends JPanel {
    TitledBorder title = BorderFactory.createTitledBorder("KAESEKUCHEN TRAINERS");
    FlowLayout flowlayout = new FlowLayout();
    TrainerCardPanel testCard = new TrainerCardPanel("Justin Oliver", "Blue", "I use ketchup on everything!");
    TrainerCardPanel testCard2 = new TrainerCardPanel("Cameron Howe", "Green Stripe", "What the hell is this Yahoo");
    TrainerCardPanel testCard3 = new TrainerCardPanel("Joe MacMillan", "Orange", "The thing that gets you to the thing");
    TrainerCardPanel testCard4 = new TrainerCardPanel("Gordon Clark", "Black2", "We had a problem. Now we have a product.");
    TrainerCardPanel testCard5 = new TrainerCardPanel("Donna Clark", "Black3", "Software comes and goes. Hardware is forever.");
    TrainerCardPanel testCard6 = new TrainerCardPanel("John Bosworth", "White", "Innovation is a risk.");
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
        this.add(newUser);
        this.setBorder(title);
        
        newUser.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              addUser();
          }
        });
        
        /*
         * PopupListener ppListener = new PopupListener();
         * newUser.addMouseListener(ppListener);
         */
         
    }
    /*
     * Method to add new user to system and card to this panel
     */
    
    void addUser() {
        TrainerCardPanel testCard7 = new TrainerCardPanel("John Bosworth", "White", "Innovation is a risk.");
        this.add(testCard7);
        JOptionPane.showMessageDialog(null, "This worked");
        this.remove(newUser);
        this.add(newUser);
        revalidate();
    }








     
}