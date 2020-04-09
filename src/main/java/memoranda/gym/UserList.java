package main.java.memoranda.gym;

import java.util.Collection;

/**
 * Class that will hold be the main source for lists of students, trainers, or could be used for owners too.
 * @author Chase
 */
public interface UserList {

    /**
     * Searches the list for a user by using the inputted ID
     * @param id String
     * @return User
     */
    User getUser(String id);

    /**
     * Adds a user to the list
     * @param user User
     */
    void addUser(User user);

    /**
     * removes a user from a list with matching ID number
     * @param id String
     */
    void removeUser(String id);

    /**
     * finds the size of list.
     * @return int
     */
    int getSize();

    /**
     * returns the entire list
     * @return Collection
     */
    Collection<User> getAllUsers();

}
