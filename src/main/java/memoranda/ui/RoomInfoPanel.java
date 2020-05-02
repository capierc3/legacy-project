/** 
 * This class is designed to display a panel and show the appropriate info when the Room Info button is clicked. 
 * It used the resources panel as an original Source
 * 
 * 
 * 
 */
package main.java.memoranda.ui;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import main.java.memoranda.gym.RoomImpl;
import main.java.memoranda.gym.Room;
import main.java.memoranda.gym.ClassListImpl;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.date.CalendarDate;
import java.util.ArrayList;
import java.util.Collection;

public class RoomInfoPanel extends JPanel {

    TitledBorder title = BorderFactory.createTitledBorder("KAESEKUCHEN ROOMS");
    FlowLayout flowlayout = new FlowLayout();

    /**
     * Constructor for the panel.
     */
    public RoomInfoPanel () {
        try {
            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    void jbInit() throws Exception {
        this.setLayout(flowlayout);
        for (Room s : App.roomManager.getAllRooms()) {
            if (s instanceof Room) {
                this.add(new RoomCardPanel((Room) s));
            }
        }
        this.setBorder(title);
    }
}