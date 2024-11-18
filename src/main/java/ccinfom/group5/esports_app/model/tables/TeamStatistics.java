package ccinfom.group5.esports_app.model.tables;

public class TeamStatistics {
    private String team_name;
    private double total_winnings;
    private String favored_map;
    private int wins;
    private int losses;

    public TeamStatistics(String team_name, double total_winnings, String favored_map, int wins, int losses){
        this.team_name = team_name;
        this.total_winnings = total_winnings;
        this.favored_map = favored_map;
        this.wins = wins;
        this.losses = losses;
    }

    public String getAllDetails() {
        String details;

        details = "\'" + team_name + "\'" + ", " 
                + total_winnings + ", " + 
                "\'" + favored_map + "\'" + ", "
                + wins + ", " +   
                losses;

        return details;
    }

    public String getTeamName() {
        return team_name;
    }

    public void setTeamName(String team_name){
        this.team_name = team_name;
    }

    public double getTotalWinnings() {
        return total_winnings;
    }

    public void setTotalWinnings(double total_winnings){
        this.total_winnings = total_winnings;
    }

    public String getFavoredMap() {
        return favored_map;
    }

    public void setFavoredMap(String favored_map) {
        this.favored_map = favored_map;
    }

    public int getWins(){
        return wins;
    }

    public void setWins(int wins){
        this.wins = wins;
    }

    public int getLosses(){
        return losses;
    }

    public void setLosses(int losses){
        this.losses = losses;
    }
}
