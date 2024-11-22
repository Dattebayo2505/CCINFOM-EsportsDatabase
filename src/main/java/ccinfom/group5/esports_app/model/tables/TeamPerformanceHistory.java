package ccinfom.group5.esports_app.model.tables;

public class TeamPerformanceHistory implements BaseTable{
    private int history_id;
    private String team;
    private String match_date;
    private String result;
    private double winnings;


    public TeamPerformanceHistory(int history_id, String team, String match_date, String result, double winnings){
        this.history_id = history_id;
        this.team = team;
        this.match_date = match_date;
        this.result = result;
        this.winnings = winnings;
    }

    @Override
    public Object[] getRecord() {
        return new Object[]{history_id, team, match_date, result, winnings};
    }

    public String getAllDetails() {
        String details;

        details = history_id + ", " + 
              "\'" + team + "\'" + ", " + 
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
        return team;
    }

    public void setTeamName(String team){
        this.team = team;
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
