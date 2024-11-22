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




}