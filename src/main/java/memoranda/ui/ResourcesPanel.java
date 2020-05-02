/* 
 * This class is designed to display a panel and show the appropriate info when the resources button is clicked. 
 * This panel existed in the original version of memoranda that was used for this gym scheduling program
 * 
 * 
 */

package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.memoranda.CurrentProject;
import main.java.memoranda.Resource;
import main.java.memoranda.util.AppList;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.MimeType;
import main.java.memoranda.util.MimeTypesList;
import main.java.memoranda.util.Util;

import java.io.*;

/*$Id: ResourcesPanel.java,v 1.13 2007/03/20 08:22:41 alexeya Exp $*/
public class ResourcesPanel extends JPanel {
    BorderLayout borderLayout1 = new BorderLayout();
    JToolBar toolBar = new JToolBar();
    JButton newResB = new JButton();
    ResourcesTable resourcesTable = new ResourcesTable();
    JButton removeResB = new JButton();
    JScrollPane scrollPane = new JScrollPane();
    JButton refreshB = new JButton();
  JPopupMenu resPPMenu = new JPopupMenu();
  JMenuItem ppRun = new JMenuItem();
  JMenuItem ppRemoveRes = new JMenuItem();
  JMenuItem ppNewRes = new JMenuItem();
  JMenuItem ppRefresh = new JMenuItem();

