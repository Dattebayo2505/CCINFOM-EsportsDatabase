package ccinfom.group5.esports_app.model.tables;

public class Company {
    private int companyID;
    private String companyName;

    public Company(int companyID, String companyName){
        this.companyID = companyID;
        this.companyName = companyName;
    }

    public String getAllDetails() {
        String details;

        details = companyID + ", " + 
              "\'" + companyName + "\'";

        return details;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName(){
        return companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
}
