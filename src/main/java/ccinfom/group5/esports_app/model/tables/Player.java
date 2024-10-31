package ccinfom.group5.esports_app.model.tables;

public class Player {

    private String player_id;
    private String last_name;
    private String first_name;
    private int age;
    private String country;
    private String team_name;


    public Player(String player_id, String last_name, String first_name, int age, String country, String team_name) {
        this.player_id = player_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.age = age;
        this.country = country;
        this.team_name = team_name;
    }

    public String getPlayer_id() {
        return player_id;
    }
    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
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

    public String getTeam_name() {
        return team_name;
    }
    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

}
