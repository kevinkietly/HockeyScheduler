package database;

import model.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import util.PrintablePreparedStatement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseConnectionHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private Connection connection;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    // insert a Goalie, given a Goalie p
    // (Called from GUI)
    public void insertPlayer(goalie p) {
        try {
            String query = "INSERT INTO goalie VALUES (?,?,?,?,?)";
            insertPlayer(query, p);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    // insert a Defense, given a Defense p
    // (Called from GUI)
    public void insertPlayer(defense p) {
        try {
            String query = "INSERT INTO defense VALUES (?,?,?,?,?)";
            insertPlayer(query, p);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    // insert a Forward, given a Forward p
    // (Called from GUI)
    public void insertPlayer(forward p) {
        try {
            String query = "INSERT INTO forward VALUES (?,?,?,?,?)";
            insertPlayer(query, p);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    // Helper Function to abstract duplicate code
    private void insertPlayer(String query, player p) throws SQLException {
        PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
        ps.setInt(1, p.getPlayerID());
        ps.setString(2, p.getName());
        ps.setInt(3, p.getNumber());
        ps.setString(4, p.getPlays_in_since());
        ps.setInt(5, p.getTeamID());

        ps.executeUpdate();
        connection.commit();

        ps.close();
    }

    public void deleteTeam(int team_id) {
        try {
            String query1 = "DELETE FROM competes_in WHERE team_id = ?";
            String query2 = "DELETE FROM coach WHERE team_id = ?";
            String query3 = "DELETE FROM coach_since WHERE team_id = ?";
            String query4 = "DELETE FROM goalie WHERE team_id = ?";
            String query5 = "DELETE FROM forward WHERE team_id = ?";
            String query6 = "DELETE FROM defense WHERE team_id = ?";
            String query7 = "DELETE FROM team WHERE team_id = ?";
            PrintablePreparedStatement ps1 = new PrintablePreparedStatement(connection.prepareStatement(query1), query1, false);
            PrintablePreparedStatement ps2 = new PrintablePreparedStatement(connection.prepareStatement(query2), query2, false);
            PrintablePreparedStatement ps3 = new PrintablePreparedStatement(connection.prepareStatement(query3), query3, false);
            PrintablePreparedStatement ps4 = new PrintablePreparedStatement(connection.prepareStatement(query4), query4, false);
            PrintablePreparedStatement ps5 = new PrintablePreparedStatement(connection.prepareStatement(query5), query5, false);
            PrintablePreparedStatement ps6 = new PrintablePreparedStatement(connection.prepareStatement(query6), query6, false);
            PrintablePreparedStatement ps7 = new PrintablePreparedStatement(connection.prepareStatement(query7), query7, false);
            ps1.setInt(1, 1);
            ps2.setInt(1, 1);
            ps3.setInt(1, 1);
            ps4.setInt(1, 1);
            ps5.setInt(1, 1);
            ps6.setInt(1, 1);
            ps7.setInt(1, 1);
            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps4.executeUpdate();
            ps5.executeUpdate();
            ps6.executeUpdate();
            ps7.executeUpdate();
            connection.commit();
            ps1.close();
            ps2.close();
            ps3.close();
            ps4.close();
            ps5.close();
            ps6.close();
            ps7.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updatePlayerName(defense p, String name) {
        updatePlayerName(p, name, "defense");
    }

    public void updatePlayerName(goalie p, String name) {
        updatePlayerName(p, name, "goalie");
    }

    public void updatePlayerName(forward p, String name) {
        updatePlayerName(p, name, "forward");
    }

    private void updatePlayerName(player p, String name,String ptype) {
        try {
            String query = "";
            if(ptype.equals("goalie")) {
                query = "UPDATE goalie SET name = ? WHERE player_id = ?";
            } else if (ptype.equals("forward")) {
                query = "UPDATE forward SET name = ? WHERE player_id = ?";
            } else {
                query = "UPDATE defense SET name = ? WHERE player_id = ?";
            }
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, name);
            ps.setInt(2, p.getPlayerID());

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Player of Type "+ptype+" and ID " + p.getPlayerID() + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public venue[] selectSpaciousVenues(int minRooms) {
        ArrayList<venue> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM venue WHERE rooms <= ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, minRooms);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                venue v = new venue(rs.getInt("venue_id"),
                        rs.getString("address"),
                        rs.getInt("rooms"),
                        rs.getInt("seats"),
                        rs.getInt("num_rinks"));
                result.add(v);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new venue[result.size()]);
    }

    public String[] getTeamNames() {

        ArrayList<String> result = new ArrayList<>();
        try {
            String query = "SELECT name FROM team";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                result.add(rs.getString("name"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }

    // For join, do we wanna abstract smth into a new, temporary class?
    public String[] goaliesUnderCoachName(String name) {
        ArrayList<String> result = new ArrayList<>();
        try {
            String query = "SELECT G.name FROM goalie G, coach C WHERE G.team_id = C.team_id AND C.name = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                result.add(rs.getString("name"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }

    public void allGameParticipants() {
        ArrayList<String> ret = new ArrayList<>();
        try {
            String query = "SELECT T.team_id FROM team T WHERE NOT EXISTS(SELECT G.game_id FROM game G WHERE NOT EXISTS(SELECT C.game_id FROM competes_in C WHERE C.game_id=G.game_id AND C.team_id = T.team_id))";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String id = rs.getString("team_id");
                ret.add(id);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public int maxSeats() {
        int max = -1;
        try {
            String query = "SELECT MAX(seats) FROM venue V";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            max = rs.getInt("seats");

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return max;
    }

    public HashMap<Integer,Integer> maxSeatsPerRef(int ref_id) {
        HashMap<Integer,Integer> ref_seats = new HashMap<Integer, Integer>();
        try {
            String query = "SELECT ref_id, MAX(V.seats) AS maxSeats FROM venue V, regulates_game_at R WHERE V.venue_id = R.venue_id GROUP BY R.ref_id";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Integer id = rs.getInt("ref_id");
                Integer seats = rs.getInt("maxSeats");
                ref_seats.put(id,seats);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return ref_seats;
    }

    public coach[] getCoachInfo() {
        ArrayList<coach> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM coach";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                coach coach = new coach(rs.getInt("coach_id"),
                        rs.getString("name"),
                        rs.getInt("team_id"));
                result.add(coach);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new coach[result.size()]);
    }

    public coach_since[] getCoachSinceInfo() {

        ArrayList<coach_since> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM coach_since";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                coach_since coachsince = new coach_since(rs.getString("coach_id"),
                        rs.getInt("name"));
                result.add(coachsince);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new coach_since[result.size()]);
    }

    public defense[] getDefenseInfo() {
        ArrayList<defense> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM defense";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                defense defense = new defense(rs.getInt("player_id"),
                        rs.getString("name"),
                        rs.getInt("num"),
                        rs.getString("plays_in_since"),
                        rs.getInt("team_id"));
                result.add(defense);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new defense[result.size()]);
    }

    public forward[] getForwardInfo() {
        ArrayList<forward> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM forward";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                forward forward = new forward(rs.getInt("player_id"),
                        rs.getString("name"),
                        rs.getInt("num"),
                        rs.getString("plays_in_since"),
                        rs.getInt("team_id"));
                result.add(forward);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new forward[result.size()]);
    }

    public game[] getGameInfo() {
        ArrayList<game> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM game";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                game game = new game(rs.getInt("game_id"),
                        rs.getInt("team1_score"),
                        rs.getInt("team2_score"));
                result.add(game);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new game[result.size()]);
    }

    public goalie[] getGoalieInfo() {
        ArrayList<goalie> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM goalie";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                goalie g = new goalie(rs.getInt("player_id"),
                        rs.getString("name"),
                        rs.getInt("num"),
                        rs.getString("plays_in_since"),
                        rs.getInt("team_id"));
                result.add(g);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new goalie[result.size()]);
    }

    public regulate_game_at[] getRegGameAtInfo() {
        ArrayList<regulate_game_at> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM regulates_game_at";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                regulate_game_at rga= new regulate_game_at(rs.getInt("game_id"),
                        rs.getInt("ref_id"),
                        rs.getInt("venue_id"),
                        rs.getString("date_and_time"));
                result.add(rga);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new regulate_game_at[result.size()]);
    }

    public organization[] getOrganizationInfo() {
        ArrayList<organization> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM organization";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                organization organization = new organization(rs.getInt("org_id"),
                        rs.getString("name"),
                        rs.getString("city"));
                result.add(organization);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new organization[result.size()]);
    }

    public player[] getPlayerInfo() {
        ArrayList<player> result = new ArrayList<>();
        try {

            String query = "SELECT * FROM forward";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                player p = new player(rs.getInt("player_id"), rs.getString("name"),
                        rs.getInt("num"), rs.getString("plays_in_since"), rs.getInt("team_id"));
                result.add(p);
            }

            query = "SELECT * FROM defense";
            ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            rs = ps.executeQuery();
            while(rs.next()) {
                player p = new player(rs.getInt("player_id"), rs.getString("name"),
                        rs.getInt("num"), rs.getString("plays_in_since"), rs.getInt("team_id"));
                result.add(p);
            }

            query = "SELECT * FROM goalie";
            ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            rs = ps.executeQuery();
            while(rs.next()) {
                player p = new player(rs.getInt("player_id"), rs.getString("name"),
                        rs.getInt("num"), rs.getString("plays_in_since"), rs.getInt("team_id"));
                result.add(p);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new player[result.size()]);
    }

    public referee[] getRefereeInfo() {
        ArrayList<referee> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM referee";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                referee r = new referee(rs.getInt("ref_id"),
                        rs.getString("name"));
                result.add(r);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new referee[result.size()]);
    }

    public team[] getTeamInfo() {
        // For Projection: also implement in this function? Or make another function?
        // Answer: NO becuase we'll just have one field filled and all other null\

        ArrayList<team> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM team";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                team team = new team(rs.getInt("team_id"),
                        rs.getString("name"),
                        rs.getInt("org_id"));
                result.add(team);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new team[result.size()]);
    }

    public venue[] getVenueInfo() {
        ArrayList<venue> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM venue";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                venue v = new venue(rs.getInt("venue_id"),
                        rs.getString("address"),
                        rs.getInt("rooms"),
                        rs.getInt("seats"),
                        rs.getInt("num_rinks"));
                result.add(v);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new venue[result.size()]);
    }



    // LOGISTICS

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:stu", username, password);
            connection.setAutoCommit(false);
            System.out.println("\nConnected to Oracle!");
            databaseSetup();
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void databaseSetup() {
        ScriptRunner sr = new ScriptRunner(connection);

        // Drop All Tables before setup
        dropTablesIfExist();

        //Creating a reader object
        try {
            Reader reader = new BufferedReader(new FileReader("src/sql/scripts/databaseSetup.sql"));
            sr.runScript(reader);

        } catch (FileNotFoundException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    // Drop All Tables
    private void dropTablesIfExist() {
        try {
            // Get all existing table names
            String query = "select table_name from user_tables";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            // Put all names in a list to iterate over
            ArrayList<String> tableList = new ArrayList<>();
            while(rs.next()) {
                String name = rs.getString(1).toLowerCase();
                tableList.add(name);
            }

            // Iterate backwards through the list (to avoid dropping tables that other tables depend on)
            while(!tableList.isEmpty()) {
                String name = tableList.get(tableList.size() - 1);

                query = "DROP TABLE " +name;
                ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
                ps.executeQuery();

                tableList.remove(tableList.size() - 1);
            }

            // Close ps and rs
            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}