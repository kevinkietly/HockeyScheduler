package ui;

import delegates.MainWindowDelegate;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPanel extends JPanel {
    private static final Insets STANDARD_INSET = new Insets(10, 40, 0, 40);

    private JComboBox comboBox;
    private MainWindowDelegate delegate;

    public ViewPanel(MainWindowDelegate delegate) {
        this.delegate = delegate;
        setLayout(new GridBagLayout());
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

        comboBoxModel.addElement(new String("All Players"));
        comboBoxModel.addElement(new String("Defense Players"));
        comboBoxModel.addElement(new String("Forward Players"));
        comboBoxModel.addElement(new String("Goalie Players"));
        comboBoxModel.addElement(new String("Coaches"));
        comboBoxModel.addElement(new String("Coach Since"));
        comboBoxModel.addElement(new String("Teams"));
        comboBoxModel.addElement(new String("Organizations"));
        comboBoxModel.addElement(new String("Games"));
        comboBoxModel.addElement(new String("Competes In"));
        comboBoxModel.addElement(new String("Venues"));
        comboBoxModel.addElement(new String("Referees"));
        comboBoxModel.addElement(new String("Regulates Game At"));
        comboBox = new JComboBox<String>(comboBoxModel);
        JButton projectButton = new JButton("View Table");
        JLabel projectLabel = new JLabel("Select Table to View");
        GridBagConstraints gbc = new GridBagConstraints();
        projectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleProjection();
                System.out.println("viewed");
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
        if (comboBox.getSelectedIndex() == 0) {
            player[] table = delegate.showPlayers();
            //Stub output to double-check functionality
            String column[] = {"ID", "Name"};
            Object data[][] = new Object[table.length][2];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getPlayerID();
                data[i][1] = table[i].getName();
            }
            displayTable(column, data, "All Players");
        } else if (comboBox.getSelectedIndex() == 1) {
            defense[] table = delegate.showDefenses();
            //Stub output to double-check functionality
            String column[] = {"ID", "Name"};
            Object data[][] = new Object[table.length][2];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getPlayerID();
                data[i][1] = table[i].getName();
            }
            displayTable(column, data, "Defensemen");
        } else if (comboBox.getSelectedIndex() == 2) {
            forward[] table = delegate.showForwards();
            String column[] = {"ID", "Name"};
            Object data[][] = new Object[table.length][2];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getPlayerID();
                data[i][1] = table[i].getName();
            }
            displayTable(column, data, "Forwards");
        } else if (comboBox.getSelectedIndex() == 3) {
            goalie[] table = delegate.showGoalies();
            String column[] = {"ID", "Name"};
            Object data[][] = new Object[table.length][2];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getPlayerID();
                data[i][1] = table[i].getName();
            }
            displayTable(column, data, "Goalies");
        } else if (comboBox.getSelectedIndex() == 4) {
            coach[] table = delegate.showCoaches();
            String column[] = {"ID", "Name"};
            Object data[][] = new Object[table.length][2];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getCoachID();
                data[i][1] = table[i].getName();
            }
            displayTable(column, data, "Coaches");
        } else if (comboBox.getSelectedIndex() == 5) {
            coach_since[] table = delegate.showCoachSince();
            String column[] = {"ID", "Coaching Since"};
            Object data[][] = new Object[table.length][2];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getTeamID();
                data[i][1] = table[i].getCoachingSince();
            }
            displayTable(column, data, "Coaching Since");
        } else if (comboBox.getSelectedIndex() == 6) {
            team[] table = delegate.showTeams();
            String column[] = {"Name", "ID", "Org ID"};
            Object data[][] = new Object[table.length][3];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getName();
                data[i][1] = table[i].getTeamID();
                data[i][2] = table[i].getOrgID();
            }
            displayTable(column, data, "Teams");
        } else if (comboBox.getSelectedIndex() == 7) {
            organization[] table = delegate.showOrganizations();
            String column[] = {"Name", "Org ID", "City"};
            Object data[][] = new Object[table.length][3];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getName();
                data[i][1] = table[i].getOrgID();
                data[i][2] = table[i].getCity();
            }
            displayTable(column, data, "Organizations");
        } else if (comboBox.getSelectedIndex() == 8) {
            game[] table = delegate.showGames();
            String column[] = {"Game ID", "Team 1 Score", "Team 2 Score"};
            Object data[][] = new Object[table.length][3];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getGameID();
                data[i][1] = table[i].getTeam1Score();
                data[i][2] = table[i].getTeam2Score();
            }
            displayTable(column, data, "Games");
        } else if (comboBox.getSelectedIndex() == 9) {
            competes_in[] table = delegate.showCompetesIn();
            String column[] = {"Game ID", "Team ID"};
            Object data[][] = new Object[table.length][2];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getGameID();
                data[i][1] = table[i].getTeamID();
            }
            displayTable(column, data, "Competes In");
        } else if (comboBox.getSelectedIndex() == 10) {
            venue[] table = delegate.showVenues();
            String column[] = {"Venue ID", "Address", "Rooms", "Seats", "Rinks"};
            Object data[][] = new Object[table.length][5];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getId();
                data[i][1] = table[i].getAddress();
                data[i][2] = table[i].getNumRooms();
                data[i][3] = table[i].getNumSeats();
                data[i][4] = table[i].getNumRinks();
            }
            displayTable(column, data, "Venues");
        } else if (comboBox.getSelectedIndex() == 11) {
            referee[] table = delegate.showReferees();
            String column[] = {"Referee ID", "Name"};
            Object data[][] = new Object[table.length][2];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getRefID();
                data[i][1] = table[i].getName();
            }
            displayTable(column, data, "Referees");
        } else if (comboBox.getSelectedIndex() == 12) {
            regulate_game_at[] table = delegate.showRegulateGameAt();
            String column[] = {"Venue ID", "Referee ID", "Game ID", "Date"};
            Object data[][] = new Object[table.length][4];
            for (int i = 0; i < table.length; i++) {
                data[i][0] = table[i].getVenueID();
                data[i][1] = table[i].getRefID();
                data[i][2] = table[i].getGameID();
                data[i][3] = table[i].getDate();
            }
            displayTable(column, data, "Regulates Game At");
        }
    }

    private void displayTable(String[] column, Object[][] data, String tableName) {
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        JFrame f = new JFrame(tableName);
        JPanel panel = new JPanel();
        panel.add(pane);
        f.add(panel);
        f.setSize(500, 250);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
