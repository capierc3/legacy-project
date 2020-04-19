package main.java.memoranda.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.java.memoranda.util.Local;


/**
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
    
    int imageWidth = 200;
    int imageHeight = 200;


    /**
     * Class constructor
     * @param name Trainer's name
     * @param belt Trainer's belt rank
     * @param fact A fact about the trainer
     */
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

    /**
     * Initializes the code
     * html tag is used in Jlabel to allow text to wrap
     *
     * @throws Exception
     */
    void jbInit() throws Exception {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        
        trainerName = new JLabel("Trainer Name: " + name + "\n");
        trainerBelt = new JLabel("Belt Level: " + belt + "\n");
        trainerInfo = new JLabel("<html>About Me: " + fact + "\n</html>");

        if (name.equalsIgnoreCase("Cameron Howe")) {
            trainerImage = ImageIO.read(this.getClass().getResource("/ui/icons/howe.jpg"));
        } else if (name.equalsIgnoreCase("Joe MacMillan")) {
            trainerImage = ImageIO.read(this.getClass().getResource("/ui/icons/joe.jpg"));
        } else if (name.equalsIgnoreCase("Gordon Clark")) {
            trainerImage = ImageIO.read(this.getClass().getResource("/ui/icons/gordon.jpg"));
        } else if (name.equalsIgnoreCase("Donna Clark")) {
            trainerImage = ImageIO.read(this.getClass().getResource("/ui/icons/donna.jpg"));
        } else if (name.equalsIgnoreCase("John Bosworth")) {
            trainerImage = ImageIO.read(this.getClass().getResource("/ui/icons/john.jpg"));
        } else {
            trainerImage = ImageIO.read(this.getClass()
                    .getResource("/ui/icons/nunchuckNorris.png"));
        }
        scaledImage = trainerImage.getScaledInstance(imageWidth,
                imageHeight,trainerImage.SCALE_SMOOTH);
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
        
        editUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editUser(e);
            }
        });

    }

    /**
     * Method to add edit this users card
     * Removes all items first so that when it revalidates they display in the correct order
     * repaint to clear old data and revalidate to display new
     * @param e Action Event from button click
     */
    void editUser(ActionEvent e) {
        
        // Creates and renders an edit box
        TrainerDialog dlg = new TrainerDialog(App.getFrame(), 
                Local.getString("Edit Trainer"), name, belt, fact);
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 
                + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED) {
            return;
        }
        
        // Set new values based on results from edit box
        this.name = dlg.trainerNameText.getText();
        this.belt = dlg.trainerBeltText.getText();
        this.fact = dlg.trainerFactText.getText();
  
        // Removes old items and writes the new items
        this.remove(trainerName);
        this.remove(trainerPicture);
        this.remove(trainerBelt);        
        this.remove(trainerInfo);
        this.remove(editUser);
        JOptionPane.showMessageDialog(null, "User Details Updated!");
        trainerName = new JLabel("Trainer Name: " + name + "\n");
        trainerBelt = new JLabel("Belt Level: " + belt + "\n");
        trainerInfo = new JLabel("<html>About Me: " + fact + "\n </html>");
        this.add(trainerName);
        this.add(trainerPicture);
        this.add(trainerBelt);        
        this.add(trainerInfo);
        this.add(editUser);
        repaint();
        revalidate();
    }

    /**
     * Gets trainer's name
     * @return String trainer's name
     */
    public String getTrainerName() {
        return this.name;
    }

    /**
     * Gets belt rank
     * @return String belt rank
     */
    public String getBelt() {
        return this.belt;
    }

    /**
     * Gets fact
     * @return String fact
     */
    public String getFact() {
        return this.fact;
    }

}