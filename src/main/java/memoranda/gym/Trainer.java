package main.java.memoranda.gym;

import main.java.memoranda.date.CalendarDate;
import java.util.Collection;
import main.java.memoranda.gym.User;

/**
 * Interface for the trainer methods to be used
 * @author Chase
 */
public interface Trainer extends User {

    /**
     * Method that returns a Collection of CalendarDate objects that represent when the trainer is able to teach.
     * @return Collection
     */
    Collection getAvailability();

    /**
     * sets the Availability of the trainer.
     * @param dates Collection
     */
    void setAvailability(Collection<CalendarDate> dates);

    /**
     * Checks if thee trainer is available during a certain time and date
     * @return boolean
     */
    boolean isAvailable(CalendarDate date);

    /**
     * Getter for the training rank of the trainer
     * @return String
     */
    String getTrainingRank();

    /**
     * Sets the training rank
     * @param rank String
     */
    void setTrainingRank(String rank);


}
