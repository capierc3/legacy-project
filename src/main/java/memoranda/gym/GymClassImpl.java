package main.java.memoranda.gym;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;

/**
 * Class that implements the GymClass Interface
 *
 * BlackBox notes:
 * All main methods should work that are found in the GymClass interface. Currently as of 4/11 the add/get students and
 * add/get trainers don't work since those codes are not available yet. also get and set room don't work for the same reason.
 *
 * @author Chase
 */
public class GymClassImpl implements GymClass {

    private Element el;

    /**
     * Constructor with only name and type, ideally used for personal training spots.
     * @param name String
     * @param type String
     */
    public GymClassImpl(String name,String type){
        el = new Element("Class");
        setName(name);
        setClassType(type);
        setID(Util.generateId());
    }

    /**
     * Constructor with a name, type and rank, ideally used for the gym classes that need a rank.
     * @param name String
     * @param type String
     * @param rank String
     */
    public GymClassImpl(String name,String type,String rank){
        this(name,type);
        setRank(rank);
    }

    @Override
    public String getName() {
        return el.getAttributeValue("Name");
    }

    @Override
    public void setName(String name) {
        setAttr("Name",name);
    }

    @Override
    public String getRank() {
        return el.getAttributeValue("Rank");
    }

    @Override
    public void setRank(String rank) {
        setAttr("Rank",rank);
    }

    @Override
    public int getMaxSize() {
        return Integer.parseInt(el.getAttributeValue("MaxSize"));
    }

    @Override
    public void setSize(int size) {
        setAttr("MaxSize",Integer.toString(size));
    }

    @Override
    public Room getRoom() {
        return attrToRoom(el.getFirstChildElement("Room"));
    }

    @Override
    public void setRoom(Room room) {
        Element roomEl = el.getFirstChildElement("Room");
        if (roomEl == null) {
            el.appendChild(roomToElm(room));
        } else {
            el.replaceChild(roomEl,roomToElm(room));
        }
    }

    @Override
    public String getID() {
        return el.getAttributeValue("ID");
    }

    @Override
    public void setID(String id) {
        setAttr("ID",id);
    }

    @Override
    public int getClassLength() {
        return Integer.parseInt(el.getAttributeValue("Length"));
    }

    @Override
    public void setClassLength(int length) {
        setAttr("Length",Integer.toString(length));
    }

    @Override
    public UserList getTrainers() {
        return elmsToUserList(el.getChildElements("Trainers"));
    }

    @Override
    public void addTrainer(Trainer trainer) {
        el.appendChild(trainerToElm(trainer));
    }

    @Override
    public UserList getStudents() {
        return elmsToUserList(el.getChildElements("Students"));
    }

    @Override
    public void addStudent(Student student) {
        el.appendChild(studentToElm(student));
    }

    @Override
    public int getSize() {
        return el.getChildElements("Students").size();
    }

    @Override
    public boolean isFull() {
        return getSize() >= getMaxSize();
    }

    @Override
    public String getClassType() {
        return el.getAttributeValue("Type");
    }

    @Override
    public void setClassType(String type) {
        setAttr("Type",type);
    }

    @Override
    public void setDate(CalendarDate date) {
        setAttr("StartTime",date.toString());
    }
    @Override
    public CalendarDate getDate() {
        return new CalendarDate(el.getAttributeValue("StartTime"));
    }

    @Override
    public String getStartTime() {
        return Util.getTimeStamp(getDate());
    }

    @Override
    public Element getContent() {
        return el;
    }

    private void setAttr(String a, String value) {
        Attribute attr = el.getAttribute(a);
        if (attr == null)
            el.addAttribute(new Attribute(a, value));
        else
            attr.setValue(value);
    }




    //TODO Need room class to write methods or should be public static methods in RoomImpl
    private Room attrToRoom(Element room){
        return null;
    }
    private Element roomToElm(Room room){
        return null;
    }
    private UserList elmsToUserList(Elements els){
        return null;
    }
    private Element trainerToElm(Trainer trainer){
        return null;
    }
    private Element studentToElm(Student student){
        return null;
    }
}
