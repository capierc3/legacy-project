package main.java.memoranda.ui;

import main.java.memoranda.gym.AppUsers;
import main.java.memoranda.gym.User;
import main.java.memoranda.util.Local;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginDialog extends JDialog implements WindowListener {

    JLabel lblLogin;
    JTextField txtLogin;
    JLabel lblPassword;
    JTextField txtPassword;
    JButton btnSubmit;
    JPanel topPanel = new JPanel(new BorderLayout());
    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JLabel header = new JLabel();
    JPanel mainPanel = new JPanel(new GridBagLayout());
    AppUsers appUsers;
    GridBagConstraints gbc;
    boolean error;



    public LoginDialog(Frame frame, String title,AppUsers appUsers) {
        super(frame, title, true);
        try {
            this.appUsers = appUsers;
            jbInit();
            pack();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
        super.addWindowListener(this);
    }

    void jbInit(){
        error = false;
        this.setResizable(false);
        // Build headerPanel
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("Login"));
        header.setIcon(new ImageIcon(main.java.memoranda.ui.EventDialog.class.getResource(
                "/ui/icons/personal_info.png")));
        headerPanel.add(header);

        lblLogin = new JLabel("LOGIN:");
        txtLogin = new JTextField();
        txtLogin.setColumns(10);
        lblPassword = new JLabel("PASSWORD:");
        txtPassword = new JTextField();
        txtPassword.setColumns(10);
        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginSubmitButton_clicked(txtLogin.getText(),txtPassword.getText());
            }
        });
        gbc = newGbc(0,0);
        mainPanel.add(lblLogin,gbc);
        gbc = newGbc(1,0);
        mainPanel.add(txtLogin,gbc);
        gbc = newGbc(0,1);
        mainPanel.add(lblPassword,gbc);
        gbc = newGbc(1,1);
        mainPanel.add(txtPassword,gbc);
        gbc = newGbc(2,3);
        mainPanel.add(btnSubmit,gbc);
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(mainPanel, BorderLayout.SOUTH);
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEvent -> {
            boolean keyHandled = false;
            if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!error) {
                        loginSubmitButton_clicked(txtLogin.getText(), txtPassword.getText());
                        keyHandled = true;
                    }
                }
            }
            return keyHandled;
        });
    }

    private GridBagConstraints newGbc(int x, int y){
        gbc = new GridBagConstraints();
        gbc.gridx = x; gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private void loginSubmitButton_clicked(String login, String password){

        String verified = appUsers.verifyPassword(login, password);

        if(verified.equalsIgnoreCase("found")) {
            User user = appUsers.getUser(login);
            appUsers.setActiveUser(user);
            this.dispose();
        }else {
            error = true;
            int res = JOptionPane.showOptionDialog(null,verified,"Incorrect Login",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,null,null,null);
            if (res == 0) {
                error = false;
            }
        }

    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}