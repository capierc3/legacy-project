/**
 * History.java
 * Created on 23.02.2003, 0:27:33 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.util.Vector;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import main.java.memoranda.util.Local;

/**
 *
 */
/* $Id: History.java,v 1.7 2006/10/31 15:34:14 hglas Exp $ */
public class History {

    static Vector _list = new Vector();
    static int p = -1;
    static Vector historyListeners = new Vector();
    static Object next = null;
    static Object prev = null;

    /**
     * Method to add HistoryItem to Vector _list
     * 
     * @param item
     */
    public static void add(HistoryItem item) {
	if (prev != null)
	    if (item.equals(prev))
		return;
	if (p < _list.size() - 1)
	    _list.setSize(p + 1);
	_list.add(item);
	p = _list.size() - 1;
	if (p > 0)
	    prev = _list.get(p - 1);
	else
	    prev = null;
	next = null;
	historyBackAction.update();
	historyForwardAction.update();
	/*
	 * System.out.println(); for (int i = 0; i < _list.size(); i++)
	 * System.out.println(((HistoryItem)_list.get(i)).getDate().toString());
	 * System.out.println(item.getDate().toShortString()+ " added");
	 */
	if (_list.size() > 99)
	    _list.remove(0);
    }

    /**
     * Method to roll back the HistoryItem and return the previous HistoryItem in
     * the _list
     * 
     * @return HistoryItem
     */
    public static HistoryItem rollBack() {
	Object n = prev;
	if (p > 1) {
	    p--;
	    prev = _list.get(p - 1);
	} else if (p > 0) {
	    p--;
	    prev = null;
	} else
	    prev = null;
	if (p < _list.size() - 1)
	    next = _list.get(p + 1);
	else
	    next = null;
	return (HistoryItem) n;
    }

    /**
     * Method to roll forward and return the next HistoryItem in the _list
     * 
     * @return HistoryItem
     */
    public static HistoryItem rollForward() {
	Object n = next;
	if (p < _list.size() - 1) {
	    p++;
	    if (p == 1)
		p++;
	    next = _list.get(p);
	} else
	    next = null;
	if (p > 0)
	    prev = _list.get(p - 1);
	else
	    prev = null;
	return (HistoryItem) n;
    }

    /**
     * Method to check if there is a previous HistoryItem to roll back to, and
     * returns a boolean
     * 
     * @return boolean
     */
    public static boolean canRollBack() {
	return prev != null;
    }

    /**
     * Method to check if there is a next HistoryItem to roll forward to and returns
     * a boolean
     * 
     * @return boolean
     */
    public static boolean canRollForward() {
	return next != null;
    }

    /**
     * Method to add a HistoryListener to the vector of historyListeners
     * 
     * @param hl
     */
    public static void addHistoryListener(HistoryListener hl) {
	historyListeners.add(hl);
    }

    /**
     * Method to remove Project from Project History and historyListeners
     * 
     * @param prj
     */
    public static void removeProjectHistory(Project prj) {
	Vector list = new Vector();
	String id;

	for (int i = 0; i < _list.size(); i++) {
	    id = (((HistoryItem) _list.elementAt(i)).getProject()).getID();
	    if (id.equals(prj.getID())) {
		list.add(_list.elementAt(i));
		p--;
		if (_list.elementAt(i).equals(prev)) {
		    if (p > 0)
			prev = _list.get(p - 1);
		    else
			prev = null;
		}
	    }
	}
	if (!list.isEmpty()) {
	    _list.removeAll(list);
	    if (p < 0) {
		p = 0;
	    }
	    _list.setSize(p);
	    next = null;
	    historyBackAction.update();
	    historyForwardAction.update();
	}
    }

    /**
     * Method to notify historyListeners of a HistoryItem
     * 
     * @param n
     */
    private static void notifyListeners(HistoryItem n) {
	for (int i = 0; i < historyListeners.size(); i++)
	    ((HistoryListener) historyListeners.get(i)).historyWasRolledTo(n);
    }

    /**
     * HistoryBackAction declared and initialized
     */
    public static final HistoryBackAction historyBackAction = new HistoryBackAction();

    /**
     * HistoryForwardAction declared and initialized
     */
    public static HistoryForwardAction historyForwardAction = new HistoryForwardAction();

    /**
     * Inner class HistoryBackAction
     */
    static class HistoryBackAction extends AbstractAction {

	/**
	 * Constructor for HistoryBackAction object
	 */
	public HistoryBackAction() {
	    super(Local.getString("History back"),
		    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/hist_back.png")));
	    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.ALT_MASK));
	    setEnabled(false);
	}

	/**
	 * Method to notify historyListeners that an action is performed on a Project
	 * and update HistoryForwardAction
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
	    notifyListeners(rollBack());
	    update();
	    historyForwardAction.update();
	}

	/*
	 * public boolean isEnabled() { return canRollBack(); }
	 */

	/**
	 * Method to update HistoryBackAction
	 */
	void update() {
	    if (canRollBack()) {
		setEnabled(true);

		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT);
		Date date = ((HistoryItem) prev).getDate().getDate();
		putValue(Action.SHORT_DESCRIPTION, Local.getString("Back to") + " " + sdf.format(date));

//                putValue(Action.SHORT_DESCRIPTION, Local.getString("Back to") + " " + ((HistoryItem) prev).getDate().toString());
	    } else {
		setEnabled(false);
		putValue(Action.SHORT_DESCRIPTION, Local.getString("Back"));
	    }
	}
    }

    /**
     * Inner class HistoryForwardAction
     */
    static class HistoryForwardAction extends AbstractAction {

	/**
	 * Constructor to create HistoryForwardAction object
	 */
	public HistoryForwardAction() {
	    super(Local.getString("History forward"),
		    new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/hist_forward.png")));
	    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.ALT_MASK));
	    setEnabled(false);
	}

	/**
	 * Method to notify historyListeners that an action is performed on a Project,
	 * and update the HistoryBackAction
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
	    notifyListeners(rollForward());
	    update();
	    historyBackAction.update();
	}

	/*
	 * public boolean isEnabled() { return canRollForward(); }
	 */

	/**
	 * Method to update HistoryBackAction
	 */
	void update() {
	    if (canRollForward()) {
		setEnabled(true);

		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT);
		Date date = ((HistoryItem) next).getDate().getDate();

		putValue(Action.SHORT_DESCRIPTION,
			// Local.getString("Forward to") + " " + ((HistoryItem)
			// next).getDate().toString());
			Local.getString("Forward to") + " " + sdf.format(date));
	    } else {
		setEnabled(false);
		putValue(Action.SHORT_DESCRIPTION, Local.getString("Forward"));
	    }
	}
    }

}
