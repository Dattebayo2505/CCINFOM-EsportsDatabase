package ccinfom.group5.esports_app.model;
    
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Reports {
    // execute query

    // refer to database class
    // store the query in a string and use the string as a parameter

    public static void viewTransferReport(Connection con, int year, int month) throws SQLException {
        String query = "SELECT YEAR(ph.joined_new_team) AS year_transferred, " +
                    "MONTH(ph.joined_new_team) AS month_transferred, " +
                    "COUNT(ph.history_id) AS total_transfers, " +
                    "COUNT(ph.history_id) / COUNT(DISTINCT ph.player_id) AS avg_transfers_per_player " +
                    "FROM playerhistory ph " +
                    "WHERE YEAR(ph.joined_new_team) = " + year + " AND MONTH(ph.joined_new_team) = " + month +
                    "GROUP BY YEAR(ph.joined_new_team), MONTH(ph.joined_new_team);";

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int yearTransferred = rs.getInt("year_transferred");
                int monthTransferred = rs.getInt("month_transferred");
                int totalTransfers = rs.getInt("total_transfers");
                float avgTransfersPerPlayer = rs.getFloat("avg_transfers_per_player");
                System.out.println("Year: " + yearTransferred + ", Month: " + monthTransferred +
                                ", Total Transfers: " + totalTransfers +
                                ", Avg Transfers per Player: " + avgTransfersPerPlayer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewCreationDeletionReport(Connection con, int year, int month) throws SQLException {
        String query = "SELECT " +
                    "YEAR(th.creation_date) AS year, " +
                    "MONTH(th.creation_date) AS month, " +
                    "COALESCE(COUNT(DISTINCT CASE WHEN YEAR(th.creation_date) = " + year + 
                    " AND MONTH(th.creation_date) = " + month + 
                    " THEN th.team END), 0) AS teams_created, " +
                    "COALESCE(COUNT(DISTINCT CASE WHEN YEAR(th.disband_date) = " + year + 
                    " AND MONTH(th.disband_date) = " + month + 
                    " THEN th.team END), 0) AS teams_disbanded, " +
                    "COALESCE(ROUND(AVG(CASE WHEN YEAR(th.creation_date) = " + year + 
                    " AND MONTH(th.creation_date) = " + month + 
                    " THEN 1 ELSE NULL END), 2), 0) AS avg_teams_created, " +
                    "COALESCE(ROUND(AVG(CASE WHEN YEAR(th.disband_date) = " + year + 
                    " AND MONTH(th.disband_date) = " + month + 
                    " THEN 1 ELSE NULL END), 2), 0) AS avg_teams_disbanded, " +
                    "COALESCE(COUNT(DISTINCT ph.player_id), 0) AS players_affected_by_disband " +
                    "FROM teamhistory th " +
                    "LEFT JOIN playerhistory ph ON th.team = ph.old_team " +
                    "AND ph.left_old_team = th.disband_date " +
                    "WHERE (YEAR(th.creation_date) = " + year + " AND MONTH(th.creation_date) = " + month + ") " +
                    "OR (YEAR(th.disband_date) = " + year + " AND MONTH(th.disband_date) = " + month + ") " +
                    "GROUP BY year, month;";

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int yearTransferred = rs.getInt("year_transferred");
                int monthTransferred = rs.getInt("month_transferred");
                int totalTransfers = rs.getInt("total_transfers");
                float avgTransfersPerPlayer = rs.getFloat("avg_transfers_per_player");
                System.out.println("Year: " + yearTransferred + ", Month: " + monthTransferred +
                                ", Total Transfers: " + totalTransfers +
                                ", Avg Transfers per Player: " + avgTransfersPerPlayer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewSponsorshipSummaryReport(Connection con, int year, int month) throws SQLException {
        String query = "SELECT t.team, YEAR(s.contract_start) AS sponsorship_year, MONTH(s.contract_start) AS sponsorship_month, " +
                    "SUM(s.contract_amount) AS total_sponsorship, " +
                    "AVG(s.contract_amount) AS average_sponsorship, " +
                    "COUNT(s.sponsor_id) AS total_sponsors " +
                    "FROM teams t " +
                    "JOIN teamsponsor s ON t.team = s.team " +
                    "WHERE YEAR(s.contract_start) = " + year + "-- Specify the year you are interested in" +
                    "AND (@month IS NULL OR MONTH(s.contract_start) = @month)  -- Optional month filter" +
                    "GROUP BY t.team, YEAR(s.contract_start), MONTH(s.contract_start)" +
                    "ORDER BY sponsorship_year, sponsorship_month, t.team;";
            
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int sponsorshipYear = rs.getInt("sponsorship_year");
                int sponsorshipMonth = rs.getInt("sponsorship_month");
                String team = rs.getString("team");
                int totalSponsors = rs.getInt("total_sponsors");
                System.out.println("Year: " + sponsorshipYear + ", Month: " + sponsorshipMonth +
                                    ", Team: " + team + ", Total Sponsors: " + totalSponsors);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewTeamPerformanceReport(Connection con, int year, int month) {
        String query = "SELECT tph.team, " + 
                    "YEAR(tph.match_date) AS match_year, " +
                    "MONTH(tph.match_date) AS match_month, " +
                    "tph.winnings, " +
                    "COUNT(CASE WHEN tph.result = 'win' THEN 1 END) AS total_wins " +
                    "FROM teamperformancehistory tph " +
                    "WHERE YEAR(tph.match_date) = " + year +
                    "AND (@month IS NULL OR MONTH(tph.match_date) = @month)" + 
                    "GROUP BY tph.team, YEAR(tph.match_date), MONTH(tph.match_date), tph.winnings" +
                    "ORDER BY total_wins DESC;";
                    
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String team = rs.getString("team");
                int matchYear = rs.getInt("match_year");
                int matchMonth = rs.getInt("match_month");
                double winnings = rs.getDouble("winnings");
                int totalWins = rs.getInt("total_wins");
                System.out.println("Team: " + team + ", Year: " + matchYear + ", Month: " + matchMonth +
                                    ", Winnings: " + winnings + ", Total Wins: " + totalWins);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