    public ResourcesPanel() {
        try {
            jbInit();
        }
        catch (Exception ex) {
           new ExceptionDialog(ex);
        }
    }
    void jbInit() throws Exception {
        toolBar.setFloatable(false);
        this.setLayout(borderLayout1);
        newResB.setIcon(
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/addresource.png")));
        newResB.setEnabled(true);
        newResB.setMaximumSize(new Dimension(24, 24));
        newResB.setMinimumSize(new Dimension(24, 24));
        newResB.setToolTipText(Local.getString("New resource"));
        newResB.setRequestFocusEnabled(false);
        newResB.setPreferredSize(new Dimension(24, 24));
        newResB.setFocusable(false);
        newResB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newResB_actionPerformed(e);
            }
        });
        newResB.setBorderPainted(false);
        resourcesTable.setMaximumSize(new Dimension(32767, 32767));
        resourcesTable.setRowHeight(24);
        removeResB.setBorderPainted(false);
        removeResB.setFocusable(false);
        removeResB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeResB_actionPerformed(e);
            }
        });
        removeResB.setPreferredSize(new Dimension(24, 24));
        removeResB.setRequestFocusEnabled(false);
        removeResB.setToolTipText(Local.getString("Remove resource"));
        removeResB.setMinimumSize(new Dimension(24, 24));
        removeResB.setMaximumSize(new Dimension(24, 24));
        removeResB.setIcon(
            new ImageIcon(
                main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/removeresource.png")));
        removeResB.setEnabled(false);
        scrollPane.getViewport().setBackground(Color.white);
        toolBar.addSeparator(new Dimension(8, 24));
        toolBar.addSeparator(new Dimension(8, 24));


        PopupListener ppListener = new PopupListener();
        scrollPane.addMouseListener(ppListener);
        resourcesTable.addMouseListener(ppListener);

        resourcesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                boolean enbl = (resourcesTable.getRowCount() > 0) && (resourcesTable.getSelectedRow() > -1);

                removeResB.setEnabled(enbl); ppRemoveRes.setEnabled(enbl);
                ppRun.setEnabled(enbl);
            }
        });
        refreshB.setBorderPainted(false);
        refreshB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshB_actionPerformed(e);
            }
        });
        refreshB.setFocusable(false);
        refreshB.setPreferredSize(new Dimension(24, 24));
        refreshB.setRequestFocusEnabled(false);
        refreshB.setToolTipText(Local.getString("Refresh"));
        refreshB.setMinimumSize(new Dimension(24, 24));
        refreshB.setMaximumSize(new Dimension(24, 24));
        refreshB.setEnabled(true);
        refreshB.setIcon(
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/refreshres.png")));
        resPPMenu.setFont(new java.awt.Font("Dialog", 1, 10));
    ppRun.setFont(new java.awt.Font("Dialog", 1, 11));
    ppRun.setText(Local.getString("Open resource")+"...");
    ppRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRun_actionPerformed(e);
            }
        });
    ppRun.setEnabled(false);

    ppRemoveRes.setFont(new java.awt.Font("Dialog", 1, 11));
    ppRemoveRes.setText(Local.getString("Remove resource"));
    ppRemoveRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRemoveRes_actionPerformed(e);
            }
        });
    ppRemoveRes.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/removeresource.png")));
    ppRemoveRes.setEnabled(false);
    ppNewRes.setFont(new java.awt.Font("Dialog", 1, 11));
    ppNewRes.setText(Local.getString("New resource")+"...");
    ppNewRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppNewRes_actionPerformed(e);
            }
        });
    ppNewRes.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/addresource.png")));

    ppRefresh.setFont(new java.awt.Font("Dialog", 1, 11));
    ppRefresh.setText(Local.getString("Refresh"));
    ppRefresh.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ppRefresh_actionPerformed(e);
      }
    });
    ppRefresh.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/refreshres.png")));

    toolBar.add(newResB, null);
        toolBar.add(removeResB, null);
        toolBar.addSeparator();
        toolBar.add(refreshB, null);
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().add(resourcesTable, null);
        this.add(toolBar, BorderLayout.NORTH);
    resPPMenu.add(ppRun);
    resPPMenu.addSeparator();
    resPPMenu.add(ppNewRes);
    resPPMenu.add(ppRemoveRes);
    resPPMenu.addSeparator();
    resPPMenu.add(ppRefresh);
	
		// remove resources using the DEL key
		resourcesTable.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e){
				if(resourcesTable.getSelectedRows().length>0 
					&& e.getKeyCode()==KeyEvent.VK_DELETE)
					ppRemoveRes_actionPerformed(null);
			}
			public void	keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){} 
		});
    }

    void newResB_actionPerformed(ActionEvent e) {
        AddResourceDialog dlg = new AddResourceDialog(App.getFrame(), Local.getString("New resource"));
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        if (dlg.localFileRB.isSelected()) {
            String fpath = dlg.pathField.getText();
            MimeType mt = MimeTypesList.getMimeTypeForFile(fpath);
            if (mt.getMimeTypeId().equals("__UNKNOWN")) {
                mt = addResourceType(fpath);
                if (mt == null)
                    return;
            }
            if (!checkApp(mt))
                return;
            // if file if projectFile, than copy the file and change url.
            if (dlg.projectFileCB.isSelected()) {
            	fpath = copyFileToProjectDir(fpath);
            	CurrentProject.getResourcesList().addResource(fpath, false, true);
            }
            else
            	CurrentProject.getResourcesList().addResource(fpath);            	     	
            
            resourcesTable.tableChanged();
        }
        else {
            if (!Util.checkBrowser())
                return;
            CurrentProject.getResourcesList().addResource(dlg.urlField.getText(), true, false);
            resourcesTable.tableChanged();
        }
    }

    void removeResB_actionPerformed(ActionEvent e) {
        int[] toRemove = resourcesTable.getSelectedRows();
        String msg = "";
        if (toRemove.length == 1)
            msg =
                Local.getString("Remove the shortcut to resource")
                    + "\n'"
                    + resourcesTable.getModel().getValueAt(toRemove[0], 0)
                    + "'";

        else
            msg = Local.getString("Remove") + " " + toRemove.length + " " + Local.getString("shortcuts");
        msg +=
            "\n"
            + Local.getString("Are you sure?");
        int n =
            JOptionPane.showConfirmDialog(
                App.getFrame(),
                msg,
                Local.getString("Remove resource"),
                JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION)
            return;
        for (int i = 0; i < toRemove.length; i++) {        	
        		CurrentProject.getResourcesList().removeResource(
                        ((Resource) resourcesTable.getModel().getValueAt(toRemove[i], ResourcesTable._RESOURCE)).getPath());
        }
        resourcesTable.tableChanged();
    }
}