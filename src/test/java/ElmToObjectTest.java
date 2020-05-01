package test.java;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Tests the elmTo[Object] methods of the main gym object classes.
 */
public class ElmToObjectTest {

    private AppUsers users;
    private ClassList classList;
    private GymClass gymClass;
    private Owner owner;
    private Room room;
    private Student student;
    private Trainer trainer;

    /**
     * Sets up the needed variables.
     */
    @Before
    public void setUp() {
        users = new AppUsers();
        classList = new ClassListImpl(new ArrayList<>());
        CalendarDate start = new CalendarDate(29,4,2020,5,30,false);
        CalendarDate end = new CalendarDate(29,4,2020,6,30,false);
        ArrayList<CalendarDate> dates = new ArrayList<>();
        dates.add(start);
        dates.add(end);
        gymClass = new GymClassImpl("Test","Public",Belt.WHITE,start,end);
        classList.addClass(gymClass);
        gymClass = new GymClassImpl("Test2","Public",Belt.WHITE,start,end);
        classList.addClass(gymClass);
        owner = new OwnerImpl("owner","owner","owner","owner",
                Belt.BLACK3,null,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        trainer = new TrainerImpl("trainer","trainer","trainer","trainer",
                Belt.BLACK3,null,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        trainer.setClassList(classList);
        trainer.setAvailability(dates);
        student = new StudentImpl("student","student","student","student",
                Belt.BLACK3,null,new ArrayList<>(),new ClassListImpl(new ArrayList<>()));
        student.setClassList(classList);
        room = new RoomImpl(1,classList,dates);
        gymClass.setRoom(room);
        gymClass.addTrainer(trainer);
        gymClass.setSize(15);
        gymClass.addStudent(student);

    }

    /**
     * Tests that a room and gym class can be build from an element object.
     */
    @Test
    public void roomClassTest() {
        Room readRoom = Room.elmToRoom(room.getContent());
        Assert.assertEquals(readRoom.getRoomNum(),room.getRoomNum());
        Assert.assertEquals(readRoom.getClasses().getSize(),room.getClasses().getSize());

        GymClass readClass = GymClass.elmToGymClass(gymClass.getContent());
        Assert.assertTrue(readClass.getStartDate().equals(gymClass.getStartDate()));
        Assert.assertTrue(readClass.getEndDate().equals(gymClass.getEndDate()));
        Assert.assertEquals(readClass.getRank(),gymClass.getRank());
        Assert.assertEquals(readClass.getName(),gymClass.getName());
        Assert.assertEquals(readClass.getSize(),gymClass.getSize());
        Assert.assertEquals(readClass.getMaxSize(),gymClass.getMaxSize());
        Assert.assertEquals(readClass.getClassLength(),gymClass.getClassLength());
        Assert.assertEquals(readClass.getClassType(),gymClass.getClassType());
        Assert.assertEquals(readClass.getID(),gymClass.getID());
        Assert.assertEquals(readClass.getRoom().getRoomNum(),gymClass.getRoom().getRoomNum());
        Assert.assertEquals(readClass.getTrainer().getID(),gymClass.getTrainer().getID());
    }

    /**
     * Tests that a Class list and App user object is created correctly from an element.
     *
     * Uncomment for local test, travis doesn't like.
     */
    //@Test
    public void elmToList() {
        ClassList readList = ClassList.elmToClassList(classList.getContent());
        Assert.assertEquals(readList.getSize(),classList.getSize());

        AppUsers readUsers = AppUsers.elmToUserList(users.getContext());
        Assert.assertEquals(users.getSize(),readUsers.getSize());
    }

    /**
     * Tests that the students are created properly.
     */
    @Test
    public void elmToUserTest() {
        Student newStudent = (Student) User.elmToUser(student.getContent());
        Assert.assertEquals(student.getName(),newStudent.getName());
        Assert.assertEquals(student.getID(),newStudent.getID());
        Assert.assertEquals(student.getPassword(),newStudent.getPassword());
        Assert.assertEquals(student.getUserName(),newStudent.getUserName());
        Assert.assertEquals(student.getBelt(),newStudent.getBelt());
        Assert.assertEquals(student.getPic(),newStudent.getPic());
        Assert.assertEquals(student.getClasses().getSize(),newStudent.getClasses().getSize());

        Trainer newTrainer = (Trainer) User.elmToUser(trainer.getContent());
        Assert.assertEquals(trainer.getName(),newTrainer.getName());
        Assert.assertEquals(trainer.getID(),newTrainer.getID());
        Assert.assertEquals(trainer.getPassword(),newTrainer.getPassword());
        Assert.assertEquals(trainer.getUserName(),newTrainer.getUserName());
        Assert.assertEquals(trainer.getBelt(),newTrainer.getBelt());
        Assert.assertEquals(trainer.getPic(),newTrainer.getPic());
        Assert.assertEquals(trainer.getAllClasses().getSize(),newTrainer.getAllClasses().getSize());
        Assert.assertEquals(trainer.getAvailability().size(),newTrainer.getAvailability().size());

        Owner newOwner = (Owner) User.elmToUser(owner.getContent());
        Assert.assertEquals(owner.getName(),newOwner.getName());
        Assert.assertEquals(owner.getID(),newOwner.getID());
        Assert.assertEquals(owner.getPassword(),newOwner.getPassword());
        Assert.assertEquals(owner.getUserName(),newOwner.getUserName());
        Assert.assertEquals(owner.getBelt(),newOwner.getBelt());
        Assert.assertEquals(owner.getPic(),newOwner.getPic());
        Assert.assertEquals(owner.getAllClasses().getSize(),newOwner.getAllClasses().getSize());
    }
}
