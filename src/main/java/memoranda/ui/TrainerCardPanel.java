package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import java.io.File;

import main.java.memoranda.EventsManager;
import main.java.memoranda.EventsScheduler;
import main.java.memoranda.History;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.util.Configuration;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.Util;

/*
 * Creates an individual trainer "card" that displays information about the trainer
 * Will be displayed in TrainerProfilesPanel
 * @author Justin Oliver
 * 
 */
public class TrainerCardPanel extends JPanel {
    JButton editUser = new JButton("Edit");
    JLabel trainerName;
    JLabel trainerBelt;
    JLabel trainerInfo;
    BufferedImage trainerImage;
    Image scaledImage;
    JLabel trainerPicture;
    String name;
    String belt;
    String fact;
    
    int imageWidth=200;
    int imageHeight=200;
    


    public TrainerCardPanel(String name, String belt, String fact) {
        try {
            this.name = name;
            this.belt = belt;
            this.fact = fact;
            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    
    /*
     * Initializes the code
     * html tag is used in Jlabel to allow text to wrap
     * 
     * 
     */
    void jbInit() throws Exception {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        
        trainerName = new JLabel("Trainer Name: " +name+ "\n");
        trainerBelt = new JLabel("Belt Level: " + belt + "\n");
        trainerInfo = new JLabel("<html>About Me: " + fact +"\n</html>");

        if(name.equalsIgnoreCase("Cameron Howe")) {
            trainerImage = ImageIO.read(this.getClass().getResource("/ui/icons/howe.jpg"));
        } else if(name.equalsIgnoreCase("Joe MacMillan")) {
            trainerImage = ImageIO.read(this.getClass().getResource("/ui/icons/joe.jpg"));
        } else if(name.equalsIgnoreCase("Gordon Clark")) {
            trainerImage = ImageIO.read(this.getClass().getResource("/ui/icons/gordon.jpg"));
        } else if(name.equalsIgnoreCase("Donna Clark")) {
            trainerImage = ImageIO.read(this.getClass().getResource("/ui/icons/donna.jpg"));
        } else if(name.equalsIgnoreCase("John Bosworth")) {
            trainerImage = ImageIO.read(this.getClass().getResource("/ui/icons/john.jpg"));
        } else {
            trainerImage = ImageIO.read(this.getClass().getResource("/ui/icons/nunchuckNorris.png"));
        }
        scaledImage = trainerImage.getScaledInstance(imageWidth,imageHeight,trainerImage.SCALE_SMOOTH);
        trainerPicture = new JLabel(new ImageIcon(scaledImage));
        this.setPreferredSize(new Dimension(300, 300));
        this.setMinimumSize(new Dimension(300, 300));
        this.setMaximumSize(new Dimension(300, 300));
        this.add(trainerName);
        this.add(trainerPicture);
        this.add(trainerBelt);        
        this.add(trainerInfo);
        this.add(editUser);
        this.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.yellow));
        
        editUser.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
              editUser();
          }
        });

    }

    /*
     * Method to add edit this users card
     * Removes all items first so that when it revalidates they display in the correct order
     * repaint to clear old data and revalidate to display new
     */
    
    void editUser() {
        //TrainerCardPanel testCard7 = new TrainerCardPanel("John Bosworth", "White", "Innovation is a risk.");
        //this.add(testCard7);
        this.remove(trainerName);
        this.remove(trainerPicture);
        this.remove(trainerBelt);        
        this.remove(trainerInfo);
        this.remove(editUser);
        JOptionPane.showMessageDialog(null, "This worked");
        name = "Hello";
        trainerName = new JLabel("Trainer Name: " +name+ "\n");
        trainerBelt = new JLabel("Belt Level: " + belt + "\n");
        trainerInfo = new JLabel("<html>About Me: " + fact +"\n</html>");
        this.add(trainerName);
        this.add(trainerPicture);
        this.add(trainerBelt);        
        this.add(trainerInfo);
        this.add(editUser);
        repaint();
        revalidate();
    }



}