package ui;

import delegates.MainWindowDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePanel extends JPanel {
    private static final Insets STANDARD_INSET = new Insets(10, 40, 0, 40);
    private JTextField teamIDField;

    private MainWindowDelegate delegate;

    public DeletePanel(MainWindowDelegate delegate) {
        this.delegate = delegate;
        setLayout(new GridBagLayout());
        teamIDField = new JTextField(20);
        JButton deleteButton = new JButton("Delete");
        JLabel deleteTeamLabel = new JLabel("Delete Team");
        JLabel teamIDLabel = new JLabel("Team ID");
        GridBagConstraints gbc = new GridBagConstraints();
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDelete();
                System.out.println("deleted");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(deleteTeamLabel, gbc);
        gbc.gridy = 1;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(teamIDLabel, gbc);
        gbc.gridy = 2;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(teamIDField, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 40, 10, 40);
        gbc.anchor = GridBagConstraints.CENTER;
        add(deleteButton, gbc);
    }

    private void handleDelete() {
        int team_id = Integer.parseInt(teamIDField.getText());
        delegate.deleteTeam(team_id);
        teamIDField.setText(null);
    }
}