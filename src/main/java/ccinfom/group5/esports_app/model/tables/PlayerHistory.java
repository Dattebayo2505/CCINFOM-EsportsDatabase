package ccinfom.group5.esports_app.model.tables;

public class PlayerHistory {
    private int history_id;
    private int player_id;
    private String old_team;
    private String left_old_team;
    private String new_team;
    private String join_new_team;

    public PlayerHistory(int history_id, int player_id, String old_team, String left_old_team, String new_team, String join_new_team){
        this.history_id = history_id;
        this.player_id = player_id;
        this.old_team = old_team;
        this.left_old_team = left_old_team;
        this.new_team = new_team;
        this.join_new_team = join_new_team;
    }

    public String getAllDetails() {
        String details;

        details = history_id + ", " + 
        player_id + ", " + 
              "\'" + old_team + "\'" + ", " +
              "\'" + left_old_team + "\'" + ", " +   
              "\'" + new_team + "\'" + "," +
              "\'" + join_new_team + "\'";

        return details;
    }

    public int getHistoryID(){
        return history_id;
    }

    public void setHistoryID(int history_id){
        this.history_id = history_id;
    }

    public int getPlayerID(){
        return player_id;
    }

    public void setPlayerID(int player_id){
        this.player_id = player_id;
    }

    public String getOldTeam(){
        return old_team;
    }

    public void setOldTeam(String old_team){
        this.old_team = old_team;
    }

    public String getLeftOldTeamDate(){
        return left_old_team;
    }

    public void setLeftOldTeamDate(String left_old_team){
        this.left_old_team = left_old_team;
    }

    public String getNewTeam(){
        return new_team;
    }

    public void setNewTeam(String new_team){
        this.new_team = new_team;
    }

    public String getJoinedNewTeamDate(){
        return join_new_team;
    }

    public void setJoinedNewTeamDate(String join_new_team){
        this.join_new_team = join_new_team;
    }
}
