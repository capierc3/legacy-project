/**
 * MimeTypesList.java
 * Created on 24.03.2003, 13:54:52 Alex
 * Package: net.sf.memoranda.util
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda.util;

import java.util.Vector;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
 * alrightt. 
 */
/* $Id: MimeTypesList.java,v 1.3 2004/01/30 12:17:42 alexeya Exp $ */
public class MimeTypesList {
    public static Document _doc = null;
    static Element _root = null;

    /**
     * Initializer of MimeTypesList
     */
    static {
	CurrentStorage.get().openMimeTypesList();
	_root = _doc.getRootElement();
    }

    /**
     * Method to build a vector with MimeTypes and return the Vector
     * 
     * @return Vector
     */
    public static Vector getAllMimeTypes() {
	Vector v = new Vector();
	Elements els = _root.getChildElements("mime-type");
	for (int i = 0; i < els.size(); i++)
	    v.add(new MimeType(els.get(i)));
	return v;
    }

    /**
     * Method to get MimeType for file based on the path passed in
     * 
     * @param path
     * @return MimeType
     */
    public static MimeType getMimeTypeForFile(String path) {
	return getMimeTypeByExt(getExtension(path));
    }

    /**
     * Method to get MimeType based on MimeId passed in
     * 
     * @param mimeId
     * @return MimeType
     */
    public static MimeType getMimeType(String mimeId) {
	Elements els = _root.getChildElements("mime-type");
	for (int i = 0; i < els.size(); i++)
	    if (els.get(i).getAttribute("id").getValue().equals(mimeId))
		return new MimeType(els.get(i));
	return new MimeType();
    }

    /**
     * Method to get MimeType by extension type String passed in
     * 
     * @param ext
     * @return MimeType
     */
    public static MimeType getMimeTypeByExt(String ext) {
	Elements els = _root.getChildElements("mime-type");
	for (int i = 0; i < els.size(); i++) {
	    Element el = els.get(i);
	    Elements exts = el.getChildElements("ext");
	    for (int j = 0; j < exts.size(); j++)
		if (exts.get(j).getValue().toLowerCase().equals(ext.toLowerCase()))
		    return new MimeType(el);
	}
	return new MimeType();
    }

    /**
     * Method to add and return MimeType based on MimeId passed in
     * 
     * @param mimeId
     * @return
     */
    public static MimeType addMimeType(String mimeId) {
	Element el = new Element("mime-type");
	el.addAttribute(new Attribute("id", mimeId));
	_root.appendChild(el);
	return new MimeType(el);
    }

    /**
     * Method to remove MimeType of MimeId passed in
     * 
     * @param mimeId
     */
    public static void removeMimeType(String mimeId) {
	Elements els = _root.getChildElements("mime-type");
	for (int i = 0; i < els.size(); i++)
	    if (els.get(i).getAttribute("id").getValue().equals(mimeId)) {
		_root.removeChild(els.get(i));
		return;
	    }
    }

    /**
     * Method to get the Applications
     * 
     * @return AppList
     */
    public static AppList getAppList() {
	return new AppList(_root.getChildElements("applications").get(0));
    }

    /**
     * Method to get and return a String with the document's extension type
     * 
     * @param s
     * @return String
     */
    public static String getExtension(String s) {
	String ext = null;
	int i = s.lastIndexOf('.');
	if (i > 0 && i < s.length() - 1) {
	    ext = s.substring(i + 1).toLowerCase();
	}
	return ext;
    }

}
