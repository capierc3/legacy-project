/**
 * RTFFileExport.java
 * Created on 25.02.2003, 17:55:08 Alex
 * Package: net.sf.memoranda.ui
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda.util;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

/**
 * A little bit louder now...
 */
/* $Id: RTFFileExport.java,v 1.3 2004/01/30 12:17:42 alexeya Exp $ */
public class RTFFileExport {

    /**
     * Constructor for RTFFileExport.
     */
    public RTFFileExport(File f, Document doc) {
	RTFEditorKit kit = new RTFEditorKit();
	try {
	    FileOutputStream file1 = new FileOutputStream(f);
	    kit.write(file1, (DefaultStyledDocument) doc, 0, doc.getLength());
	    file1.close();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

}
