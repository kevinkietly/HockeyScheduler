package ui;

import delegates.MainWindowDelegate;
import model.venue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionPanel extends JPanel {
    private static final Insets STANDARD_INSET = new Insets(10, 40, 0, 40);

    private JTextField numRoomsField;

    private MainWindowDelegate delegate;

    public SelectionPanel(MainWindowDelegate delegate) {
        this.delegate = delegate;
        setLayout(new GridBagLayout());
        numRoomsField = new JTextField(20);
        JButton updateButton = new JButton("Filter");
        JLabel selectLabel = new JLabel("Filter Venues with at Least a Number of Rooms");
        JLabel numLabel = new JLabel("Number of Rooms Requested");
        GridBagConstraints gbc = new GridBagConstraints();
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSelect();
                System.out.println("selected");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(selectLabel, gbc);
        gbc.gridy = 1;
        gbc.insets = STANDARD_INSET;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(numLabel, gbc);
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(numRoomsField, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 40, 10, 40);
        gbc.anchor = GridBagConstraints.CENTER;
        add(updateButton, gbc);
    }

    private void handleSelect() {
        int numRooms = Integer.parseInt(numRoomsField.getText());
        venue[] venues = delegate.selectSpaciousVenues(numRooms);
        for (venue v: venues) {
            System.out.println("Venue "+v.getId()+":");
            System.out.println("Address is :"+v.getAddress()+"\n");
        }
        String column[] = {"Venue ID", "Address", "Rooms", "Seats", "Number of Rinks"};
        Object data[][] = new Object[venues.length][5];
        for (int i = 0; i < venues.length; i++) {
            data[i][0] = venues[i].getId();
            data[i][1] = venues[i].getAddress();
            data[i][2] = venues[i].getNumRooms();
            data[i][3] = venues[i].getNumSeats();
            data[i][4] = venues[i].getNumRinks();
        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        JFrame f = new JFrame("Selection");
        JPanel panel = new JPanel();
        panel.add(pane);
        f.add(panel);
        f.setSize(500, 250);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}