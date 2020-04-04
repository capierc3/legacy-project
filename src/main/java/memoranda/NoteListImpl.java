package main.java.memoranda;
import java.util.Collection;
import java.util.Vector;

import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
/*$Id: NoteListImpl.java,v 1.14 2004/10/28 11:30:15 alexeya Exp $*/
/**
 *  Class that implements the Note list interface
 */
public class NoteListImpl implements NoteList {

    private Project _project = null;
    private Document _doc = null;
    private Element _root = null;


    /**
     * Constructor when using a existing Document and project
     * @param doc nu.xom.document
     * @param prj Project object
     */
    public NoteListImpl(Document doc, Project prj) {
        _doc = doc;
        _root = _doc.getRootElement();
        _project = prj;
    }

    /**
     * Constructor with out existing Document
     * @param prj Project object
     */
    public NoteListImpl(Project prj) {
        _root = new Element("noteslist");
        _doc = new Document(_root);
        _project = prj;    
    }

    /**
     * @see NoteList#getAllNotes()
     * @return Vector
     */
    public Collection getAllNotes() {
        Vector v = new Vector();
        Elements yrs = _root.getChildElements("year");
        for (int yi = 0; yi < yrs.size(); yi++) {
            Year y = new Year(yrs.get(yi));
            Vector ms = y.getMonths();
            for (Object o : ms) {
                Month m = (Month) o;
                Vector ds = m.getDays();
                for (Object value : ds) {
                    Day d = (Day) value;
                    Vector ns = d.getNotes();
                    for (Object item : ns) {
                        NoteElement n = (NoteElement) item;
                        v.add(new NoteImpl(n.getElement(), _project));
                    }
                }
            }
        }
        return v;
    }
    
    /**
     * @see main.java.memoranda.NoteList#getMarkedNotes()
     */
    public Collection getMarkedNotes() {
        Vector v = new Vector();
        Elements yrs = _root.getChildElements("year");
        for (int yi = 0; yi < yrs.size(); yi++) {
            Year y = new Year(yrs.get(yi));
            Vector ms = y.getMonths();
            for (int mi = 0; mi < ms.size(); mi++) {
                Month m = (Month) ms.get(mi);
                Vector ds = m.getDays();
                for (int di = 0; di < ds.size(); di++) {
                    Day d = (Day) ds.get(di);
					Vector ns = d.getNotes();
					for(int ni = 0; ni < ns.size(); ni++) {
						NoteElement ne = (NoteElement) ns.get(ni);
						Note n = new NoteImpl(ne.getElement(), _project);
						if (n.isMarked()) v.add(n);
                }
            }
        }
    }
	        return v;
	}

    /**
     * @see main.java.memoranda.NoteList#getNotesForPeriod(CalendarDate, CalendarDate)
     * @param startDate CalendarDate
     * @param endDate CalendarDate
     * @return Vector
     */
    public Collection getNotesForPeriod(CalendarDate startDate, CalendarDate endDate) {
        Vector v = new Vector();
        Elements yrs = _root.getChildElements("year");
        for (int yi = 0; yi < yrs.size(); yi++) {
            Year y = new Year(yrs.get(yi));
            if ((y.getValue() >= startDate.getYear()) && (y.getValue() <= endDate.getYear())) {
                Vector months = y.getMonths();
                for (int mi = 0; mi < months.size(); mi++) {
                    Month m = (Month) months.get(mi);
                    if (!((y.getValue() == startDate.getYear()) && (m.getValue() < startDate.getMonth()))
                        || !((y.getValue() == endDate.getYear()) && (m.getValue() > endDate.getMonth()))) {
                        Vector days = m.getDays();
                        for (int di = 0; di < days.size(); di++) {
                            Day d = (Day) days.get(di);
                            if (!((m.getValue() == startDate.getMonth()) && (d.getValue() < startDate.getDay()))
							|| !((m.getValue() == endDate.getMonth()) && (d.getValue() > endDate.getDay()))) {
								Vector ns = d.getNotes();
								for(int ni = 0; ni < ns.size(); ni++) {
									NoteElement n = (NoteElement) ns.get(ni);
									v.add(new NoteImpl(n.getElement(), _project));
								}
							}
                        }
                    }
                }
            }
        }
        return v;
    }


    /**
     * @see  main.java.memoranda.NoteList#getNoteForDate(CalendarDate)
      * @param date CalendarDate
     * @return Note
     */
    public Note getNoteForDate(CalendarDate date) {
        Day d = getDay(date);
        if (d == null)
            return null;
		Vector ns = d.getNotes();
		if(ns.size()>0) {
			NoteElement n = (NoteElement) ns.get(0);
			Note currentNote = new NoteImpl(n.getElement(), _project);
			return currentNote; 
		}
		return null;
        //return new NoteImpl(d.getElement(), _project);
    }

