/**
 * HistoryListener.java
 * Created on 23.02.2003, 1:56:52 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;

public interface HistoryListener {

    /*void historyWasRolledBack();
    
    void historyWasRolledForward();*/

    /**
     * Method to roll listener to the HistoryItem item passed in
     * @param item
     */
    void historyWasRolledTo(HistoryItem item);

}
