package ccinfom.group5.esports_app.model.tables;

public class TeamHistory implements BaseTable{
    private int history_id;
    private String team;
    private String creation_date;
    private String disband_date;

    public TeamHistory(int history_id, String team, String creation_date, String disband_date){
        this.history_id = history_id;
        this.team = team;
        this.creation_date = creation_date;
        this.disband_date = disband_date;
    }

    @Override
    public Object[] getRecord() {
        return new Object[]{history_id, team, creation_date, disband_date};
    }

    public String getAllDetails() {
        String details;

        details = history_id + ", " + 
              team + ", " + 
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

    public String getTeamID(){
        return team;
    }

    public void setTeamID(String team){
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
