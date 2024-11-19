package ccinfom.group5.esports_app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import ccinfom.group5.esports_app.model.tables.*;
import ccinfom.group5.esports_app.utils.*;

public class Database {
    
    private Connection con;
    private Statement statement;

    private ArrayList<Player> allPlayers;
    private ArrayList<Team> allTeams;
    private ArrayList<Company> allCompanies;
    private ArrayList<TeamSponsor> allTeamSponsors;
    private ArrayList<TeamStats> allTeamStats;
    private ArrayList<PlayerHistory> allPlayerHistories;
    private ArrayList<TeamHistory> allTeamHistories;
    private ArrayList<SponsorHistory> allSponsorHistories;
    private ArrayList<TeamPerformanceHistory> allTeamPerformanceHistories;

    public Database() {
        this.con = null;
        this.statement = null;
        this.allPlayers = new ArrayList<Player>();
        this.allTeams = new ArrayList<Team>();
        this.allCompanies = new ArrayList<Company>();
        this.allTeamSponsors = new ArrayList<TeamSponsor>();
        this.allTeamStats = new ArrayList<TeamStats>();
        this.allPlayerHistories = new ArrayList<PlayerHistory>();
        this.allTeamHistories = new ArrayList<TeamHistory>();
        this.allSponsorHistories = new ArrayList<SponsorHistory>();
        this.allTeamPerformanceHistories = new ArrayList<TeamPerformanceHistory>();
    }

    public void initializeDatabase(List<String> filepaths, Connection con) {
        FileReaderUtil.getDatabase(filepaths, con);
    }

    public void initiateModel(List<String> tables) {
        ArrayList columnsCompany = new ArrayList<String>();
    }

    // TODO: ADD OTHER QUERY/UPDATE METHODS HERE - JOB 



    public boolean initialStatus() {
        this.con = JavaSQLConnection.tryMakeConnection();
        return this.con != null;        
    }

    public Connection getCon() {
        return con;
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }
    public void setAllPlayers(ArrayList<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }
    public ArrayList<Team> getAllTeams() {
        return allTeams;
    }
    public void setAllTeams(ArrayList<Team> allTeams) {
        this.allTeams = allTeams;
    }
    public ArrayList<Company> getAllCompanies() {
        return allCompanies;
    }
    public void setAllCompanies(ArrayList<Company> allCompanies) {
        this.allCompanies = allCompanies;
    }
    public ArrayList<TeamSponsor> getAllTeamSponsors() {
        return allTeamSponsors;
    }
    public void setAllTeamSponsors(ArrayList<TeamSponsor> allTeamSponsors) {
        this.allTeamSponsors = allTeamSponsors;
    }
    public ArrayList<TeamStats> getAllTeamStats() {
        return allTeamStats;
    }
    public void setAllTeamStats(ArrayList<TeamStats> allTeamStats) {
        this.allTeamStats = allTeamStats;
    }
    public ArrayList<PlayerHistory> getAllPlayerHistories() {
        return allPlayerHistories;
    }
    public void setAllPlayerHistories(ArrayList<PlayerHistory> allPlayerHistories) {
        this.allPlayerHistories = allPlayerHistories;
    }
    public ArrayList<TeamHistory> getAllTeamHistories() {
        return allTeamHistories;
    }
    public void setAllTeamHistories(ArrayList<TeamHistory> allTeamHistories) {
        this.allTeamHistories = allTeamHistories;
    }
    public ArrayList<SponsorHistory> getAllSponsorHistories() {
        return allSponsorHistories;
    }
    public void setAllSponsorHistories(ArrayList<SponsorHistory> allSponsorHistories) {
        this.allSponsorHistories = allSponsorHistories;
    }
    public ArrayList<TeamPerformanceHistory> getAllTeamPerformanceHistories() {
        return allTeamPerformanceHistories;
    }
    public void setAllTeamPerformanceHistories(ArrayList<TeamPerformanceHistory> allTeamPerformanceHistories) {
        this.allTeamPerformanceHistories = allTeamPerformanceHistories;
    }


}

