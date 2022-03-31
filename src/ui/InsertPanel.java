package ui;

import delegates.InsertDelegate;
import model.Forward;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertPanel extends JPanel {
    private static final Insets STANDARD_INSET = new Insets(10, 40, 0, 40);

    private JTextField playerIDField;
    private JTextField playerNameField;
    private JTextField playerNumField;
    private JTextField playsInSinceField;
    private JTextField teamIDField;

    private InsertDelegate delegate;

    public InsertPanel(InsertDelegate delegate) {
        this.delegate = delegate;
        setLayout(new GridBagLayout());
        playerIDField = new JTextField(20);
        playerNameField = new JTextField(20);
        playerNumField = new JTextField(20);
        playsInSinceField = new JTextField(20);
        teamIDField = new JTextField(20);
        JButton insertButton = new JButton("Insert");
        JLabel insertForwardLabel = new JLabel("Insert Forward");
        JLabel playerIDLabel = new JLabel("Player ID");
        JLabel playerNameLabel = new JLabel("Name");
        JLabel playerNumLabel = new JLabel("Number");
        JLabel playsInSinceLabel = new JLabel("Plays In Since");
        JLabel teamIDLabel = new JLabel("Team ID");
        GridBagConstraints gbc = new GridBagConstraints();
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleInsert();
                System.out.println("inserted");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(insertForwardLabel, gbc);
        gbc.gridy = 1;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(playerIDLabel, gbc);
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(playerIDField, gbc);
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(playerNameLabel, gbc);
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(playerNameField, gbc);
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(playerNumLabel, gbc);
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        add(playerNumField, gbc);
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(playsInSinceLabel, gbc);
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        add(playsInSinceField, gbc);
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(teamIDLabel, gbc);
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        add(teamIDField, gbc);
        gbc.gridy = 11;
        gbc.insets = new Insets(10, 40, 10, 40);
        gbc.anchor = GridBagConstraints.CENTER;
        add(insertButton, gbc);
    }

    private void handleInsert() {
        int player_id = Integer.parseInt(playerIDField.getText());
        String name = playerNameField.getText();
        int number = Integer.parseInt(playerNumField.getText());
        String plays_in_since = playsInSinceField.getText();
        int team_id = Integer.parseInt(teamIDField.getText());
        Forward player = new Forward(player_id, name, number, plays_in_since, team_id);
        delegate.insertPlayer(player);
    }
}
