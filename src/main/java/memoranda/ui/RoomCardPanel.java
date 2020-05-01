package main.java.memoranda.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.Owner;
import main.java.memoranda.gym.Room;
import main.java.memoranda.util.Local;
/**
 * This class is designed to display a panel and show the appropriate
 * info when the Room Info button is clicked.
 * It used the resources panel as an original Source.
 */

public class RoomCardPanel extends JPanel {

    //JButton editUser = new JButton("Edit");
    JLabel roomNumber;
    JLabel roomPicture;
    BufferedImage roomImage;
    Image scaledImage;
    JLabel roomAvailable;
    boolean isAvailable = true;
    int number;
    File pic;
    Room room;

    int imageWidth = 200;
    int imageHeight = 200;


    /**
     * Class constructor.
     * @param Room room
     */
    public RoomCardPanel(Room room) {
        try {
            this.room = room;
            this.number = room.getRoomNum();
            this.isAvailable = room.isAvailable(new CalendarDate());
            this.pic = room.getPic();
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

        roomNumber = new JLabel("Room Number: " + number + "\n");
        roomAvailable = new JLabel("Room Available: " + isAvailable + "\n");
        roomImage = ImageIO.read(pic);
        scaledImage = roomImage.getScaledInstance(imageWidth,
                imageHeight, Image.SCALE_SMOOTH);
        roomPicture = new JLabel(new ImageIcon(scaledImage));
        this.setPreferredSize(new Dimension(300, 300));
        this.setMinimumSize(new Dimension(300, 300));
        this.setMaximumSize(new Dimension(300, 300));
        this.add(roomNumber);
        this.add(roomPicture);
        this.add(roomAvailable);
        this.setBorder(BorderFactory.createEtchedBorder(Color.blue, Color.yellow));
    }

}
