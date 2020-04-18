package main.java.memoranda.gym;

import java.io.File;
import java.util.Collection;

import main.java.memoranda.Note;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.gym.Trainer;
import main.java.memoranda.gym.UserImpl;
import nu.xom.Attribute;
import nu.xom.Element;

/**
 * Interface for the trainer methods to be used
 * @author Chase
 */
public abstract class TrainerImpl extends UserImpl implements Trainer{
    private Collection<CalendarDate> availabilityDates;


    public TrainerImpl(String name, String id, String userName, String password, Belt belt, File newPicture,
                       Collection<Note> newNoteList, ClassList newUserClasses, String userType) {
        super(name, id, userName, password, belt, newPicture, newNoteList, newUserClasses, "Trainer");
    }

    /**
     * Method to set Description on Element.
     * @param description
     */
    public void setDescription(String description) {
        setAttr("Description", description);
    }

    /**
     * Method to get Description of Trainer.
     * @return String
     */
    public String getDescription() {
        return element.getAttributeValue("Description");
    }

    /**
     * Method to set Availability Dates in Element
     */
    public void setAvailabilityDates(Collection<CalendarDate> availableDates) {
        for (CalendarDate date : availableDates) {
            element.appendChild(date.toString());
        }
    }

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
        setAvailabilityDates(availabilityDates);
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
        return element.getAttributeValue("TrainingRank");
    }

    /**
     * Sets the training rank
     * @param rank String
     */
    public void setTrainingRank(String rank) {
        setAttr("TrainingRank", rank);
    }

    public Element getContent() {
        return element;
    }

    private void setAttr(String a, String value) {
        Attribute attr = element.getAttribute(a);
        if (attr == null)
            element.addAttribute(new Attribute(a, value));
        else
            attr.setValue(value);
    }

}
