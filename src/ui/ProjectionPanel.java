package ui;

import delegates.MainWindowDelegate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectionPanel extends JPanel {
    private static final Insets STANDARD_INSET = new Insets(10, 40, 0, 40);

    private JComboBox comboBox;
    private MainWindowDelegate delegate;

    public ProjectionPanel(MainWindowDelegate delegate) {
        this.delegate = delegate;
        setLayout(new GridBagLayout());
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        comboBoxModel.addElement(new String("Team Names"));
        comboBoxModel.addElement(new String("Team IDs"));
        comboBoxModel.addElement(new String("Team Org IDs"));
        comboBox = new JComboBox<String>(comboBoxModel);
        JButton projectButton = new JButton("Project");
        JLabel projectLabel = new JLabel("Projection from Team table");
        GridBagConstraints gbc = new GridBagConstraints();
        projectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleProjection();
                System.out.println("projected");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(projectLabel, gbc);
        gbc.gridy = 1;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.CENTER;
        add(comboBox, gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 40, 10, 40);;
        gbc.anchor = GridBagConstraints.CENTER;
        add(projectButton, gbc);
    }

    private void handleProjection() {
        String column[];
        Object[] rs;
        if (comboBox.getSelectedIndex() == 0) {
            rs = delegate.getTeamProjection("name");
            column = new String[]{"Team Name"};
        } else if (comboBox.getSelectedIndex() == 1) {
            rs = delegate.getTeamProjection("team_id");
            column = new String[]{"Team ID"};
        } else {
            rs = delegate.getTeamProjection("org_id");
            column = new String[]{"Team Org ID"};
        }
        Object data[][] = new Object[rs.length][1];
        for (int i = 0; i < rs.length; i++) {
            System.out.println(rs[i]);
            data[i][0] = rs[i];
        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        JFrame f = new JFrame("Projection");
        JPanel panel = new JPanel();
        panel.add(pane);
        f.add(panel);
        f.setSize(500, 250);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
