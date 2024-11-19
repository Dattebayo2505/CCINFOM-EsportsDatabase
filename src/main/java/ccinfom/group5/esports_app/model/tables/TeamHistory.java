package ccinfom.group5.esports_app.model.tables;

public class TeamHistory {
    private int history_id;
    private int team;
    private String creation_date;
    private String disband_date;

    public TeamHistory(int history_id, int team, String creation_date, String disband_date){
        this.history_id = history_id;
        this.team = team;
        this.creation_date = creation_date;
        this.disband_date = disband_date;
    }

    public String getAllDetails() {
        String details;

        details = history_id + ", " + 
              + team + ", " + 
              "\'" + creation_date + "\'" + ", " +
              "\'" + disband_date + "\'";

        return details;
    }

    public int getHistoryID() {
        return history_id;
    }

    public void setHistoryID(int history_id){
        this.history_id = history_id;
    }

    public int getTeamID(){
        return team;
    }

    public void setTeamID(int team){
        this.team = team;
    }
    
    public String getCreationDate(){
        return creation_date;
    }

    public void setCreationDate(String creation_date){
        this.creation_date = creation_date;
    }

    public String getDisbandDate(){
        return disband_date;
    }

    public void setDisbandDate(String disband_date){
        this.disband_date = disband_date;
    }
}
