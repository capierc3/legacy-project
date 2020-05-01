/**
 * This class is designed to display a panel and show the appropriate info
 * when the Resources.
 * It is meant to be used to house resources for the program.
 *
 * @author Justin Oliver
 *
 *
 */
package main.java.memoranda.ui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ResourcesPanel extends JPanel {
    TitledBorder title = BorderFactory.createTitledBorder("KAESEKUCHEN RESOURCES");
    FlowLayout flowlayout = new FlowLayout();
    JButton newResource = new JButton("Add A New Resource");

    public ResourcesPanel() {
        try {
            jbInit();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    void jbInit() throws Exception {
        this.setLayout(flowlayout);
        this.setBorder(title);
        this.add(newResource);

        newResource.addActionListener(e -> addResource());

    }

    /**
     * Method to add a new resource to this page Todo: create this method For now,
     * just displays an alert window
     */
    void addResource() {
        JOptionPane.showMessageDialog(null,
                "Coming soon....While you wait, " + "Can I offer you an egg in this trying time?", "Add Resource",
                JOptionPane.PLAIN_MESSAGE);
    }

}