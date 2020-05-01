/**
 * CurrentStorage.java
 * Created on 13.02.2003, 18:30:59 Alex
 * Package: net.sf.memoranda.util
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Vector;

/**
 * hello.
 */
/* $Id: CurrentStorage.java,v 1.3 2004/01/30 12:17:42 alexeya Exp $ */
public class CurrentStorage {

    /**
     * @todo: implement storage configuration
     */

    private static Storage _storage = new FileStorage();

    private static Vector actionListeners = new Vector();

    /**
     * Method to get the current Storage
     * 
     * @return Storage
     */
    public static Storage get() {
	return _storage;
    }

    /**
     * Method to set the current Storage
     * 
     * @param storage
     */
    public static void set(Storage storage) {
	_storage = storage;
	storageChanged();
    }

    /**
     * Method to add a ChangeListenerr to current actionListeners Vector
     * 
     * @param al
     */
    public static void addChangeListener(ActionListener al) {
	actionListeners.add(al);
    }

    /**
     * Method to return the Vector of current ChangeListeners
     * 
     * @return Collection
     */
    public static Collection getChangeListeners() {
	return actionListeners;
    }

    /**
     * Method to notify actionListeners that the current Storage has changed
     */
    private static void storageChanged() {
	for (int i = 0; i < actionListeners.size(); i++)
	    ((ActionListener) actionListeners.get(i))
		    .actionPerformed(new ActionEvent(null, 0, "Current storage changed"));
    }

}
