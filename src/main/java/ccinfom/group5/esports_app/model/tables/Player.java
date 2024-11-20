package ccinfom.group5.esports_app.model.tables;

public class Player {
    private String player_id;
    private String last_name;
    private String first_name;
    private int age;
    private String country;
    private String current_team;
    private String status;

    public Player(String player_id, String last_name, String first_name, int age, String country, String current_team, String status) {
        this.player_id = player_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.age = age;
        this.country = country;
        this.current_team = current_team;
        this.status = status;
    }

    public String getAllDetails() {
        String details;

        details = "\'"+player_id+"\'"+", "+"\'"+last_name+"\'"+", "+"\'"+first_name+"\'"+", "+age+", "+
                  "\'"+country+"\'"+", "+"\'"+current_team+"\'"+", "+"\'"+status+"\'";

        return details;
    }

    public String getPlayerID() {
        return player_id;
    }
    public void setPlayerID(String player_id) {
        this.player_id = player_id;
    }

    public String getLastName() {
        return last_name;
    }
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getFirstName() {
        return first_name;
    }
    public void setFirstName(String first_name) {
        this.first_name = first_name;
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

    public String getCurrentTeam() {
        return current_team;
    }
    public void setCurrentTeam(String current_team) {
        this.current_team = current_team;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
