package main.java.memoranda.gym;

import main.java.memoranda.Note;
import main.java.memoranda.date.CalendarDate;

import java.io.File;
import java.util.Collection;

public class OwnerImpl extends UserImpl implements Owner {
    public OwnerImpl(String name, String id, String userName, String password, Belt belt, File newPicture, Collection<Note> newNoteList, ClassList newUserClasses) {
        super(name, id, userName, password, belt, newPicture, newNoteList, newUserClasses, "Owner");
    }
}
