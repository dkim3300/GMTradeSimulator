package model;

// General functionalities for RosterManagement
// - 1) Separate forwards, defense, goalies
// - 2) Categorize players within their positions
//     - Forwards: Left Wing / Center / Right Wing
//     - Defense:  Left Defence / Right Defence
//     - Goalies:       Starter / Backup


import java.util.ArrayList;
import java.util.List;

public class RosterManagement {

    private ArrayList<Player> roster;

    public RosterManagement() {
        roster = new ArrayList<>();
    }

    // Requires:
    // Modifies: this
    // Effects: adds players into forwards group
    public void forwards() {
        // stub
    }

    // Requires:
    // Modifies: this
    // Effects: adds players into defence group
    public void defense() {
        // stub
    }

    // Requires:
    // Modifies: this
    // Effects: adds goalies into defence group
    public void goalies() {
        // stub
    }

    // Effects: adds a player, p to the roster
    public void addPlayerToRoster(Player player) {
        roster.add(player);
    }

    // Effects: returns the updated current roster
    public ArrayList<Player> currRoster() {
        return roster;
    }

    // Effects: removes a player, p from the roster
    public Player removePlayerFromRoster(Player player) {
        // remove player
        roster.remove(player);
        return player;
    }

    // Effects: returns the size of the current team
    public int sizeCurrTeam() {
        return roster.size();
    }

    // Effects: returns true of players is in current team
    public boolean containCurrTeam(Player player) {
        return roster.contains(player);
    }


}
