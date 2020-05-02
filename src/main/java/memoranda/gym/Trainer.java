package main.java.memoranda.gym;

import main.java.memoranda.date.CalendarDate;
import java.util.Collection;
import main.java.memoranda.gym.User;
import nu.xom.Element;

/**
 * Interface for the trainer methods to be used
 * 
 * @author Chase
 */
public interface Trainer extends User {
    /**
     * Method to set Description on Element.
     * 
     * @param description
     */
    void setDescription(String description);

    /**
     * Method to get Description of Trainer.
     * 
     * @return String
     */
    String getDescription();

    /**
     * Method to set Availability Dates in Element
     */
    void setAvailabilityDates(Collection<CalendarDate> availableDates);

    /**
     * Method that returns a Collection of CalendarDate objects that represent when
     * the trainer is able to teach.
     * 
     * @return Collection
     */
    Collection getAvailability();

    /**
     * sets the Availability of the trainer.
     * 
     * @param dates Collection
     */
    void setAvailability(Collection<CalendarDate> dates);

    /**
     * Checks if thee trainer is available during a certain time and date
     * 
     * @return boolean
     */
    boolean isAvailable(CalendarDate date);

    /**
     * Getter for the training rank of the trainer
     * 
     * @return String
     */
    String getTrainingRank();

    /**
     * Sets the training rank
     * 
     * @param rank String
     */
    void setTrainingRank(String rank);

    /**
     * Method to return Trainer Element
     * 
     * @return Element
     */
    Element getContent();
}
