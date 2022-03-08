package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a player's name, position, hockey attributes, value
public class Player implements Writable {
    private String name;                     // name of player
    private String position;                 // player's position
    private String status;                   // player's status
    private int valuationOfPlayer;           // player's value
    private int shooting;                    // player's shooting rating
    private int skating;                     // player's skating rating
    private int puckSkills;                  // player's puck skills
    private int competeLevel;                // player's compete level
    private int hockeyIQ;                    // player's hockey sense


    // REQUIRES: value has to be >= 0
    // EFFECTS: name of the player is playerName; position of the player is set to playerPosition;
    //          value of the player has to be >= 0
    public Player(String playerName, String playerPosition) {
        this.name = playerName;
        this.position = playerPosition;
        this.status = status;
        this.valuationOfPlayer = 0;
        this.shooting = 0;
        this.skating = 0;
        this.puckSkills = 0;
        this.competeLevel = 0;
        this.hockeyIQ = 0;
    }

    // EFFECTS: creating constructor for all fields
    public Player(String playerName, String playerPosition, String status, int shooting, int skating,
                  int puckSkills, int competelevel, int hockeyIQ) {
        this.name = playerName;
        this.position = playerPosition;
        this.status = status;
        this.shooting = shooting;
        this.skating = skating;
        this.puckSkills = puckSkills;
        this.competeLevel = competelevel;
        this.hockeyIQ = hockeyIQ;
    }

    // MODIFIES: this
    // EFFECTS: adjusts a player's rating
    public void addSkatingRating(int rating) {
        this.skating += rating;
    }

    // MODIFIES: this
    // EFFECTS: adjusts a player's rating
    public void addShootingRating(int rating) {
        this.shooting += rating;
    }

    // MODIFIES: this
    // EFFECTS: adjusts a player's rating
    public void addPuckSkillsRating(int rating) {
        this.puckSkills += rating;
    }

    // MODIFIES: this
    // EFFECTS: adjusts a player's rating
    public void addCompeteLevelRating(int rating) {
        this.competeLevel += rating;
    }

    // MODIFIES: this
    // EFFECTS: adjusts a player's rating
    public void addHockeyIQRating(int rating) {
        this.hockeyIQ += rating;
    }

    // MODIFIES: this
    // EFFECTS: adjusts a player's rating
    public void setStatusAvailable() {
        this.status = "Available";
    }

    // MODIFIES: this
    // EFFECTS: adjusts a player's rating
    public void setStatusNotAvailable() {
        this.status = "Not Available";
    }

    // public int valueOfPlayer() {
        // Player valuation will be based on in 4 quartiles: 100, 90, 80, 70, 60
        // - 1) 100 will represent the most valuable: overall rating is >= 95
        // - 2) 90 will represent valuable: overall rating is  95 > n >= 85
        // - 3) 80 will represent mediocre value: overall rating is 85 > n > 75
        // - 4) 70 will represent low value: overall rating is 75 > n
    // }

    // REQUIRES: ratings of players >= 0
    // MODIFIES: this
    // EFFECTS: returns the overall ratings of players
    public double getOverallRating() {
        return ((shooting + skating + puckSkills + competeLevel + hockeyIQ) / 5);
    }

    // EFFECTS: returns the skating rating
    public int getSkatingRating() {
        return skating;
    }

    // EFFECTS: returns shooting rating
    public int getShootingRating() {
        return shooting;
    }

    // EFFECTS: returns player's puck skills
    public int getPuckSkillsRating() {
        return puckSkills;
    }

    // EFFECTS: returns player's complete level rating
    public int getCompeteLevel() {
        return competeLevel;
    }

    // EFFECTS: returns player's hockey sense (IQ)
    public int getHockeyIQ() {
        return hockeyIQ;
    }

    // EFFECTS: returns player's status
    public String getStatus() {
        return status;
    }

    // EFFECTS: returns player's name
    public String getName() {
        return name;
    }

    // EFFECTS: return player's position
    public String getPosition() {
        return position;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("position", position);
        json.put("status", status);
        json.put("shooting", shooting);
        json.put("skating", skating);
        json.put("puckSkills", puckSkills);
        json.put("competeLevel", competeLevel);
        json.put("hockeyIQ", hockeyIQ);
        return json;
    }

}
