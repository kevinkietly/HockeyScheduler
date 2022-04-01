package ui;

import delegates.MainWindowDelegate;
import model.goalie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class updatePanel extends JPanel {
    private static final Insets STANDARD_INSET = new Insets(10, 40, 0, 40);

    private JTextField playerIDField;
    private JTextField playerNameField;

    private MainWindowDelegate delegate;

    public updatePanel(MainWindowDelegate delegate) {
        this.delegate = delegate;
        setLayout(new GridBagLayout());
        playerIDField = new JTextField(20);
        playerNameField = new JTextField(20);
        JButton updateButton = new JButton("Update");
        JLabel updateGoalieLabel = new JLabel("Update Goalie");
        JLabel playerIDLabel = new JLabel("Player ID");
        JLabel playerNameLabel = new JLabel("Name");
        GridBagConstraints gbc = new GridBagConstraints();
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleInsert();
                System.out.println("updated");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(updateGoalieLabel, gbc);
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
        gbc.insets = new Insets(10, 40, 10, 40);
        gbc.anchor = GridBagConstraints.CENTER;
        add(updateButton, gbc);
    }

    private void handleInsert() {
        int player_id = Integer.parseInt(playerIDField.getText());
        String name = playerNameField.getText();
        goalie player = new goalie(player_id, name, -1, "", -1);
        delegate.updatePlayerName(player, name);
    }
}