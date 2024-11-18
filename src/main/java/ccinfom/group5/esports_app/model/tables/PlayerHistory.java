package ccinfom.group5.esports_app.model.tables;

import java.lang.management.ThreadInfo;

public class PlayerHistory {
    private int historyID;
    private int playerID;
    private String oldTeamName;
    private String leftOldTeamDate;
    private String newTeamName;
    private String joinedNewTeamDate;

    public PlayerHistory(int historyID, int playerID, String oldTeamName, String leftOldTeamDate, String newTeamName, String joinedNewTeamDate){
        this.historyID = historyID;
        this.playerID = playerID;
        this.oldTeamName = oldTeamName;
        this.leftOldTeamDate = leftOldTeamDate;
        this.newTeamName = newTeamName;
        this.joinedNewTeamDate = joinedNewTeamDate;
    }

    public String getAllDetails() {
        String details;

        details = historyID + ", " + 
              playerID + ", " + 
              "\'" + oldTeamName + "\'" + ", " +
              "\'" + leftOldTeamDate + "\'" + ", " +   
              "\'" + newTeamName + "\'" + "," +
              "\'" + joinedNewTeamDate + "\'";

        return details;
    }

    public int getHistoryID(){
        return historyID;
    }

    public void setHistoryID(int historyID){
        this.historyID = historyID;
    }

    public int getPlayerID(){
        return playerID;
    }

    public void setPlayerID(int playerID){
        this.playerID = playerID;
    }

    public String getsetOldTeamName(){
        return oldTeamName;
    }

    public void setOldTeamName(String oldTeamName){
        this.oldTeamName = oldTeamName;
    }

    public String getLeftOldTeamDate(){
        return leftOldTeamDate;
    }

    public void setLeftOldTeamDate(String leftOldTeamDate){
        this.leftOldTeamDate = leftOldTeamDate;
    }

    public String getNewTeamName(){
        return newTeamName;
    }

    public void setNewTeamName(String newTeamName){
        this.newTeamName = newTeamName;
    }

    public String getJoinedNewTeamDate(){
        return joinedNewTeamDate;
    }

    public void setJoinedNewTeamDate(String joinedNewTeamDate){
        this.joinedNewTeamDate = joinedNewTeamDate;
    }
}
