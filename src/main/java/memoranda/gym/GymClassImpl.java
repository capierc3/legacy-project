package main.java.memoranda.gym;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;

import java.io.File;
import java.util.ArrayList;

/**
 * Class that implements the GymClass Interface
 *
 * BlackBox notes:
 * All main methods should work that are found in the GymClass interface. Currently as of 4/11 the add/get students and
 * add/get trainers don't work since those codes are not available yet. also get and set room don't work for the same reason.
 * Static Analysis done by Justin.
 * Blackbox and Whitebox testing done in Task76Test.java
 *
 * getClassLength() does not work for overnight classes, PM->AM classes.
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
    public GymClassImpl(String name,String type,Belt rank){
        this(name,type);
        setRank(rank);
    }

    public GymClassImpl(String name, String type, Belt rank, CalendarDate startDate, CalendarDate endDate){
        this(name,type,rank);
        setStartDate(startDate);
        setEndDate(endDate);
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
    public Belt getRank() {
        return Belt.getBelt(Integer.parseInt(el.getAttributeValue("Rank")));
    }

    @Override
    public void setRank(Belt rank) {
        setAttr("Rank",String.valueOf(rank.getValue()));
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
        Element e = el.getFirstChildElement("Room");
        if (e != null) {
            return attrToRoom((Element) e.getChild(0));
        }
        return null;
    }

    @Override
    public void setRoom(Room room) {
        Element roomEl = el.getFirstChildElement("Room");
        if (roomEl == null) {
            Element e = new Element("Room");
            e.addAttribute(new Attribute("RoomNum",String.valueOf(room.getRoomNum())));
            e.appendChild(room.getContent().copy());
            el.appendChild(e);
        } else {
            Element e = el.getFirstChildElement("Room");
            e.removeChildren();
            e.appendChild(room.getContent().copy());
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
        String startDate = Util.getTimeStamp(getStartDate());
        String endDate = Util.getTimeStamp(getEndDate());
        int endHour = Integer.parseInt(endDate.split(":")[0]);
        int startHour = Integer.parseInt(startDate.split(":")[0]);
        int endMin, startMin;
        if (!getEndDate().isAM()){
            if (endHour!=12) {
                endHour += 12;
            }
            endMin = Integer.parseInt(endDate.split(":")[1].replace("_PM",""));
        } else {
            endMin = Integer.parseInt(endDate.split(":")[1].replace("_AM",""));
        }
        if (!getStartDate().isAM()){
            if (startHour!=12) {
                startHour += 12;
            }
            startMin = Integer.parseInt(startDate.split(":")[1].replace("_PM",""));
        } else {
            startMin = Integer.parseInt(startDate.split(":")[1].replace("_AM", ""));
        }
        int hourLength = endHour-startHour;
        int minLength;
        if (startMin>endMin){
            hourLength--;
            minLength = (60-startMin)+endMin;
        } else {
            minLength = endMin-startMin;
        }

        return (hourLength*60)+minLength;
    }

    @Override
    public Trainer getTrainer() {
        return elmToTrainer(el.getFirstChildElement("Trainer"));
    }

    @Override
    public void addTrainer(Trainer trainer) {
        el.appendChild(trainer.getContent().copy());
    }

    //Todo: get all student elements and return a class list
    @Override
    public UserList getStudents() {
        return null;
    }

    @Override
    public void addStudent(Student student) {
        Element element = new Element("Students");
        element.addAttribute(new Attribute("Id",student.getID()));
        element.appendChild(student.getContent().copy());
        el.appendChild(element);
    }

    public void removeStudent(Student student){
        for (int i = 0; i < el.getChildElements("Students").size(); i++) {
            Element element = el.getChildElements("Students").get(i);
            if (element.getAttributeValue("Id").equalsIgnoreCase(student.getID())){
                element.detach();
                break;
            }
        }
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
    public void setStartDate(CalendarDate date) {
        setAttr("StartDate",date.toString());
    }

    @Override
    public void setEndDate(CalendarDate date){
        setAttr("EndDate",date.toString());
    }

    @Override
    public CalendarDate getStartDate() {
        return new CalendarDate(el.getAttributeValue("StartDate"));
    }

    @Override
    public CalendarDate getEndDate(){
        return new CalendarDate(el.getAttributeValue("EndDate"));
    }

    @Override
    public String getStartTime() {
        return Util.getTimeStamp(getStartDate());
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
        if (room == null){
            return null;
        }
        return new RoomImpl(Integer.parseInt(room.getAttributeValue("RoomNumber")),
                new ClassListImpl(new ArrayList<>()),
                new ArrayList<>());
    }
    private Trainer elmToTrainer(Element el){
        if (el!=null) {
            return new TrainerImpl(el.getAttributeValue("Name"),
                    el.getAttributeValue("Id"),
                    el.getAttributeValue("UserName"),
                    el.getAttributeValue("Password"),
                    Belt.getBelt(Integer.parseInt(el.getAttributeValue("Rank"))),
                    new File(""),
                    new ArrayList<>(),
                    new ClassListImpl(new ArrayList<>()));
        } else {
            return null;
        }
    }
    //Todo: need to turn elements into a ClassList
//    private UserList elmsToStudentList(Elements els){
//        return null;
//    }

}
