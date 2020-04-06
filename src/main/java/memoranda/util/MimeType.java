/**
 * MimeType.java
 * Created on 24.03.2003, 13:55:46 Alex
 * Package: net.sf.memoranda.util
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda.util;
import java.util.Vector;

import javax.swing.ImageIcon;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;
/**
 *
 */
/*$Id: MimeType.java,v 1.3 2004/01/30 12:17:42 alexeya Exp $*/
public class MimeType {

    public Element _root = null;

    /**
     * Constructor setting _root Element to the Element passed in
     * @param root
     */
    public MimeType(Element root) {
        _root = root;
    }

    /**
     * Constructor for MimeType.
     */
    public MimeType() {
      _root = new Element("default-type");
      _root.addAttribute(new Attribute("id", "__UNKNOWN"));
      _root.addAttribute(new Attribute("label", "Unknown"));
    }

    /**
     * Method to get MimeTypeId
     * @return String
     */
    public String getMimeTypeId() {
        return _root.getAttribute("id").getValue();
    }

    /**
     * Method to get the Extension
     * @return String
     */
    public String getExtension() {
        Elements exts = _root.getChildElements("ext");
        if (exts.size() > 0)
            return exts.get(0).getValue();
        return null;
    }

    /**
     * Method to get Extensions of the MimeType as a String[]
     * @return String[]
     */
    public String[] getExtensions() {
        Vector v = new Vector();
        String[] ss = {};
        Elements exts = _root.getChildElements("ext");
        for (int i = 0; i < exts.size(); i++)
            v.add(exts.get(i).getValue());
        return (String[]) v.toArray(ss);
    }

    /**
     * Method to add Extension to the list of MimeTypes
     * @param ext
     */
    public void addExtension(String ext) {
        Element exe = new Element("ext");
        exe.appendChild(ext);
        _root.appendChild(exe);
    }

    /**
     * Method to return the Label of the MimeType
     * @return String
     */
    public String getLabel() {
        if ((_root.getAttribute("label") != null) && (_root.getAttribute("label").getValue().length() >0))
          return _root.getAttribute("label").getValue();
        else
           return _root.getAttribute("id").getValue();
    }

    /**
     * Method to set the Label of the MimeType
     * @param label
     */
    public void setLabel(String label) {
        if (_root.getAttribute("label") != null)
         _root.getAttribute("label").setValue(label);
        else
        _root.addAttribute(new Attribute("label", label));
    }

    /**
     * Method to get AppId for the platform code passed in
     * @param plafCode
     * @return String
     */
    public String getAppId(String plafCode) {
        Elements apps = _root.getChildElements("app");
        for (int i = 0; i < apps.size(); i++)
            if (apps.get(i).getAttribute("platform").getValue().toLowerCase().equals(plafCode.toLowerCase()))
                return apps.get(i).getAttribute("appId").getValue();
        return null;
    }

    /**
     * Method to set App based on platform code and AppId passed in
     * @param plafCode
     * @param appId
     */
    public void setApp(String plafCode, String appId) {
        if (getAppId(plafCode) != null) {
            Elements apps = _root.getChildElements("app");
            for (int i = 0; i < apps.size(); i++)
                if (apps.get(i).getAttribute("platform").getValue().toLowerCase().equals(plafCode.toLowerCase()))
                    apps.get(i).getAttribute("appId").setValue(appId);
        }
        else {
            Element app = new Element("app");
            app.addAttribute(new Attribute("appId", appId));
            app.addAttribute(new Attribute("platform", plafCode));
            _root.appendChild(app);
        }
    }

    /**
     * Method to set App based on AppId passed in
     * @param appId
     */
    public void setApp(String appId) {
        setApp(AppList.getPlafCode(System.getProperty("os.name")), appId);
    }

    /**
     * Method to get AppId
     * @return String
     */
    public String getAppId() {
        String plaf = AppList.getPlafCode(System.getProperty("os.name"));
        return getAppId(plaf);
    }

    /**
     * Method to get Icon path for MimeType
     * @return String
     */
    public String getIconPath() {
        if (_root.getAttribute("icon") != null)
          return _root.getAttribute("icon").getValue();
        else
          return "";
    }

    /**
     * Method to set Icon path to String passed in for MimeType
     * @param path
     */
    public void setIconPath(String path) {
         if (_root.getAttribute("icon") != null)
          _root.getAttribute("icon").setValue(path);
        else
        _root.addAttribute(new Attribute("icon", path));
    }

    /**
     * Method to get ImageIcon of MimeType
     * @return
     */
    public ImageIcon getIcon() {
       String ip = getIconPath();
       ImageIcon icon = null;
       if (ip.equals("")) {
        ip = "/ui/icons/mimetypes/"+getMimeTypeId()+".png";
        try {
          icon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource(ip));
        }
        catch (Exception ex) {
          ip = "/ui/icons/mimetypes/"+getMimeTypeId().split("/")[0]+"/default.png";
          try {
            icon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource(ip));
          }
          catch (Exception ex2) {
            icon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/mimetypes/default.png"));
          }
        }
      }
      else
        try {
            icon = new ImageIcon(ip);
          }
          catch (Exception ex) {
          ip = "/ui/icons/mimetypes/"+getMimeTypeId().split("/")[0]+"/default.png";
          try {
            icon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource(ip));
          }
          catch (Exception ex2) {
            icon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/mimetypes/default.png"));
          }
        }
      return icon;
    }
}
