package test.java;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ElmToObjectTest {

    AppUsers users;
    ClassList classList;
    GymClass gymClass;
    Owner owner;
    Room room;
    Student student;
    Trainer trainer;

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

    }

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
