package main.java.memoranda.ui;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.*;
import nu.xom.*;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class RoomManager {

    private HashMap<Integer,Room> rooms;
    private Element element;

    RoomManager() {
        element = new Element("Rooms");
        rooms = new HashMap<>();
        try {
            loadRooms();
        } catch (IOException | ParsingException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void addRoom(Room room) {
        if (!rooms.containsKey(room.getRoomNum())) {
            rooms.put(room.getRoomNum(), room);
            Element e = new Element("Room");
            e.appendChild(room.getContent().copy());
            element.appendChild(e);
        }
    }

    public Room getRoom(int roomNum) {
        return rooms.get(roomNum);
    }

    public Collection<Room> getAllRooms() {
        return rooms.values();
    }

    public void saveRooms() {
        try {
            ObjectSerializer.serializeElement(element,"server/rooms.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRooms() throws IOException, ParsingException, URISyntaxException {
        Builder parser;
        File roomFile = new File("server/rooms.xml");
        if (roomFile.exists()) {
            System.out.println("Rooms Loaded");
            parser = new Builder();
            InputStream fileInputStream = new FileInputStream(roomFile);
            Document readDoc = parser.build(fileInputStream);
            Elements roomElements = readDoc.getRootElement().getChildElements("Room");
            for (int i = 0; i < roomElements.size(); i++) {
                if (roomElements.get(i) != null) {
                    addRoom(Room.elmToRoom(roomElements.get(i).getFirstChildElement("Room")));
                }
            }
        }
//        else {
//            System.out.println("HardCoded");
//            Collection<GymClass> gymClasses = new ArrayList<>();
//            Collection<CalendarDate> calendarDates = new ArrayList<>();
//            ClassList list = new ClassListImpl(gymClasses);
//            Room room1 = new RoomImpl(403, list, calendarDates);
//            File file1 = new File(this.getClass().getResource("/ui/icons/redRoom.jpeg").toURI());
//            room1.setPic(file1);
//            addRoom(room1);
//            Room room2 = new RoomImpl(202, list, calendarDates);
//            File file2 = new File(this.getClass().getResource("/ui/icons/ringsRoom.jpeg").toURI());
//            room2.setPic(file2);
//            addRoom(room2);
//            Room room3 = new RoomImpl(303, list, calendarDates);
//            File file3 = new File(this.getClass().getResource("/ui/icons/whiteRoom.jpeg").toURI());
//            room3.setPic(file3);
//            addRoom(room3);
//            Room room4 = new RoomImpl(500, list, calendarDates);
//            File file4 = new File(this.getClass().getResource("/ui/icons/blueRoom.jpeg").toURI());
//            room4.setPic(file4);
//            addRoom(room4);
//        }
    }

}
