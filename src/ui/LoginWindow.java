package ui;

import delegates.LoginWindowDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class LoginWindow extends JFrame implements ActionListener {
    private static final Insets TOP_BOTTOM_INSET = new Insets(40, 40, 40, 40);
    private static final Insets STANDARD_INSET = new Insets(10, 40, 0, 40);

    private JTextField usernameField;
    private JPasswordField passwordField;

    private LoginWindowDelegate loginWindowDelegate;

    public LoginWindow(LoginWindowDelegate loginWindowDelegate) {
        super("Login");
        this.loginWindowDelegate = loginWindowDelegate;
        initializeComponents();
    }

    public void initializeComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        passwordField.setEchoChar('●');
        JButton loginButton = new JButton("Login");
        JLabel hockeySchedulerLabel = new JLabel("Hockey Scheduler");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Font titleFont = new Font("Helvetica-Neue", Font.PLAIN, 24);
        hockeySchedulerLabel.setFont(titleFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = TOP_BOTTOM_INSET;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(hockeySchedulerLabel, gbc);
        gbc.gridy = 1;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(usernameLabel, gbc);
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(usernameField, gbc);
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(passwordLabel, gbc);
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(passwordField, gbc);
        gbc.gridy = 5;
        gbc.insets = TOP_BOTTOM_INSET;;
        loginPanel.add(loginButton, gbc);
        this.setContentPane(loginPanel);
        loginButton.addActionListener(this);
        SwingUtilities.getRootPane(this).setDefaultButton(loginButton);
        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void handleLoginFailed() {
        usernameField.setText(null);
        passwordField.setText(null);
        JOptionPane.showMessageDialog(this, "Incorrect username or password");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            loginWindowDelegate.login(usernameField.getText(), String.valueOf(passwordField.getPassword()));
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