    /**
     * @see main.java.memoranda.NoteList#createNoteForDate(CalendarDate)
     * @param date CalendarDate
     * @return Note
     */
    public Note createNoteForDate(CalendarDate date) {
        Year y = getYear(date.getYear());
        if (y == null)
            y = createYear(date.getYear());
        Month m = y.getMonth(date.getMonth());
        if (m == null)
            m = y.createMonth(date.getMonth());
        Day d = m.getDay(date.getDay());
        if (d == null) 
            d = m.createDay(date.getDay());
		NoteElement ne = d.createNote(Util.generateId());
        return new NoteImpl(ne.getElement(), _project);
    }
    
     /*
     * @see net.sf.memoranda.NoteList#removeNoteForDate(net.sf.memoranda.date.CalendarDate)
     */
/*    public void removeNoteForDate(CalendarDate date) {
        Day d = getDay(date);
        if (d == null) return;
        d.getElement().getParent().removeChild(d.getElement());             
    }
*/

    /**
     * @see main.java.memoranda.NoteList#removeNote(CalendarDate, String)
     * @param date CalendarDate
     * @param id String
     */
	 public void removeNote(CalendarDate date, String id) {
        Day d = getDay(date);
        if (d == null) return;
		Vector ns = d.getNotes();
		for(int i=0;i<ns.size();i++) {
			NoteElement n = (NoteElement) ns.get(i);
			Element ne = n.getElement();
			if(ne.getAttribute("refid").getValue().equals(id)) d.getElement().removeChild(n.getElement());
		}
//		CurrentNote.set(null);
    }

    /**
     * @see NoteList#getActiveNote()
     * @return Note
     */
    public Note getActiveNote() {
        //return CurrentNote.get();
    	return getNoteForDate(CurrentDate.get());
    	// FIXED: Must return the first note for today [alexeya]
    }

    /**
     * getter for a year element in the _root doc
     * @param y int for the year
     * @return Year object
     */
    private Year getYear(int y) {
        Elements yrs = _root.getChildElements("year");
        String yy = Integer.toString(y);
        for (int i = 0; i < yrs.size(); i++)
            if (yrs.get(i).getAttribute("year").getValue().equals(yy))
                return new Year(yrs.get(i));
        return null;
    }

    /**
     * creates a new Year object from the _root doc
     * @param y int for the year
     * @return Year object
     */
    private Year createYear(int y) {
        Element el = new Element("year");
        el.addAttribute(new Attribute("year", Integer.toString(y)));
        _root.appendChild(el);
        return new Year(el);
    }
/*
    private Vector getYears() {
        Vector v = new Vector();
        Elements yrs = _root.getChildElements("year");
        for (int i = 0; i < yrs.size(); i++)
            v.add(new Year(yrs.get(i)));
        return v;
    }
*/

    /**
     * getter that returns a Day object from a CalendarDate object
     * @param date CalendarDate
     * @return Day
     */
    private Day getDay(CalendarDate date) {
        Year y = getYear(date.getYear());
        if (y == null)
            return null;
        Month m = y.getMonth(date.getMonth());
        if (m == null)
            return null;
        return m.getDay(date.getDay());
    }

    /**
     * Private class for the Year data
     */
    private class Year {
        Element yearElement = null;

        /**
         * constructor for the year, sets the element for the class
         * @param el
         */
        public Year(Element el) {
            yearElement = el;
        }

        /**
         * gets the int value for the year object
         * @return int
         */
        public int getValue() {
            return Integer.parseInt(yearElement.getAttribute("year").getValue());
        }

        /**
         * Returns a month object that corresponds to the int value
         * @param m int
         * @return Month
         */
        public Month getMonth(int m) {
            Elements ms = yearElement.getChildElements("month");
            String mm = Integer.toString(m);
            for (int i = 0; i < ms.size(); i++)
                if (ms.get(i).getAttribute("month").getValue().equals(mm))
                    return new Month(ms.get(i));
            return null;
        }

        /**
         * creates a Month object and stores in the yearElement, also returns the Month object
         * @param m int
         * @return Month
         */
        private Month createMonth(int m) {
            Element el = new Element("month");
            el.addAttribute(new Attribute("month", Integer.toString(m)));
            yearElement.appendChild(el);
            return new Month(el);
        }

        /**
         * Gets the vector of months in the year object
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
         * getter that returns the yearElement used.
         * @return Element
         */
        public Element getElement() {
            return yearElement;
        }
    }

    /**
     * Private class for the Month data
     */
    private class Month {
        Element mElement = null;

        /**]
         * Constructor that sets the month element to the inputted element
         * @param el Element
         */
        public Month(Element el) {
            mElement = el;
        }

