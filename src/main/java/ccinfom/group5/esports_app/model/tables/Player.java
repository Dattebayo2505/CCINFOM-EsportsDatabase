package ccinfom.group5.esports_app.model.tables;

public class Player {

    private int player_id;
    private String last_name;
    private String first_name;
    private int age;
    private String nationality;
    private String team_name;


    public Player(int player_id, String last_name, String first_name, int age, String nationality, String team_name) {
        this.player_id = player_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.age = age;
    }


    public int getPlayer_id() {
        return player_id;
    }
    public void setPlayer_id(int player_id) {
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

    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTeam_name() {
        return team_name;
    }
    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    

    
}
