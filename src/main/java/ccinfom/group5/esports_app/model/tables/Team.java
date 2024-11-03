package ccinfom.group5.esports_app.model.tables;
/* Team, Region, Country, Win Rate */
public class Team {
    private String teamName;
    private String region;
    private String country;
    private float winRate;

    public Team(String teamName, String region, String country, float winRate) {
        this.teamName = teamName;
        this.region = region;
        this.country = country;
        this.winRate = winRate;
    }

    public String getAllDetails() {
        String details;

        details = "\'" + teamName + "\'" + ", " + 
              "\'" + region + "\'" + ", " + 
              "\'" + country + "\'" + ", " + 
              winRate;

        return details;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region){
        this.region = region;
    }

    public String getCoutry() {
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public float getWinRate() {
        return winRate;
    }

    public void setWinRate (float winRate){
        this.winRate = winRate;
    }
}
