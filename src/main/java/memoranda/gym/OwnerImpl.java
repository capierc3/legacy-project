package main.java.memoranda.gym;

import main.java.memoranda.Note;
import main.java.memoranda.gym.TrainerImpl;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.gym.Owner;
import nu.xom.Element;


import java.io.File;
import java.util.Collection;

public class OwnerImpl extends UserImpl implements Owner {
    /**
     * Contructor for OwnerImpl.
     * @param name
     * @param id
     * @param userName
     * @param password
     * @param belt
     * @param newPicture
     * @param newNoteList
     * @param newUserClasses
     */
    public OwnerImpl(String name, String id, String userName, String password, Belt belt, File newPicture, Collection<Note> newNoteList, ClassList newUserClasses) {
        super(name, id, userName, password, belt, newPicture, newNoteList, newUserClasses,"Owner");

    }
}
