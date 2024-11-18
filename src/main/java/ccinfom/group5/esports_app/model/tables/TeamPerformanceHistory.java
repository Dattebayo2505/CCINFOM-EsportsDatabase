package ccinfom.group5.esports_app.model.tables;

public class TeamPerformanceHistory {
    private int historyID;
    private String teamName;
    private String matchDate;
    private String matchResult;
    private double winnings;


    public TeamPerformanceHistory(int historyID, String teamName, String matchDate, String matchResult, double winnings){
        this.historyID = historyID;
        this.teamName = teamName;
        this.matchDate = matchDate;
        this.matchResult = matchResult;
        this.winnings = winnings;
    }

    public String getAllDetails() {
        String details;

        details = historyID + ", " + 
              "\'" + teamName + "\'" + ", " + 
              "\'" + matchDate + "\'" + ", " +
              "\'" + matchResult + "\'" + ", " +
              winnings;

        return details;
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID){
        this.historyID = historyID;
    }

    public String getTeamName(){
        return teamName;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public String getMatchDate(){
        return matchDate;
    }

    public void setMatchDate(String matchDate){
        this.matchDate = matchDate;
    }

    public String getMatchResult(){
        return matchResult;
    }

    public void setMatchResult(String matchResult){
        this.matchResult = matchResult;
    }

    public double getWinnings(){
        return winnings;
    }

    public void setWinnings(double winnings){
        this.winnings = winnings;
    }
}
