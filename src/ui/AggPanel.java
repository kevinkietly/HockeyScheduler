package ui;

import delegates.MainWindowDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AggPanel extends JPanel {
    private static final Insets STANDARD_INSET = new Insets(10, 40, 0, 40);

    private MainWindowDelegate delegate;

    public AggPanel(MainWindowDelegate delegate) {
        this.delegate = delegate;
        setLayout(new GridBagLayout());
        JButton updateButton = new JButton("Maximum Seats");
        JLabel aggLabel = new JLabel("Maximum Seats of All Venues");
        JLabel retValueLabel = new JLabel();
        GridBagConstraints gbc = new GridBagConstraints();
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAgg(retValueLabel);
                System.out.println("selected");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(aggLabel, gbc);
        gbc.gridy = 1;
        gbc.insets = STANDARD_INSET;
        gbc.insets = new Insets(10, 40, 10, 40);
        gbc.anchor = GridBagConstraints.CENTER;
        add(updateButton, gbc);
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(retValueLabel, gbc);

    }

    private void handleAgg(JLabel retValeLabel) {
        int max = delegate.maxSeats();
        retValeLabel.setText(Integer.toString(max));
    }
}