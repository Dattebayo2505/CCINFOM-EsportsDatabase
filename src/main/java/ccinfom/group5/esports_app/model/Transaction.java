package ccinfom.group5.esports_app.model;

import ccinfom.group5.esports_app.model.tables.Player;
import ccinfom.group5.esports_app.model.tables.PlayerHistory;
import ccinfom.group5.esports_app.model.tables.SponsorHistory;
import ccinfom.group5.esports_app.model.tables.Team;
import ccinfom.group5.esports_app.model.tables.TeamHistory;
import ccinfom.group5.esports_app.model.tables.TeamPerformanceHistory;
import ccinfom.group5.esports_app.model.tables.TeamStats;

public class Transaction {
    private Database database;

    Transaction(Database database) {
        this.database = database;
    }


    public void playerTransfer(Player player, 
                                String leftOldTeam, 
                                String newTeam,
                                String joinedNewTeam) {

        int lastHistory = database.getAllPlayerHistories().size();
        database.getAllPlayerHistories().add(new PlayerHistory(lastHistory + 1, 
                                                player.getPlayerID(), 
                                                player.getCurrentTeam(), 
                                                leftOldTeam, 
                                                newTeam, 
                                                joinedNewTeam));

        player.setCurrentTeam(newTeam);
        player.setStatus("active");
    }

    public void setTeamHistory(Team team, String disbandDate) {
        // Disband the team by updating team history
        int lastHistory = database.getAllTeamHistories().size();

        TeamHistory teamHistory = new TeamHistory(lastHistory + 1, 
                                                    team.getTeamName(), 
                                                    team.getTeamHistory(database), 
                                                    disbandDate);
        database.getAllTeamHistories().add(teamHistory);

        // Set all players in the team to "inactive" and update their histories
        for (Player player : database.getAllPlayers()) {
            if (player.getCurrentTeam().equals(team.getTeamName())) {
                String playerID = player.getPlayerID();
                String leftOldTeamDate = disbandDate;

                // Update player history
                int playerLastHistory = database.getAllPlayerHistories().size();
                PlayerHistory playerHistory = new PlayerHistory(playerLastHistory + 1, 
                                                                playerID, 
                                                                player.getCurrentTeam(), 
                                                                leftOldTeamDate, 
                                                                null, 
                                                                null);
                database.getAllPlayerHistories().add(playerHistory);

                // Set player status to inactive
                player.setCurrentTeam(null);
                player.setStatus("inactive");
            }
        }
    }

    public void modifySponsorships(int history_id, int sponsor_id, String teamName, double contract_amount, String contract_start, String contract_end) {
        int currSponsorships = 0;
        // check for active sponsorships
        for (SponsorHistory sponsorHistory : database.getAllSponsorHistories()) {
            if (sponsorHistory.getSponsorID() == sponsor_id) {
                // help pls
            }
        }

        if (currSponsorships <= 3) {
            int lastHistory = database.getAllSponsorHistories().size();
            // add new record to sponsorhistory
            database.getAllSponsorHistories().add(new SponsorHistory(lastHistory + 1, 
                                                            sponsor_id, 
                                                            teamName, 
                                                            contract_amount, 
                                                            contract_start, 
                                                            contract_end));
        }
    }

    public void teamStatistics(String teamName, String matchDate, String result, double winnings) {
        // Get the last history record index for TeamPerformanceHistory
        int lastHistory = database.getAllTeamPerformanceHistories().size();
        
        // Add the new TeamPerformanceHistory record
        database.getAllTeamPerformanceHistories().add(new TeamPerformanceHistory(lastHistory + 1, 
                                                            teamName, 
                                                            matchDate, 
                                                            result, 
                                                            winnings));

        // Update the TeamStats for the team directly by matching teamName
        for (TeamStats teamStats : database.getAllTeamStats()) {
            if (teamStats.getTeamName().equals(teamName)) {
                if (result.equalsIgnoreCase("win")) {
                    // Increment wins if the result is "win"
                    teamStats.setWins(teamStats.getWins() + 1); 
                } else {
                    // Increment losses if the result is not "win"
                    teamStats.setLosses(teamStats.getLosses() + 1); 
                }
                break; // Exit the loop once the correct team is found and updated
            }
        }
    }    
}