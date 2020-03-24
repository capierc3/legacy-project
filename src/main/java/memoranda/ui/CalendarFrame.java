package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.border.Border;

import main.java.memoranda.util.Local;

/*$Id: CalendarFrame.java,v 1.5 2004/04/05 10:05:44 alexeya Exp $*/
public class CalendarFrame extends JInternalFrame {

  public JNCalendarPanel cal = new JNCalendarPanel();
  Border border1;

  public CalendarFrame() {
    try {
      jbInit();
    }
    catch(Exception e) {
      new ExceptionDialog(e);
    }

  }
  private void jbInit() throws Exception {                           // changed color from grey to red
    border1 = BorderFactory.createLineBorder(Color.red,2); // changed border thickness from 1 to 2
    this.setClosable(true);
    this.setTitle(Local.getString("Choose date"));              // changed text from "Select" to "Choose"
    this.setBorder(border1);
    //this.setPreferredSize(new Dimension(200, 200));
    this.setToolTipText("");
    cal.setPreferredSize(new Dimension(this.getContentPane().getWidth(),
    this.getContentPane().getHeight()));
    this.getContentPane().add(cal,  BorderLayout.CENTER);
  }
}