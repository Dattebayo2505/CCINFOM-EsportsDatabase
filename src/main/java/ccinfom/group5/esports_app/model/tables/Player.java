package ccinfom.group5.esports_app.model.tables;

public class Player {

    private String playerID;
    private String lastName;
    private String firstName;
    private int age;
    private String country;
    private String teamName;


    public Player(String playerID, String lastName, String firstName, int age, String country, String teamName) {
        this.playerID = playerID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.country = country;
        this.teamName = teamName;
    }

    public String getAllDetails() {
        String details;

        details = "\'"+playerID+"\'"+", "+"\'"+lastName+"\'"+", "+"\'"+firstName+"\'"+", "+age+", "+
                  "\'"+country+"\'"+", "+"\'"+teamName+"\'";

        return details;
    }

    public String getPlayerID() {
        return playerID;
    }
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}
