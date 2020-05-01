package main.java.memoranda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Util;

import java.util.Map;
import java.util.Collections;

import nu.xom.Attribute;
//import nu.xom.Comment;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParentNode;

public class EventsManager {
	public static final int NO_REPEAT = 0;
	public static final int REPEAT_DAILY = 1;
	public static final int REPEAT_WEEKLY = 2;
	public static final int REPEAT_MONTHLY = 3;
	public static final int REPEAT_YEARLY = 4;

	public static Document _doc = null;
	static Element _root = null;

	/**
	 * Initializing Events Manager root and document
	 */
	static {
		CurrentStorage.get().openEventsManager();
		if (_doc == null) {
			_root = new Element("eventslist");
			_doc = new Document(_root);
		} else
			_root = _doc.getRootElement();

	}

	/**
	 * creates a sticker and adds it to root
	 * @param text String
	 * @param prior int
	 */
	public static void createSticker(String text, int prior) {
		Element el = new Element("sticker");
		el.addAttribute(new Attribute("id", Util.generateId()));
		el.addAttribute(new Attribute("priority", prior+""));
		el.appendChild(text);
		_root.appendChild(el);
	}

	/**
	 * gets a hashmap of all the stickers
	 * @return HashMap
	 */
	public static Map getStickers() {
		Map m = new HashMap();
		Elements els = _root.getChildElements("sticker");
		for (int i = 0; i < els.size(); i++) {
			Element se = els.get(i);
			m.put(se.getAttribute("id").getValue(), se);
		}
		return m;
	}

	/**
	 * removes a sticker
	 * @param stickerId String
	 */
	public static void removeSticker(String stickerId) {
		Elements els = _root.getChildElements("sticker");
		for (int i = 0; i < els.size(); i++) {
			Element se = els.get(i);
			if (se.getAttribute("id").getValue().equals(stickerId)) {
				_root.removeChild(se);
				break;
			}
		}
	}

	/**
	 * find if there is an event on a given day
	 * @param date CalendarDate
	 * @return boolean
	 */
	public static boolean isNREventsForDate(CalendarDate date) {
		Day d = getDay(date);
		if (d == null)
			return false;
		return d.getElement().getChildElements("event").size() > 0;
	}

	/**
	 * returns all events for a given date
	 * @param date CalendarDate
	 * @return Vector
	 */
	public static Collection getEventsForDate(CalendarDate date) {
		Vector v = new Vector();
		Day d = getDay(date);
		if (d != null) {
			Elements els = d.getElement().getChildElements("event");
			for (int i = 0; i < els.size(); i++)
				v.add(new EventImpl(els.get(i)));
		}
		Collection r = getRepeatableEventsForDate(date);
		if (r.size() > 0)
			v.addAll(r);
		Collections.sort(v);
		return v;
	}

	/**
	 * creates a new event Object with inputted information
	 * @param date CalendarDate
	 * @param hh int
	 * @param mm int
	 * @param text String
	 * @return Event
	 */
	public static Event createEvent(CalendarDate date, int hh, int mm, String text) {
		Element el = new Element("event");
		el.addAttribute(new Attribute("id", Util.generateId()));
		el.addAttribute(new Attribute("hour", String.valueOf(hh)));
		el.addAttribute(new Attribute("min", String.valueOf(mm)));
		el.appendChild(text);
		Day d = getDay(date);
		if (d == null)
			d = createDay(date);
		d.getElement().appendChild(el);
		return new EventImpl(el);
	}

	/**
	 * creates a repeatable event with given information
	 * @param type int
	 * @param startDate CalendarDate
	 * @param endDate CalendarDate
	 * @param period int
	 * @param hh int
	 * @param mm int
	 * @param text String
	 * @param workDays boolean
	 * @return Event
	 */
	public static Event createRepeatableEvent(
		int type, CalendarDate startDate, CalendarDate endDate, int period, int hh, int mm, String text, boolean workDays) {
		Element el = new Element("event");
		Element rep = _root.getFirstChildElement("repeatable");
		if (rep == null) {
			rep = new Element("repeatable");
			_root.appendChild(rep);
		}
		el.addAttribute(new Attribute("repeat-type", String.valueOf(type)));
		el.addAttribute(new Attribute("id", Util.generateId()));
		el.addAttribute(new Attribute("hour", String.valueOf(hh)));
		el.addAttribute(new Attribute("min", String.valueOf(mm)));
		el.addAttribute(new Attribute("startDate", startDate.toString()));
		if (endDate != null)
			el.addAttribute(new Attribute("endDate", endDate.toString()));
		el.addAttribute(new Attribute("period", String.valueOf(period)));
		// new attribute for wrkin days - ivanrise
		el.addAttribute(new Attribute("workingDays",String.valueOf(workDays)));
		el.appendChild(text);
		rep.appendChild(el);
		return new EventImpl(el);
	}

