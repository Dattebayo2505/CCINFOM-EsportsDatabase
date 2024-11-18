package ccinfom.group5.esports_app.model.tables;

public class TeamStatistics {
    private String teamName;
    private double totalWinnings;
    private String favoredMap;
    private int numWins;
    private int numLosses;

    public TeamStatistics(String teamName, double totalWinnings, String favoredMap, int numWins, int numLosses){
        this.teamName = teamName;
        this.totalWinnings = totalWinnings;
        this.favoredMap = favoredMap;
        this.numWins = numWins;
        this.numLosses = numLosses;
    }

    public String getAllDetails() {
        String details;

        details = "\'" + teamName + "\'" + ", " 
                + totalWinnings + ", " + 
                "\'" + favoredMap + "\'" + ", "
                + numWins + ", " +   
                numLosses;

        return details;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public double getTotalWinnings() {
        return totalWinnings;
    }

    public void setTotalWinnings(double totalWinnings){
        this.totalWinnings = totalWinnings;
    }

    public String getFavoredMap() {
        return favoredMap;
    }

    public void setFavoredMap(String favoredMap) {
        this.favoredMap = favoredMap;
    }

    public int getNumWins(){
        return numWins;
    }

    public void setNumWins(int numWins){
        this.numWins = numWins;
    }

    public int getNumLosses(){
        return numWins;
    }

    public void setNumLosses(int numLosses){
        this.numLosses = numLosses;
    }
}
