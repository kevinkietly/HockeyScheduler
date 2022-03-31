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

//	public void deleteBranch(int branchId) {
//		try {
//			String query = "DELETE FROM branch WHERE branch_id = ?";
//			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//			ps.setInt(1, branchId);
//
//			int rowCount = ps.executeUpdate();
//			if (rowCount == 0) {
//				System.out.println(WARNING_TAG + " Branch " + branchId + " does not exist!");
//			}
//
//			connection.commit();
//
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
//	}
//
//	public void insertBranch(BranchModel model) {
//		try {
//			String query = "INSERT INTO branch VALUES (?,?,?,?,?)";
//			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//			ps.setInt(1, model.getId());
//			ps.setString(2, model.getName());
//			ps.setString(3, model.getAddress());
//			ps.setString(4, model.getCity());
//			if (model.getPhoneNumber() == 0) {
//				ps.setNull(5, java.sql.Types.INTEGER);
//			} else {
//				ps.setInt(5, model.getPhoneNumber());
//			}
//
//			ps.executeUpdate();
//			connection.commit();
//
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
//	}
//
//	public BranchModel[] getBranchInfo() {
//		ArrayList<BranchModel> result = new ArrayList<BranchModel>();
//
//		try {
//			String query = "SELECT * FROM branch";
//			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()) {
//				BranchModel model = new BranchModel(rs.getString("branch_addr"),
//						rs.getString("branch_city"),
//						rs.getInt("branch_id"),
//						rs.getString("branch_name"),
//						rs.getInt("branch_phone"));
//				result.add(model);
//			}
//
//			rs.close();
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new BranchModel[result.size()]);
//	}
//
//	public void updateBranch(int id, String name) {
//		try {
//			String query = "UPDATE branch SET branch_name = ? WHERE branch_id = ?";
//			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//			ps.setString(1, name);
//			ps.setInt(2, id);
//
//			int rowCount = ps.executeUpdate();
//			if (rowCount == 0) {
//				System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
//			}
//
//			connection.commit();
//
//			ps.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//			rollbackConnection();
//		}
//	}

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
    public void insertPlayer(Forward p) {
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
        ps.setInt(3, p.getTeamID());

        ps.executeUpdate();
        connection.commit();

        ps.close();
    }

    public void deleteTeam(int team_id) {
		try {
			String query = "DELETE FROM team WHERE team_id = ?";
			PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
			ps.setInt(1, team_id);

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Team " + team_id + " does not exist!");
			}

			connection.commit();
			ps.close();
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

    public void updatePlayerName(Forward p, String name) {
        updatePlayerName(p, name, "forward");
    }

    private void updatePlayerName(player p, String name,String ptype) {
        try {
            String query = "UPDATE ? SET name = ? WHERE player_id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, ptype);
            ps.setString(2, name);
            ps.setInt(3, p.getPlayerID());

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

    // For join, do we wanna abstract smth into a new, temporary class?
    // I think we might need to do this for selection/projection as well

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
        // For Selection: also implement in this function? Or make another function?
        // Answer: NO becuase we'll just have one field filled and all other null

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

    public Forward[] getForwardInfo() {
        ArrayList<Forward> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM forward";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Forward forward = new Forward(rs.getInt("player_id"),
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
        return result.toArray(new Forward[result.size()]);
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