	/**
	 * Returns a vector of all repeatable events
	 * @return Vector
	 */
	public static Collection getRepeatableEvents() {
		Vector v = new Vector();
		Element rep = _root.getFirstChildElement("repeatable");
		if (rep == null)
			return v;
		Elements els = rep.getChildElements("event");
		for (int i = 0; i < els.size(); i++)
			v.add(new EventImpl(els.get(i)));
		return v;
	}

	/**
	 * gets all repeatable events that happen on a certain date
	 * @param date CalenderDate
	 * @return Vector
	 */
	public static Collection getRepeatableEventsForDate(CalendarDate date) {
		Vector reps = (Vector) getRepeatableEvents();
		Vector v = new Vector();
		for (Object rep : reps) {
			Event ev = (Event) rep;
			// --- ivanrise
			// ignore this event if it's a 'only working days' event and today is weekend.
			if (ev.getWorkingDays() && (date.getCalendar().get(Calendar.DAY_OF_WEEK) == 1 ||
					date.getCalendar().get(Calendar.DAY_OF_WEEK) == 7)) continue;
			if (date.inPeriod(ev.getStartDate(), ev.getEndDate())) {
				if (ev.getRepeat() == REPEAT_DAILY) {
					int n = date.getCalendar().get(Calendar.DAY_OF_YEAR);
					int ns =
							ev.getStartDate().getCalendar().get(
									Calendar.DAY_OF_YEAR);
					if ((n - ns) % ev.getPeriod() == 0)
						v.add(ev);
				} else if (ev.getRepeat() == REPEAT_WEEKLY) {
					if (date.getCalendar().get(Calendar.DAY_OF_WEEK)
							== ev.getPeriod())
						v.add(ev);
				} else if (ev.getRepeat() == REPEAT_MONTHLY) {
					if (date.getCalendar().get(Calendar.DAY_OF_MONTH)
							== ev.getPeriod())
						v.add(ev);
				} else if (ev.getRepeat() == REPEAT_YEARLY) {
					int period = ev.getPeriod();
					if ((date.getYear() % 4) == 0
							&& date.getCalendar().get(Calendar.DAY_OF_YEAR) > 60)
						period++;

					if (date.getCalendar().get(Calendar.DAY_OF_YEAR) == period)
						v.add(ev);
				}
			}
		}
		return v;
	}

	/**
	 * gets all events currently happening
	 * @return Collection
	 */
	public static Collection getActiveEvents() {
		return getEventsForDate(CalendarDate.today());
	}

	/**
	 * Returns an event that happens on inputted date and time, if exists
	 * @param date CalenderDate
	 * @param hh int
	 * @param mm int
	 * @return Event
	 */
	public static Event getEvent(CalendarDate date, int hh, int mm) {
		Day d = getDay(date);
		if (d == null)
			return null;
		Elements els = d.getElement().getChildElements("event");
		for (int i = 0; i < els.size(); i++) {
			Element el = els.get(i);
			if ((Integer.parseInt(el.getAttribute("hour").getValue())
				== hh)
				&& (Integer.parseInt(el.getAttribute("min").getValue())
					== mm))
				return new EventImpl(el);
		}
		return null;
	}

	/**
	 * removes a date on specified date and time, if exists.
	 * @param date CalendarDate
	 * @param hh int
	 * @param mm int
	 */
	public static void removeEvent(CalendarDate date, int hh, int mm) {
		Day d = getDay(date);
		if (d == null)
			d.getElement().removeChild(getEvent(date, hh, mm).getContent());
	}

	/**
	 * removes an event.
	 * @param ev Event
	 */
	public static void removeEvent(Event ev) {
		ParentNode parent = ev.getContent().getParent();
		parent.removeChild(ev.getContent());
	}


	//year, month, and day class just like Notes has. Should just turn into its own classes.

	/**
	 * Method that creates and returns a Day from the CalendarDate passed in
	 * @param date
	 * @return Day
	 */
	private static Day createDay(CalendarDate date) {
		Year y = getYear(date.getYear());
		if (y == null)
			y = createYear(date.getYear());
		Month m = y.getMonth(date.getMonth());
		if (m == null)
			m = y.createMonth(date.getMonth());
		Day d = m.getDay(date.getDay());
		if (d == null)
			d = m.createDay(date.getDay());
		return d;
	}

	/**
	 * Method that returns a Year that is created by the int y passed in
	 * @param y
	 * @return Year
	 */
	private static Year createYear(int y) {
		Element el = new Element("year");
		el.addAttribute(new Attribute("year", new Integer(y).toString()));
		_root.appendChild(el);
		return new Year(el);
	}

