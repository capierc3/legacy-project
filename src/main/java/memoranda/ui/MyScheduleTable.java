package main.java.memoranda.ui;

import main.java.memoranda.EventsManager;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.gym.AppUsers;
import main.java.memoranda.gym.GymClass;
import main.java.memoranda.util.Local;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
/*$Id: EventsTable.java,v 1.6 2004/10/11 08:48:20 alexeya Exp $*/

/**
 *  Class for the main table that displays the Classes/Events for the software
 */
public class MyScheduleTable extends JTable {

    public static final int EVENT = 100;
    public static final int EVENT_ID = 101;

    MyScheduleManager manager;
    ArrayList<GymClass> classList;
    /**
     * Constructor for EventsTable.
     */
    MyScheduleTable() {
        super();
        this.manager = App.appUsers.getManager();
        setModel(new ClassTableModel());
        initTable(CurrentDate.get());
        this.setShowGrid(false);
        CurrentDate.addDateListener(this::initTable);
    }

    /**
     * sets up the table
     * @param d CalendarDate
     */
    void initTable(CalendarDate d) {
        System.out.println(manager == null);
        System.out.println(App.appUsers.getActiveUser().getName());
        classList = manager.getDaysClasses(d);
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
                comp.setForeground(Color.gray);
//                if (ev.isRepeatable())
//                    comp.setFont(comp.getFont().deriveFont(Font.ITALIC));
                if (CurrentDate.get().after(CalendarDate.today())) {
                  comp.setForeground(Color.black);
                } else if (CurrentDate.get().equals(CalendarDate.today())) {
                    if (gymClass.getStartDate().after(CalendarDate.today())) {
                        comp.setForeground(Color.black);
                        comp.setFont(comp.getFont().deriveFont(Font.BOLD));
                    } else if (gymClass.getStartDate().before(CalendarDate.today())){
                        comp.setForeground(Color.lightGray);
                    } else {
                        comp.setForeground(Color.black);
                    }
                }
                return comp;
            }
        };

    }

    /**
     * Nested class for the table to be used
     */
    class ClassTableModel extends AbstractTableModel {
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
        ClassTableModel() {
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
                return hoursDisplay(gymClass.getStartTime().replace("_"," "));
            } else if (col ==4) {
                return gymClass.getClassLength()+" minutes";
            } else if (col ==5) {
                return gymClass.getTrainer().getName();
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

        private String hoursDisplay(String disp){
            String[] dispSplit = disp.split(":");
            if (dispSplit[0].equalsIgnoreCase("0")){
                dispSplit[0] = "12";
            }
            String[] amPMSplit = dispSplit[1].split(" ");
            if (amPMSplit[0].length()==1){
                amPMSplit[0] = "0"+amPMSplit[0];
            }
            return dispSplit[0]+":"+amPMSplit[0]+" "+amPMSplit[1];
        }

    }
}
