package ui;

import delegates.MainWindowDelegate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class JoinPanel extends JPanel {
    private static final Insets STANDARD_INSET = new Insets(10, 40, 0, 40);

    private JTextField coachNameField;
    private JList<String> result;
    private MainWindowDelegate delegate;

    public JoinPanel(MainWindowDelegate delegate) {
        this.delegate = delegate;
        setLayout(new GridBagLayout());
        coachNameField = new JTextField(20);
        JButton joinButton = new JButton("Join");
        JLabel joinCoachAndGoalieLabel = new JLabel("Join Coach and Goalie");
        JLabel coachNameLabel = new JLabel("Coach Name");
        GridBagConstraints gbc = new GridBagConstraints();
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleInsert();
                System.out.println("updated");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.CENTER;
        add(joinCoachAndGoalieLabel, gbc);
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(coachNameLabel, gbc);
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(coachNameField, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 40, 10, 40);
        gbc.anchor = GridBagConstraints.CENTER;
        add(joinButton, gbc);
    }

    private void handleInsert() {
        String name = coachNameField.getText();
        String[] goalies = delegate.goaliesUnderCoachName(name);
        System.out.println(Arrays.toString(goalies));
        String column[] = {"Name"};
        Object data[][] = new Object[goalies.length][1];
        for (int i = 0; i < goalies.length; i++) {
            data[i][0] = goalies[i];
        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        JFrame f = new JFrame("Join");
        JPanel panel = new JPanel();
        panel.add(pane);
        f.add(panel);
        f.setSize(500, 250);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}