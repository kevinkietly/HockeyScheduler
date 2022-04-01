package ui;

import delegates.MainWindowDelegate;
import model.*;

import javax.swing.*;
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
            for (player i: table) {
                System.out.println("Player "+i.getPlayerID()+":");
                System.out.println("Unique Description is: "+i.getName()+"\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        } else if (comboBox.getSelectedIndex() == 1) {
            defense[] table = delegate.showDefenses();
            //Stub output to double-check functionality
            for (defense i: table) {
                System.out.println("Defense Player "+i.getPlayerID()+":");
                System.out.println("Unique Description is: "+i.getName()+"\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        } else if (comboBox.getSelectedIndex() == 2) {
            forward[] table = delegate.showForwards();
            //Stub output to double-check functionality
            for (forward i: table) {
                System.out.println("Forward Player "+i.getPlayerID()+":");
                System.out.println("Unique Description is: "+i.getName()+"\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        } else if (comboBox.getSelectedIndex() == 3) {
            goalie[] table = delegate.showGoalies();
            //Stub output to double-check functionality
            for (player i: table) {
                System.out.println("Goalie Player "+i.getPlayerID()+":");
                System.out.println("Unique Description is: "+i.getName()+"\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        } else if (comboBox.getSelectedIndex() == 4) {
            coach[] table = delegate.showCoaches();
            //Stub output to double-check functionality
            for (coach i: table) {
                System.out.println("Player "+i.getCoachID()+":");
                System.out.println("Unique Description is: "+i.getName()+"\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        } else if (comboBox.getSelectedIndex() == 5) {
            coach_since[] table = delegate.showCoachSince();
            //Stub output to double-check functionality
            for (coach_since i: table) {
                System.out.println("Coaching Since "+i.getCoachingSince()+":");
                System.out.println("For Team "+i.getTeamID()+"\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        } else if (comboBox.getSelectedIndex() == 6) {
            team[] table = delegate.showTeams();
            //Stub output to double-check functionality
            for (team i: table) {
                System.out.println("Team "+i.getTeamID()+":");
                System.out.println("Unique Description is: "+i.getName()+"\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        } else if (comboBox.getSelectedIndex() == 7) {
            organization[] table = delegate.showOrganizations();
            //Stub output to double-check functionality
            for (organization i: table) {
                System.out.println("Organization "+i.getOrgID()+":");
                System.out.println("Unique Description is: "+i.getName()+"\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        } else if (comboBox.getSelectedIndex() == 8) {
            game[] table = delegate.showGames();
            //Stub output to double-check functionality
            for (game i: table) {
                System.out.println("Player "+i.getGameID()+":");
                System.out.println("Unique Description is: "+i.getTeam1ID()+" and "+i.getTeam2ID()+"\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        } else if (comboBox.getSelectedIndex() == 9) {
            competes_in[] table = delegate.showCompetesIn();
            //Stub output to double-check functionality
            for (competes_in i: table) {
                System.out.println("Team: "+i.getTeamID());
                System.out.println("Competes in Game: "+i.getGameID()+"\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        } else if (comboBox.getSelectedIndex() == 10) {
            venue[] table = delegate.showVenues();
            //Stub output to double-check functionality
            for (venue i: table) {
                System.out.println("Player "+i.getId()+":");
                System.out.println("Unique Description is: "+i.getAddress()+"\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        } else if (comboBox.getSelectedIndex() == 11) {
            referee[] table = delegate.showReferees();
            //Stub output to double-check functionality
            for (referee i: table) {
                System.out.println("Player "+i.getRefID()+":");
                System.out.println("Unique Description is: "+i.getName()+"\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        } else if (comboBox.getSelectedIndex() == 12) {
            regulate_game_at[] table = delegate.showRegulateGameAt();
            //Stub output to double-check functionality
            for (regulate_game_at i: table) {
                System.out.println("Referee "+i.getRefID()+
                        " refs for Game "+i.getGameID()+
                        " at "+i.getVenueID()+
                        " at "+i.getDate()+
                        "\n");
            }
            // Scroll-Down Table Display - TO BE IMPLEMENTED
        }
    }
}
