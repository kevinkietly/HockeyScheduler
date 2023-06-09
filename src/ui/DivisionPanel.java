package ui;

import delegates.MainWindowDelegate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class DivisionPanel extends JPanel {
    private static final Insets STANDARD_INSET = new Insets(10, 40, 0, 40);

    private MainWindowDelegate delegate;

    public DivisionPanel(MainWindowDelegate delegate) {
        this.delegate = delegate;
        setLayout(new GridBagLayout());
        JButton divideButton = new JButton("Divide");
        JLabel divideLabel = new JLabel("Find all teams that have participated in all games");
        GridBagConstraints gbc = new GridBagConstraints();
        divideButton.addActionListener(new ActionListener() {
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
        add(divideLabel, gbc);
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 40, 10, 40);
        gbc.anchor = GridBagConstraints.CENTER;
        add(divideButton, gbc);
    }

    private void handleInsert() {
        String[] result = delegate.allGameParticipants();
        System.out.println(Arrays.toString(result));

        String column[] = {"Team ID"};
        Object data[][] = new Object[result.length][1];
        for (int i = 0; i < result.length; i++) {
            data[i][0] = result[i];
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