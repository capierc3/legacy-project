/**
 * This class is designed to display a panel and show the appropriate info when the Student List button is clicked. 
 * It used the resources panel as an original Source
 * 
 * 
 * 
 */
package main.java.memoranda.ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.memoranda.CurrentProject;
import main.java.memoranda.Resource;
import main.java.memoranda.gym.*;
import main.java.memoranda.util.AppList;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.MimeType;
import main.java.memoranda.util.MimeTypesList;
import main.java.memoranda.util.Util;

import java.io.*;
import java.util.ArrayList;

public class StudentListPanel extends JPanel {

    TitledBorder title = BorderFactory.createTitledBorder("KAESEKUCHEN TRAINERS");
    FlowLayout flowlayout = new FlowLayout();
    JButton newUser = new JButton("New Student");

    public StudentListPanel() {
        try {
            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    void jbInit() throws Exception {
        this.setLayout(flowlayout);
        for (User s:App.appUsers.getAllUsers()){
            if (s instanceof Student){
                this.add(new StudentCardPanel((Student) s));
            }
        }
        if (App.appUsers.getActiveUser() instanceof Owner) {
            this.add(newUser);
        }
        this.setBorder(title);
        newUser.addActionListener(e -> addUser());
    }
    /*
     * Method to add new user to system and card to this panel
     */

    void addUser() {
        String name = "";
        Belt belt = Belt.WHITE;

        // Creates and renders an edit box
        StudentDialog dlg = new StudentDialog(App.getFrame(),
                Local.getString("Add New Student"), name, belt);
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x,
                (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED) {
            return;
        }

        // Set new values based on results from edit box
        name = dlg.studentNameText.getText();
        belt = Belt.getBelt(dlg.beltBox.getSelectedIndex());

        // Add components to new card
        JOptionPane.showMessageDialog(null, "New User Successfully Created!");
        Student student = new StudentImpl(name,name,name,"Password", belt,
                null,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        App.appUsers.addUser(student);
        StudentCardPanel newstudent = new StudentCardPanel(student);
        // Remove and read button so it appears in correct spot
        this.remove(this.newUser);
        this.add(newstudent);
        this.add(this.newUser);
        repaint();
        revalidate();
    }
}