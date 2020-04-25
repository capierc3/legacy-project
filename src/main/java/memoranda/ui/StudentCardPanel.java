package main.java.memoranda.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.Owner;
import main.java.memoranda.gym.Student;
import main.java.memoranda.util.Local;


/**
 * This class is designed to display a panel and show the appropriate
 * info when the Student List button is clicked.
 * It used the resources panel as an original Source.
 */
public class StudentCardPanel extends JPanel {

    JButton editUser = new JButton("Edit");
    JLabel studentName;
    JLabel studentBelt;
    BufferedImage studentImage;
    Image scaledImage;
    JLabel studentPicture;
    String name;
    Belt belt;
    File pic;
    Student student;

    int imageWidth = 200;
    int imageHeight = 200;


    /**
     * Class constructor.
     * @param student student
     */
    public StudentCardPanel(Student student) {
        try {
            this.student = student;
            this.name = student.getName();
            this.belt = student.getBelt();
            this.pic = student.getPic();
            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    /**
     * Initializes the code.
     * html tag is used in Jlabel to allow text to wrap.
     * @throws IOException e
     */
    void jbInit() throws IOException {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        studentName = new JLabel("student Name: " + name + "\n");
        studentBelt = new JLabel("Belt Level: " + belt + "\n");
        studentImage = ImageIO.read(pic);
        scaledImage = studentImage.getScaledInstance(imageWidth,
                imageHeight, Image.SCALE_SMOOTH);
        studentPicture = new JLabel(new ImageIcon(scaledImage));
        this.setPreferredSize(new Dimension(300, 300));
        this.setMinimumSize(new Dimension(300, 300));
        this.setMaximumSize(new Dimension(300, 300));
        this.add(studentName);
        this.add(studentPicture);
        this.add(studentBelt);
        if (App.appUsers.getActiveUser() != null
                && (App.appUsers.getActiveUser() instanceof Owner
                || App.appUsers.getActiveUser().equals(student))) {
            this.add(editUser);
        }
        this.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.yellow));

        editUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editUser(e);
            }
        });

    }

    /**
     * Method to add edit this users card.
     * Removes all items first so that when it revalidates they display in the correct order
     * repaint to clear old data and revalidate to display new
     * @param e Action Event from button click
     */
    void editUser(ActionEvent e) {

        // Creates and renders an edit box
        StudentDialog dlg = new StudentDialog(App.getFrame(),
                Local.getString("Edit student"), name, belt);
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2
                + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.cancelled) {
            return;
        }

        // Set new values based on results from edit box
        this.name = dlg.studentNameText.getText();
        this.belt = (Belt) dlg.beltBox.getSelectedItem();

        // Removes old items and writes the new items
        this.remove(studentName);
        this.remove(studentPicture);
        this.remove(studentBelt);
        this.remove(editUser);
        JOptionPane.showMessageDialog(null, "User Details Updated!");
        studentName = new JLabel("student Name: " + name + "\n");
        studentBelt = new JLabel("Belt Level: " + belt + "\n");
        this.add(studentName);
        this.add(studentPicture);
        this.add(studentBelt);
        this.add(editUser);
        repaint();
        revalidate();
    }

    /**
     * Gets student's name.
     * @return String student's name
     */
    public String getstudentName() {
        return this.name;
    }

    /**
     * Gets belt rank.
     * @return Belt belt rank
     */
    public Belt getBelt() {
        return this.belt;
    }




}