package ccinfom.group5.esports_app.model.tables;

public class SponsorHistory {
    private int historyID;
    private int sponsorID;
    private String teamName;
    private double contractAmount;
    private String contractStart;
    private String contractEnd;

    public SponsorHistory(int historyID, int sponsorID, String teamName, double contractAmount, String contractStart, String contractEnd){
        this.historyID = historyID;
        this.sponsorID = sponsorID;
        this.teamName = teamName;
        this.contractAmount = contractAmount;
        this.contractStart = contractStart;
        this.contractEnd = contractEnd;
    }

    public String getAllDetails() {
        String details;

        details = historyID + ", " + 
              sponsorID + ", " + 
              "\'" + teamName + "\'" + ", " +
              + contractAmount + ", " +
              "\'" + contractStart + "\'" + ", " +   
              "\'" + contractEnd + "\'";

        return details;
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID){
        this.historyID = historyID;
    }

    public int getSponsorID(){
        return sponsorID;
    }

    public void setSponsorID(int sponsorID){
        this.sponsorID = sponsorID;
    }

    public String getTeamName(){
        return teamName;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public double getContractAmount(){
        return contractAmount;
    }

    public void setContractAmount(double contractAmount){
        this.contractAmount = contractAmount;
    }

    public String getContractStart(){
        return contractStart;
    }

    public void setContractStart(String contractStart){
        this.contractStart = contractStart;
    }

    public String getContractEnd(){
        return contractEnd;
    }

    public void setContractEnd(String contractEnd){
        this.contractEnd = contractEnd;
    }
}
