package main.java.memoranda.date;

/*$Id: DateListener.java,v 1.2 2004/01/30 12:17:41 alexeya Exp $*/
public interface DateListener {

  // Method declared to change the current date to the CalendarDate parameter passed in
  void dateChange(CalendarDate date);

}