package ccinfom.group5.esports_app.model.tables;

public class Company implements BaseTable {
    private int company_id;
    private String company;

    // private Object[] records = new Object[2];

    public Company(int company_id, String company) {
        this.company_id = company_id;
        this.company = company;
    }
    
    @Override
    public Object[] getRecord() {
        return new Object[]{company_id, company};  // Returning fields as an array
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

    // public Object[] getRecord() {
    //     return records;
    // }
}
