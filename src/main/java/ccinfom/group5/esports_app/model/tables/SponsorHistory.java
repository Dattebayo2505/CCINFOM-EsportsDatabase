package ccinfom.group5.esports_app.model.tables;

public class SponsorHistory implements BaseTable{
    private int history_id;
    private int sponsor_id;
    private String team;
    private double contract_amount;
    private String contract_start;
    private String contract_end;

    public SponsorHistory(int history_id, int sponsor_id, String team, double contract_amount, String contract_start, String contract_end){
        this.history_id = history_id;
        this.sponsor_id = sponsor_id;
        this.team = team;
        this.contract_amount = contract_amount;
        this.contract_start = contract_start;
        this.contract_end = contract_end;
    }

    @Override
    public Object[] getRecord() {
        return new Object[]{history_id, sponsor_id, team, contract_amount, contract_start, contract_end};
    }

    public String getAllDetails() {
        String details;

        details = history_id + ", " + 
        sponsor_id + ", " + 
              "\'" + team + "\'" + ", " +
              + contract_amount + ", " +
              "\'" + contract_start + "\'" + ", " +   
              "\'" + contract_end + "\'";

        return details;
    }

    public int getHistoryID() {
        return history_id;
    }

    public void setHistoryID(int history_id){
        this.history_id = history_id;
    }

    public int getSponsorID(){
        return sponsor_id;
    }

    public void setSponsorID(int sponsor_id){
        this.sponsor_id = sponsor_id;
    }

    public String getTeamName(){
        return team;
    }

    public void setTeamName(String team){
        this.team = team;
    }

    public double getContractAmount(){
        return contract_amount;
    }

    public void setContractAmount(double contract_amount){
        this.contract_amount = contract_amount;
    }

    public String getContractStart(){
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
