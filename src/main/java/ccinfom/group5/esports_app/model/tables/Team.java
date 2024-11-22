package ccinfom.group5.esports_app.model.tables;
/* Team, Region, Country, Win Rate */
public class Team implements BaseTable{
    private String team;
    private String captain;
    private String region;
    private String country;
    private String status;

    public Team(String team, String captain, String region, String country, String status) {
        this.team = team;
        this.captain = captain;
        this.region = region;
        this.country = country;
        this.status = status;
    }

    @Override
    public Object[] getRecord() {
        return new Object[]{team, captain, region, country, status};
    }

    public String getAllDetails() {
        String details;

        details = "\'" + team + "\'" + ", " +
                "\'" + captain + "\'" + ", " + 
              "\'" + region + "\'" + ", " + 
              "\'" + country + "\'" + ", " + 
              "\'" + status + "\'";

        return details;
    }

    public String getTeamName() {
        return team;
    }

    public void setTeamName(String team){
        this.team = team;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain){
        this.captain = captain;
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

    public String getStatus() {
        return status;
    }

    public void setStatus (String status){
        this.status = status;
    }
}
