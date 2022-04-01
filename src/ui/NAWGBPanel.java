package ui;

import delegates.MainWindowDelegate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class NAWGBPanel extends JPanel {
    private static final Insets STANDARD_INSET = new Insets(10, 40, 0, 40);

    private MainWindowDelegate delegate;

    public NAWGBPanel(MainWindowDelegate delegate) {
        this.delegate = delegate;
        setLayout(new GridBagLayout());
        JButton submitButton = new JButton("Submit");
        JLabel NAWGBLabel = new JLabel("Nested Aggregation with Group By on Ref ID");
        GridBagConstraints gbc = new GridBagConstraints();
        submitButton.addActionListener(new ActionListener() {
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
        add(NAWGBLabel, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 40, 10, 40);
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);
    }

    private void handleInsert() {
        HashMap<Integer,Integer> result = delegate.maxSeatsPerRef();
        String column[] = {"Referee ID", "Max Seats"};
        Object data[][] = new Object[result.size()][2];
        int k = 0;
        for (Integer j : result.keySet()) {
            data[k][0] = j;
            data[k][1] = result.get(j);
            k++;
        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        JFrame f = new JFrame("Nested Aggregation with Group By");
        JPanel panel = new JPanel();
        panel.add(pane);
        f.add(panel);
        f.setSize(500, 250);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}