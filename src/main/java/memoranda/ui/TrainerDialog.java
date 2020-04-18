package main.java.memoranda.ui;



import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.java.memoranda.util.Local;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


/*
 * A class to allow the ability to edit and add a new trainer in the trainer profiles page
 * @author Justin Oliver
 */


public class TrainerDialog extends JDialog implements WindowListener {
    public boolean CANCELLED = false;
    boolean ignoreStartChanged = false;
    boolean ignoreEndChanged = false;
    JPanel topPanel = new JPanel(new BorderLayout());
    JPanel bottomPanel = new JPanel(new BorderLayout());
    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JLabel header = new JLabel();
    JPanel eventPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc;
    String name; 
    String belt;
    String fact;
    
    // The three editable values, creating label and text field
    JLabel trainerName = new JLabel();
    JTextField trainerNameText = new JTextField();
    JLabel trainerBelt = new JLabel();
    JTextField trainerBeltText = new JTextField();
    JLabel trainerFact = new JLabel();
    JTextField trainerFactText = new JTextField();




    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    JButton okB = new JButton();
    JButton cancelB = new JButton();


    public TrainerDialog(Frame frame, String title, String name, String belt, String fact) {
        super(frame, title, true);
        try {
            this.name = name;
            this.belt = belt;
            this.fact = fact;
            jbInit();
            pack();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
        super.addWindowListener(this);
    }

    void jbInit() throws Exception {
        this.setResizable(false);
        // Build headerPanel
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("Edit Trainer"));
        header.setIcon(new ImageIcon(TrainerDialog.class.getResource(
            "/ui/icons/trainer-profiles.png")));
        headerPanel.add(header);

        // Build eventPanel 
        trainerName.setText("Name");
        trainerName.setMinimumSize(new Dimension(60, 24));
        trainerBelt.setText("Belt");
        trainerBelt.setMinimumSize(new Dimension(60, 24));
        trainerFact.setText("Fact");
        trainerFact.setMinimumSize(new Dimension(60, 24));

        trainerNameText.setText(name);
        trainerNameText.setMinimumSize(new Dimension(120, 24));
        newGbc(0,1,1);
        eventPanel.add(trainerName, gbc);
        gbc.gridx++;
        eventPanel.add(trainerNameText, gbc);
        
        
        trainerBeltText.setText(belt);
        trainerBeltText.setMinimumSize(new Dimension(120, 24));
        newGbc(0,2,1);
        eventPanel.add(trainerBelt, gbc);
        gbc.gridx++;
        eventPanel.add(trainerBeltText, gbc);
              
        trainerFactText.setText(fact);
        trainerFactText.setMinimumSize(new Dimension(120, 24));
        newGbc(0,3,1); 
        eventPanel.add(trainerFact, gbc);
        gbc.gridx++;
        eventPanel.add(trainerFactText, gbc);



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

    private void newGbc(int x, int y, int width){
        gbc = new GridBagConstraints();
        gbc.gridx = x; gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }



     

    void okB_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    void cancelB_actionPerformed(ActionEvent e) {
        CANCELLED = true;
        this.dispose();
    }


    
    public void windowOpened( WindowEvent e ) {}

    public void windowClosing( WindowEvent e ) {
        CANCELLED = true;
        this.dispose();
    }
    
    

      
      public String getTrainerName() { 
          return this.name; 
      }
      


     
    
    public void windowClosed( WindowEvent e ) {}

    public void windowIconified( WindowEvent e ) {}

    public void windowDeiconified( WindowEvent e ) {}

    public void windowActivated( WindowEvent e ) {}

    public void windowDeactivated( WindowEvent e ) {}

}