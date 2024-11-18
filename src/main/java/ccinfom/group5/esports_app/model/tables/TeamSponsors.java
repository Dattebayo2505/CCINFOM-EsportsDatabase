package ccinfom.group5.esports_app.model.tables;
/* Sponsor ID, Team, Contract Amount, Contract Start, Contract End */
public class TeamSponsors {
    private int sponsor_id;
    private String team;
    private double contract_amount;
    private String contract_start;
    private String contract_end;

    public TeamSponsors(int sponsor_id, String team, double contract_amount, String contract_start, String contract_end) {
        this.sponsor_id = sponsor_id;
        this.team = team;
        this.contract_amount = contract_amount;
        this.contract_start = contract_start;
        this.contract_end = contract_end;
    }

    public String getAllDetails() {
        String details;

        details = sponsor_id + ", " + 
              "\'" + team + "\'" + ", " + 
              + contract_amount + ", " +
              "\'" + contract_start + "\'" + ", " +   
              "\'" + contract_end + "\'";

        return details;
    }

    public int getSponsorID() {
        return sponsor_id;
    }

    public void setSponsorID(int sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public String getTeamName() {
        return team;
    }

    public void setTeamName(String team) {
        this.team = team;
    }

    public double getContractAmount() {
        return contract_amount;
    }

    public void setContractAmount(double contract_amount) {
        this.contract_amount = contract_amount;
    }

    public String getContractStart (){
        return contract_start;
    }

    public void setContractStart(String contract_start){
        this.contract_start = contract_start;
    }

    public String getContractEnd(){
        return contract_end;
    }

    public void setContractEnd(String contract_end){
        this.contract_end = contract_end;
    }
}
