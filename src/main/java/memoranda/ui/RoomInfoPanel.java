/* 
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
    JButton newUser = new JButton("New Student");

    Collection<Room> rooms = new ArrayList<>();
    Collection<GymClass> gymClasses = new ArrayList<>();
    Collection<CalendarDate> calendarDates = new ArrayList<>();
    ClassList list = new ClassListImpl(gymClasses);


    /**
     * Constructor for the panel.
     */
    public RoomInfoPanel () {
        try {
            Room room1 = new RoomImpl(403, list, calendarDates);
            File file1 = new File(this.getClass().getResource("/ui/icons/redRoom.jpeg").toURI());
            room1.setPic(file1);
            rooms.add(room1);
            Room room2 = new RoomImpl(202, list, calendarDates);
            File file2 = new File(this.getClass().getResource("/ui/icons/ringsRoom.jpeg").toURI());
            room2.setPic(file2);
            rooms.add(room2);
            Room room3 = new RoomImpl(303, list, calendarDates);
            File file3 = new File(this.getClass().getResource("/ui/icons/whiteRoom.jpeg").toURI());
            room3.setPic(file3);
            rooms.add(room3);
            Room room4 = new RoomImpl(500, list, calendarDates);
            File file4 = new File(this.getClass().getResource("/ui/icons/blueRoom.jpeg").toURI());
            room4.setPic(file4);
            rooms.add(room4);
            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    void jbInit() throws Exception {
        this.setLayout(flowlayout);
        for (Room s : rooms) {
            if (s instanceof Room) {
                this.add(new RoomCardPanel((Room) s));
            }
        }
        this.setBorder(title);
    }

}