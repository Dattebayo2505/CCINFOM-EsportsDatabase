package ccinfom.group5.esports_app.model.tables;

public class Company {
    private int company_id;
    private String company;

    public Company(int company_id, String company){
        this.company_id = company_id;
        this.company = company;
    }

    public String getAllDetails() {
        String details;

        details = company_id + ", " + 
              "\'" + company + "\'";

        return details;
    }

    public int getCompanyID() {
        return company_id;
    }

    public void setCompanyID(int company_id) {
        this.company_id = company_id;
    }

    public String getCompanyName(){
        return company;
    }

    public void setCompanyName(String company){
        this.company = company;
    }
}
