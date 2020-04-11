package memoranda.gym;

/**
 * Interface for the Room methods
 * @author Daimi Mussey
 */
public class RoomImpl {
    private ClassList list;
    private Collection<CalendarDate> classDates;
    private int roomNum;

    /**
     * Gets a ClassList object of all the classes that are currently scheduled for the room
     * @return ClassList
     */
    public ClassList getClasses() {
        return list;
    }

    /**
     * Add a class to the room's ClassList
     * @param gymClass GymClass
     */
    public void addClass(GymClass gymClass) {
        list.addClass(gymClass);
    }

    /**
     * Getter for the room number
     * @return int
     */
    public int getRoomNum() {
        return roomNum;
    }

    /**
     * Setter for the room number
     * @param num int
     */
    public void setRoomNum(int num) {
        roomNum = num;
    }

    /**
     * Lets you know if a room is available on a given date and time.
     * @param date CalendarDate
     * @return boolean
     */
    public boolean isAvailable(CalendarDate date) {
        boolean result = false;

        if (!classDates.isEmpty()) {
            if(classDates.contains(date)) {
                result = true;
            }
        }
        return result;
    }

}
