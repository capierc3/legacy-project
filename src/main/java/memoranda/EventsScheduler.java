/**
 * EventsScheduler.java
 * Created on 10.03.2003, 20:20:08 Alex
 * Package: net.sf.memoranda
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 *
 */
/*$Id: EventsScheduler.java,v 1.4 2004/01/30 12:17:41 alexeya Exp $*/
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
     * Method to initialize EventsScheduler
     */
    public static void init() {
        cancelAll();
        //changeDateTimer.cancel();
        Vector events = (Vector)EventsManager.getActiveEvents();
        _timers = new Vector();
        /*DEBUG*/System.out.println("----------");
        for (int i = 0; i < events.size(); i++) {
            Event ev = (Event)events.get(i);
            Date evTime = ev.getTime();
        /*DEBUG*/System.out.println((Calendar.getInstance()).getTime());
          //  if (evTime.after(new Date())) {
	      if (evTime.after((Calendar.getInstance()).getTime())) {	
                EventTimer t = new EventTimer(ev);
                t.schedule(new NotifyTask(t), ev.getTime());                
                _timers.add(t);
                /*DEBUG*/System.out.println(ev.getTimeString());
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
     * Method to remove all of the EventTimer from the _timers Vector
     */
    public static void cancelAll() {
        for (int i = 0; i < _timers.size(); i++) {
            EventTimer t = (EventTimer)_timers.get(i);
            t.cancel();
        }
    }

    /**
     * Method that returns a Vector of Scheduled Events
     * @return Vector
     */
    public static Vector getScheduledEvents() {
        Vector v = new Vector();
        for (int i = 0; i < _timers.size(); i++) 
            v.add(((EventTimer)_timers.get(i)).getEvent());
        return v;
    }

    /**
     * Method that returns the first ScheduledEvent from the Events Vector
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
     * Method that adds an EventNotificationListener to the Vector of _listeners
     * @param enl
     */
    public static void addListener(EventNotificationListener enl) {
        _listeners.add(enl);
    }

    /**
     * Method that returns a boolean based on if any Events are scheduled
     * @return boolean
     */
    public static boolean isEventScheduled() {
        return _timers.size() > 0;
    }

    /**
     * Method to notify EventNotificationListeners if the event has occurred
     * @param ev
     */
    private static void notifyListeners(Event ev) {
        for (int i = 0; i < _listeners.size(); i++)
            ((EventNotificationListener)_listeners.get(i)).eventIsOccured(ev);
    }

    /**
     * Method that notifies the EventNotificationListeners that the event has changed
     */
    private static void notifyChanged() {
        for (int i = 0; i < _listeners.size(); i++)
            ((EventNotificationListener)_listeners.get(i)).eventsChanged();
    }

    /**
     * Method that returns Date at midnight
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
