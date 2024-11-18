package ccinfom.group5.esports_app.model.tables;

public class TeamHistory {
    private int historyID;
    private int teamID;
    private String creationDate;
    private String disbandDate;

    public TeamHistory(int historyID, int teamID, String creationDate, String disbandDate){
        this.historyID = historyID;
        this.teamID = teamID;
        this.creationDate = creationDate;
        this.disbandDate = disbandDate;
    }

    public String getAllDetails() {
        String details;

        details = historyID + ", " + 
              + teamID + ", " + 
              "\'" + creationDate + "\'" + ", " +
              "\'" + disbandDate + "\'";

        return details;
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID){
        this.historyID = historyID;
    }

    public int getTeamID(){
        return teamID;
    }

    public void setTeamID(int teamID){
        this.teamID = teamID;
    }
    
    public String getCreationDate(){
        return creationDate;
    }

    public void setCreationDate(String creationDate){
        this.creationDate = creationDate;
    }

    public String getDisbandDate(){
        return disbandDate;
    }

    public void setDisbandDate(String disbandDate){
        this.disbandDate = disbandDate;
    }
}
