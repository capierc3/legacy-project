package main.java.memoranda.util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.memoranda.ui.AppFrame;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

/*$Id: Context.java,v 1.3 2004/01/30 12:17:42 alexeya Exp $*/
public class Context {
    
  public static LoadableProperties context = new LoadableProperties();

    /**
     * Initializer for Context
     */
  static {
    CurrentStorage.get().restoreContext();
    AppFrame.addExitListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CurrentStorage.get().storeContext();
            }
        });
  }

    /**
     * Method to return Object from context map
     * @param key
     * @return Object
     */
  public static Object get(Object key) {
    return context.get(key);
  }

    /**
     * Method to put an Object key and value into context map
     * @param key
     * @param value
     */
  public static void put(Object key, Object value) {
    context.put(key, value);
  }

}