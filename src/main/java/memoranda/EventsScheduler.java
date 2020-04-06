package main.java.memoranda;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/*$Id: EventsScheduler.java,v 1.4 2004/01/30 12:17:41 alexeya Exp $*/
/**
 * Class that handles scheduling events
 */
public class EventsScheduler {

    static Vector _timers = new Vector();
    static Vector _listeners = new Vector();

    static Timer changeDateTimer = new Timer();

    /**
     * Initializer to add DefaultEventNotifier Listener
     */
    static {
        addListener(new DefaultEventNotifier());            
    }

    /**
     * creates the scheduler.
     */
    public static void init() {
        cancelAll();
        Vector events = (Vector)EventsManager.getActiveEvents();
        _timers = new Vector();
        /*DEBUG*/System.out.println("----------");
        for (Object event : events) {
            Event ev = (Event) event;
            Date evTime = ev.getTime();
            /*DEBUG*/
            System.out.println((Calendar.getInstance()).getTime());
            //  if (evTime.after(new Date())) {
            if (evTime.after((Calendar.getInstance()).getTime())) {
                EventTimer t = new EventTimer(ev);
                t.schedule(new NotifyTask(t), ev.getTime());
                _timers.add(t);
                /*DEBUG*/
                System.out.println(ev.getTimeString());
            }
        }
        /*DEBUG*/System.out.println("----------");
        Date midnight = getMidnight();
        changeDateTimer.schedule(new TimerTask() {
                public void run() {
                    init();
                    this.cancel();
                }
        }, midnight);
        notifyChanged();
    }

    /**
     * cancels all timers in _timers
     */
    public static void cancelAll() {
        for (Object timer : _timers) {
            EventTimer t = (EventTimer) timer;
            t.cancel();
        }
    }

    /**
     * method that returns a vector of all scheduled events\
     * @return Vector
     */
    public static Vector getScheduledEvents() {
        Vector v = new Vector();
        for (Object timer : _timers) v.add(((EventTimer) timer).getEvent());
        return v;
    }

    /**
     * returns the next scheduled event
     * @return Event
     */
    public static Event getFirstScheduledEvent() {
        if (!isEventScheduled()) return null;
        Event e1 = ((EventTimer)_timers.get(0)).getEvent();
        for (int i = 1; i < _timers.size(); i++) { 
            Event ev = ((EventTimer)_timers.get(i)).getEvent();
            if (ev.getTime().before(e1.getTime()))
                e1 = ev;
        }
        return e1;
    }

    /**
     * Adds a listener to _listeners
     * @param enl EventNotificationListener
     */
    public static void addListener(EventNotificationListener enl) {
        _listeners.add(enl);
    }

    /**
     * method that determines if there are any events scheduled
     * @return boolean
     */
    public static boolean isEventScheduled() {
        return _timers.size() > 0;
    }

    /**
     * Notifies the listeners that an event happened
     * @param ev Event
     */
    private static void notifyListeners(Event ev) {
        for (Object listener : _listeners) ((EventNotificationListener) listener).eventIsOccured(ev);
    }

    /**
     * Notifies the listeners that an event has changed
     */
    private static void notifyChanged() {
        for (Object listener : _listeners) ((EventNotificationListener) listener).eventsChanged();
    }

    /**
     * returns a Date object for midnight
     * @return Date
     */
    private static Date getMidnight() {
       Calendar cal = Calendar.getInstance();
       cal.set(Calendar.HOUR_OF_DAY, 0);
       cal.set(Calendar.MINUTE, 0);
       cal.set(Calendar.SECOND,0);
       cal.set(Calendar.MILLISECOND,0);
       cal.add(Calendar.DAY_OF_MONTH,1);
       return cal.getTime();
    }

    //Nested classes where IntelliJ can't find any uses.
    /**
     * Inner NotifyTask class that extends TimerTask
     */
    static class NotifyTask extends TimerTask {
        
        EventTimer _timer;

        /**
         * Method that creates NotifyTask based on EventTimer t passed in and sets current timer to NotifyTask
         * @param t
         */
        public NotifyTask(EventTimer t) {
            super();            
            _timer = t;
        }

        /**
         * Method that runs the timer.
         */
        public void run() {            
            _timer.cancel();
            _timers.remove(_timer);
            notifyListeners(_timer.getEvent());
            notifyChanged();
        }
    }

    /**
     * Inner EventTimer class
     */
    static class EventTimer extends Timer {
        Event _event;

        /**
         * Method to create an EventTimer based on Event ev passed in, and sets current Event to ev
         * @param ev
         */
        public EventTimer(Event ev) {
            super();
            _event = ev;
        }

        /**
         * Method that returns the current Event
         * @return Event
         */
        public Event getEvent() {
            return _event;
        }
    }


}
