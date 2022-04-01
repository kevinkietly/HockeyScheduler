package ui;

import delegates.MainWindowDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class NAWGBPanel extends JPanel {
    private static final Insets STANDARD_INSET = new Insets(10, 40, 0, 40);

    private JTextField refIDField;

    private MainWindowDelegate delegate;

    public NAWGBPanel(MainWindowDelegate delegate) {
        this.delegate = delegate;
        setLayout(new GridBagLayout());
        refIDField = new JTextField(20);
        JButton updateButton = new JButton("Submit");
        JLabel updateGoalieLabel = new JLabel("Nested Aggregation with Group By on Ref ID");
        JLabel refIDLabel = new JLabel("Referee ID");
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
        add(refIDLabel, gbc);
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(refIDField, gbc);
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 40, 10, 40);
        gbc.anchor = GridBagConstraints.CENTER;
        add(updateButton, gbc);
    }

    private void handleInsert() {
        int ref_id = Integer.parseInt(refIDField.getText());
        HashMap<Integer,Integer> result = delegate.maxSeatsPerRef(ref_id);
        System.out.println(result);
    }
}