	/**
	 * Method to gets the Year based on the int y passed in, and returns the Year
	 * @param y
	 * @return Year
	 */
	private static Year getYear(int y) {
		Elements yrs = _root.getChildElements("year");
		String yy = new Integer(y).toString();
		for (int i = 0; i < yrs.size(); i++)
			if (yrs.get(i).getAttribute("year").getValue().equals(yy))
				return new Year(yrs.get(i));
		//return createYear(y);
		return null;
	}

	/**
	 * Method to get the Day based on CalendarDate date passed in
	 * @param date
	 * @return Day
	 */
	private static Day getDay(CalendarDate date) {
		Year y = getYear(date.getYear());
		if (y == null)
			return null;
		Month m = y.getMonth(date.getMonth());
		if (m == null)
			return null;
		return m.getDay(date.getDay());
	}

	/**
	 * Initializing inner Year class
	 */
	static class Year {
		Element yearElement = null;

		public Year(Element el) {
			yearElement = el;
		}

		/**
		 * Method to get the int value for the current Year
		 * @return int
		 */
		public int getValue() {
			return new Integer(yearElement.getAttribute("year").getValue())
				.intValue();
		}

		/**
		 * Method to return Month based on the int m passed in tied to the current Year
		 * @param m
		 * @return Month
		 */
		public Month getMonth(int m) {
			Elements ms = yearElement.getChildElements("month");
			String mm = new Integer(m).toString();
			for (int i = 0; i < ms.size(); i++)
				if (ms.get(i).getAttribute("month").getValue().equals(mm))
					return new Month(ms.get(i));
			//return createMonth(m);
			return null;
		}

		/**
		 * Method that creates and returns a Month created based on the int m passed in and
		 * ties to current Year
		 * @param m
		 * @return Month
		 */
		private Month createMonth(int m) {
			Element el = new Element("month");
			el.addAttribute(new Attribute("month", new Integer(m).toString()));
			yearElement.appendChild(el);
			return new Month(el);
		}

		/**
		 * Method that gets and returns a Vector of Months in the current Year
		 * @return Vector
		 */
		public Vector getMonths() {
			Vector v = new Vector();
			Elements ms = yearElement.getChildElements("month");
			for (int i = 0; i < ms.size(); i++)
				v.add(new Month(ms.get(i)));
			return v;
		}

		/**
		 * Method that gets and returns the current Year Element
		 * @return Element
		 */
		public Element getElement() {
			return yearElement;
		}

	}

	/**
	 * Inner Month class for Event
	 */
	static class Month {
		Element mElement = null;

		/**
		 * Contructor that creates and sets current Month based on Element passed in
		 * @param el
		 */
		public Month(Element el) {
			mElement = el;
		}

		/**
		 * Method that returns the int value of the current Month element
		 * @return int
		 */
		public int getValue() {
			return new Integer(mElement.getAttribute("month").getValue())
				.intValue();
		}

		/**
		 * Method that returns the Day tied to the current Month based on the int d value for the Day
		 * @param d
		 * @return Day
		 */
		public Day getDay(int d) {
			if (mElement == null)
				return null;
			Elements ds = mElement.getChildElements("day");
			String dd = new Integer(d).toString();
			for (int i = 0; i < ds.size(); i++)
				if (ds.get(i).getAttribute("day").getValue().equals(dd))
					return new Day(ds.get(i));
			//return createDay(d);
			return null;
		}

		/**
		 * Method to creates and returns a Day object and ties to the current Month
		 * @param d
		 * @return Day
		 */
		private Day createDay(int d) {
			Element el = new Element("day");
			el.addAttribute(new Attribute("day", new Integer(d).toString()));
			el.addAttribute(
				new Attribute(
					"date",
					new CalendarDate(
						d,
						getValue(),
						new Integer(
							((Element) mElement.getParent())
								.getAttribute("year")
								.getValue())
							.intValue())
						.toString()));

			mElement.appendChild(el);
			return new Day(el);
		}

		/**
		 * Method to return Vector of days related to the current Month
		 * @return Vector
		 */
		public Vector getDays() {
			if (mElement == null)
				return null;
			Vector v = new Vector();
			Elements ds = mElement.getChildElements("day");
			for (int i = 0; i < ds.size(); i++)
				v.add(new Day(ds.get(i)));
			return v;
		}

		/**
		 * Method to return the current Month element
		 * @return Element
		 */
		public Element getElement() {
			return mElement;
		}

	}

	/**
	 * Inner Day class
	 */
	static class Day {
		Element dEl = null;

		/**
		 * Constructor that creates the current day to the Element el passed in
		 * @param el
		 */
		public Day(Element el) {
			dEl = el;
		}

		/**
		 * Method to gets the int value for the current Day
		 * @return int
		 */
		public int getValue() {
			return Integer.parseInt(dEl.getAttribute("day").getValue());
		}

		/*
		 * public Note getNote() { return new NoteImpl(dEl);
		 */

		/**
		 * Method that returns the current Day element
		 * @return Element
		 */
		public Element getElement() {
			return dEl;
		}
	}
}
