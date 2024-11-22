package ccinfom.group5.esports_app.model;

import ccinfom.group5.esports_app.model.tables.*;

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
}