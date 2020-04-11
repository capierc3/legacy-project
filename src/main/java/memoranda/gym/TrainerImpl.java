package memoranda.gym;

import java.util.Collection;
import main.java.memoranda.date.CalendarDate;

/**
 * Interface for the trainer methods to be used
 * @author Chase
 */
public class TrainerImpl implements Trainer{
    private String beltLevel;
    private String trainingRank;
    private String description;
    private Collection<CalendarDate> availabilityDates;

    /**
     * Method that returns a Collection of CalendarDate objects that represent when the trainer is able to teach.
     * @return Collection
     */
    public Collection<CalendarDate> getAvailability() {
        return availabilityDates;
    }

    /**
     * sets the Availability of the trainer.
     * @param dates Collection
     */
    public void setAvailability(Collection<CalendarDate> dates) {
        availabilityDates = dates;
    }

    /**
     * Checks if thee trainer is available during a certain time and date
     * @return boolean
     */
    public boolean isAvailable(CalendarDate date) {
        boolean result = false;
        if (!availabilityDates.isEmpty()) {
            if (availabilityDates.contains(date)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Getter for the training rank of the trainer
     * @return String
     */
    public String getTrainingRank() {
        return trainingRank;
    }

    /**
     * Sets the training rank
     * @param rank String
     */
    public void setTrainingRank(String rank) {
        trainingRank = rank;
    }

}
