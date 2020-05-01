package main.java.memoranda.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import main.java.memoranda.gym.Belt;
import main.java.memoranda.util.Local;


/**
 * A class to allow the ability to edit and add a new trainer in the trainer profiles page.
 * @author Justin Oliver
 */
public class StudentDialog extends JDialog implements WindowListener {
    public boolean cancelled = false;
    JPanel topPanel = new JPanel(new BorderLayout());
    JPanel bottomPanel = new JPanel(new BorderLayout());
    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JLabel header = new JLabel();
    JPanel eventPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc;
    String name;
    Belt belt;
    String fact;
    String title;

    // The three editable values, creating label and text field
    JLabel studentName = new JLabel();
    JTextField studentNameText = new JTextField("", 20);
    JLabel studentBelt = new JLabel();
    JComboBox beltBox;




    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    JButton okB = new JButton();
    JButton cancelB = new JButton();

    /**
     * Constructor for the pop up dialog.
     * @param frame Frame
     * @param title String
     * @param name String
     * @param belt Belt
     */
    public StudentDialog(Frame frame, String title, String name, Belt belt) {
        super(frame, title, true);
        try {
            this.name = name;
            this.belt = belt;
            this.fact = fact;
            this.title = title;
            jbInit();
            pack();
        } catch (Exception ex) {
            new ExceptionDialog(ex);
        }
        super.addWindowListener(this);
    }

    void jbInit() throws Exception {
        this.setResizable(false);
        // Build headerPanel
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        header.setFont(new Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(title);
        header.setIcon(new ImageIcon(StudentDialog.class.getResource(
            "/ui/icons/trainer-profiles.png")));
        headerPanel.add(header);

        // Build eventPanel 
        studentName.setText("Name");
        studentName.setMinimumSize(new Dimension(60, 24));

        studentBelt.setText("Belt");
        studentBelt.setMinimumSize(new Dimension(60, 24));

        studentNameText.setText(name);
        studentNameText.setMinimumSize(new Dimension(120, 24));
        newGbc(0,1,1);
        eventPanel.add(studentName, gbc);
        gbc.gridx++;
        eventPanel.add(studentNameText, gbc);

        beltBox = new JComboBox(Belt.values());
        beltBox.setSelectedItem(belt);
        beltBox.setMinimumSize(new Dimension(120, 24));
        newGbc(0,2,1);
        eventPanel.add(studentBelt, gbc);
        gbc.gridx++;
        eventPanel.add(beltBox, gbc);


        // Build ButtonsPanel
        okB.setMaximumSize(new Dimension(100, 26));
        okB.setMinimumSize(new Dimension(100, 26));
        okB.setPreferredSize(new Dimension(100, 26));
        okB.setText(Local.getString("Ok"));
        okB.addActionListener(this::okB_actionPerformed);
        this.getRootPane().setDefaultButton(okB);
        cancelB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelB_actionPerformed(e);
            }
        });
        cancelB.setText(Local.getString("Cancel"));
        cancelB.setPreferredSize(new Dimension(100, 26));
        cancelB.setMinimumSize(new Dimension(100, 26));
        cancelB.setMaximumSize(new Dimension(100, 26));
        buttonsPanel.add(okB);
        buttonsPanel.add(cancelB);
        
        // Finally build the Dialog
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(eventPanel, BorderLayout.SOUTH);
        //bottomPanel.add(repeatPanel, BorderLayout.NORTH);
        bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    private void newGbc(int x, int y, int width) {
        gbc = new GridBagConstraints();
        gbc.gridx = x; 
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }


    void okB_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    void cancelB_actionPerformed(ActionEvent e) {
        cancelled = true;
        this.dispose();
    }


    
    public void windowOpened(WindowEvent e) {
        
    }

    public void windowClosing(WindowEvent e) {
        cancelled = true;
        this.dispose();
    }

    public String getStudentName() {
        return this.name; 
    }

    
    public void windowClosed(WindowEvent e) {}

    public void windowIconified(WindowEvent e) {}

    public void windowDeiconified(WindowEvent e) {}

    public void windowActivated(WindowEvent e) {}

    public void windowDeactivated(WindowEvent e) {}

}