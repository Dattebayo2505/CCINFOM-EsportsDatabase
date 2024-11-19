package ccinfom.group5.esports_app.model.tables;

public class TeamPerformanceHistory {
    private int history_id;
    private String team_name;
    private String match_date;
    private String result;
    private double winnings;


    public TeamPerformanceHistory(int history_id, String team_name, String match_date, String result, double winnings){
        this.history_id = history_id;
        this.team_name = team_name;
        this.match_date = match_date;
        this.result = result;
        this.winnings = winnings;
    }

    public String getAllDetails() {
        String details;

        details = history_id + ", " + 
              "\'" + team_name + "\'" + ", " + 
              "\'" + match_date + "\'" + ", " +
              "\'" + result + "\'" + ", " +
              winnings;

        return details;
    }

    public int getHistoryID() {
        return history_id;
    }

    public void setHistoryID(int history_id){
        this.history_id = history_id;
    }

    public String getTeamName(){
        return team_name;
    }

    public void setTeamName(String team_name){
        this.team_name = team_name;
    }

    public String getMatchDate(){
        return match_date;
    }

    public void setMatchDate(String match_date){
        this.match_date = match_date;
    }

    public String getResult(){
        return result;
    }

    public void setResult(String result){
        this.result = result;
    }

    public double getWinnings(){
        return winnings;
    }

    public void setWinnings(double winnings){
        this.winnings = winnings;
    }
}