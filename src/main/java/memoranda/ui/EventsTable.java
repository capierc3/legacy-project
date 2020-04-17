package main.java.memoranda.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import main.java.memoranda.Event;
import main.java.memoranda.EventsManager;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.gym.Belt;
import main.java.memoranda.gym.ClassList;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.gym.GymClassImpl;
import main.java.memoranda.util.Local;
/*$Id: EventsTable.java,v 1.6 2004/10/11 08:48:20 alexeya Exp $*/
/**
 *  Class for the main table that displays the Classes/Events for the software
 */
public class EventsTable extends JTable {

    public static final int EVENT = 100;
    public static final int EVENT_ID = 101;

    Vector events = new Vector();
    MyScheduleManager manager;
    ArrayList<GymClass> classList;
    /**
     * Constructor for EventsTable.
     */
    EventsTable(MyScheduleManager manager) {
        super();
        this.manager = manager;
        setModel(new EventsTableModel());
        initTable(CurrentDate.get());
        this.setShowGrid(false);
        CurrentDate.addDateListener(this::initTable);
    }

    /**
     * sets up the table
     * @param d CalendarDate
     */
    void initTable(CalendarDate d) {
        events = (Vector)EventsManager.getEventsForDate(d);
        classList = manager.getClasses();
//        getColumnModel().getColumn(0).setPreferredWidth(75);
//        getColumnModel().getColumn(0).setMaxWidth(75);
        clearSelection();
        updateUI();
    }

    /**
     * refreshes the table
     */
    public void refresh() {
        initTable(CurrentDate.get());
    }

     public TableCellRenderer getCellRenderer(int row, int column) {
        return new javax.swing.table.DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
                Component comp;
                comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                GymClass gymClass = (GymClass)getModel().getValueAt(row, EVENT);
                comp.setForeground(java.awt.Color.gray);
//                if (ev.isRepeatable())
//                    comp.setFont(comp.getFont().deriveFont(Font.ITALIC));
                if (CurrentDate.get().after(CalendarDate.today())) {
                  comp.setForeground(java.awt.Color.black);
                }
                else if (CurrentDate.get().equals(CalendarDate.today())) {
                    if (gymClass.getStartDate().after(new CalendarDate())) {
                        comp.setForeground(java.awt.Color.black);
                        comp.setFont(comp.getFont().deriveFont(Font.BOLD));
                    }
                    else if (gymClass.getStartDate().before(new CalendarDate())){
                        comp.setForeground(Color.lightGray);
                    }
                }
                return comp;
            }
        };

    }

    /**
     * Nested class for the table to be used
     */
    class EventsTableModel extends AbstractTableModel {
        //Can be used to add new columns
        String[] columnNames = {
                Local.getString("Name"),
                Local.getString("Class Type"),
                Local.getString("Belt Needed"),
                Local.getString("Start Time"),
                Local.getString("Class Length"),
                Local.getString("Instructor"),
                Local.getString("Spots Remaining")
        };

        /**
         * Constructor for the table
         */
        EventsTableModel() {
            super();
        }

        /**
         * returns the amount of columns, hard coded '4'
         * @return int
         */
        public int getColumnCount() {
            return 7;
        }

        /**
         * gets the amount of rows in the table
         * @return int
         */
        public int getRowCount() {
			int i;
			try {
				i = classList.size();
			}
			catch(NullPointerException e) {
				i = 1;
			}
			return i;
        }

        /**
         * gets the value displayed in a given row and col
         * @param row int
         * @param col int
         * @return Object
         */
        public Object getValueAt(int row, int col) {
            GymClass gymClass = classList.get(row);
            if (col == 0) {
                return gymClass.getName();
            } else if (col == 1) {
                return gymClass.getClassType();
            } else if (col == 2) {
                return gymClass.getRank();
            } else if (col == 3) {
                return gymClass.getStartTime().replace("_"," ");
            } else if (col ==4) {
                return gymClass.getClassLength()+" minutes";
            } else if (col ==5) {
                //return gymClass.getTrainers().get(0);
                return "TBD";
            } else if (col ==6) {
                try {
                    return gymClass.getMaxSize() - gymClass.getSize();
                } catch (NumberFormatException e){
                    return "Not Set";
                }
            } else if (col == EVENT_ID) {
                return gymClass.getID();
            }
            return gymClass;
        }

        /**
         * returns the columnName at a given column number, starts at 0
         * @param col int
         * @return String
         */
        public String getColumnName(int col) {
            return columnNames[col];
        }
    }
}
