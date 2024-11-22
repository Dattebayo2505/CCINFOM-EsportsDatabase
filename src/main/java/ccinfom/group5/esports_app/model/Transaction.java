package ccinfom.group5.esports_app.model;

import ccinfom.group5.esports_app.model.tables.*;

public class Transaction {

    Transaction() {
    }


    public static void playerTransfer(Database database, 
                                Player player, 
                                String leftOldTeam, 
                                String newTeam,
                                String joinedNewTeam) {

        int lastHistory = database.getAllPlayerHistories().size();
        database.getAllPlayerHistories().add(new PlayerHistory(lastHistory + 1, player.getPlayerID(), 
                                                                player.getCurrentTeam(), leftOldTeam, 
                                                                newTeam, joinedNewTeam));

        player.setCurrentTeam(newTeam);
        player.setStatus("active");
    }
}