        /**
         * gets the values of the
         * @return int
         */
        public int getValue() {
            return Integer.parseInt(mElement.getAttribute("month").getValue());
        }

        /**
         * Returns a Day object with the corresponding int day value.
         * @param d int
         * @return Day
         */
        public Day getDay(int d) {
            if (mElement == null) return null;
            Elements ds = mElement.getChildElements("day");
            String dd = Integer.toString(d);
            for (int i = 0; i < ds.size(); i++)
                if (ds.get(i).getAttribute("day").getValue().equals(dd))
                    return new Day(ds.get(i));
            return null;
        }

        /**
         * creates a new Day object and adds it to the month, also returns it.
         * @param d int
         * @return Day
         */
        private Day createDay(int d) {
            Element el = new Element("day");
            el.addAttribute(new Attribute("day", Integer.toString(d)));
            mElement.appendChild(el);
            return new Day(el);
        }

        /**
         * returns a vector of Day objects that are in the month
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
         * getter for the month element used.
         * @return Element
         */
        public Element getElement() {
            return mElement;
        }
    }

    /**
     * Private class for the Day data
     */
    private class Day {
        Element dEl;

        /**
         * constructor for the Day object, sets the main element to the inputted one
         * @param el Element
         */
        public Day(Element el) {
            dEl = el;
            if (dEl.getAttribute("date") != null) {
            	Attribute dAttr = dEl.getAttribute("date");
            	Attribute tAttr = dEl.getAttribute("title");
				Element nEl = new Element("note");
				String date = dAttr.getValue().replace('/', '-');
				nEl.addAttribute(new Attribute("refid", date));
				nEl.addAttribute(new Attribute("title", tAttr.getValue()));
				dEl.appendChild(nEl);
            	dEl.removeAttribute(dAttr);            	
				dEl.removeAttribute(tAttr);
            }
        }

        /**
<<<<<<< HEAD
         * getter for the int value of the Day
=======
         * Method that returns the int value of the current Day
>>>>>>> US_44/comments-to-util-package
         * @return int
         */
        public int getValue() {
            return Integer.parseInt(dEl.getAttribute("day").getValue());
        }

        /*public Note getNote() {
            return new NoteImpl(dEl);
        }*/

        /**
<<<<<<< HEAD
         * Returns the note stored in the element
         * @param d String
=======
         * Method that gets and returns the NoteElement from the current day based on String d passed in
         * @param d
>>>>>>> US_44/comments-to-util-package
         * @return NoteElement
         */
		public NoteElement getNote(String d) {
            if (dEl == null) 
				return null;
            Elements ne = dEl.getChildElements("note");
            for (int i = 0; i < ne.size(); i++)
                if (ne.get(i).getAttribute("refid").getValue().equals(d))
                    return new NoteElement(ne.get(i));
            return null;
        }

        /**
<<<<<<< HEAD
         * Creates a note element and adds it to the day
         * @param d String
=======
         * Method that creates and returns a NoteElement from String d passed in
         * @param d
>>>>>>> US_44/comments-to-util-package
         * @return NoteElement
         */
        public NoteElement createNote(String d) {
            Element el = new Element("note");
//			el.addAttribute(new Attribute("refid", d));
/*            el.addAttribute(new Attribute("day", new Integer(d).toString()));
                        el.addAttribute(
                new Attribute(
                    "date",
                    new CalendarDate(
                        10,
                        10,
                        2004).toString()));
*/
            dEl.appendChild(el);
            return new NoteElement(el);
        }

        /**
<<<<<<< HEAD
         * Getter that returns a vector of all the notes stored in the day
=======
         * Method returns Vector of Notes tied to the current Day
>>>>>>> US_44/comments-to-util-package
         * @return Vector
         */
        public Vector getNotes() {
            if (dEl == null)
                return null;
            Vector v = new Vector();
            Elements ds = dEl.getChildElements("note");
            for (int i = 0; i < ds.size(); i++)
                v.add(new NoteElement(ds.get(i)));                                    
            return v;
        }

        /**
<<<<<<< HEAD
         * getter that returns the main element used in the class
=======
         * Method that returns the current Day Element
>>>>>>> US_44/comments-to-util-package
         * @return Element
         */
        public Element getElement() {
            return dEl;
        }
    }

    /**
     * private class to hold the note data
     */
    private class NoteElement {
		Element nEl;

        /**
         * Constructor that sets the inputted element to the class element.
         * @param el Element
         */
		public NoteElement(Element el) {
			nEl = el;
		}

        /**
         * returns the main element
         * @return Element
         */
		public Element getElement() {
			return nEl;
		}
	}

    /**
     * @see main.java.memoranda.NoteList#getXMLContent()
     */
    public Document getXMLContent() {
        return _doc;
    }
   
    

}
