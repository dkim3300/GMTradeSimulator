package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class GeneralManager {

    //private String name;

    private ArrayList<Player> tradingBlock;      // Players on the trading block
    private ArrayList<Player> currTeam;          // Current players on this team

    public GeneralManager() {
        //this.name = name;
        tradingBlock = new ArrayList<>();
        currTeam = new ArrayList<>();
    }
/*
    public GeneralManager(String name) {
        this.name = name;
        tradingBlock = new ArrayList<>();
        currTeam = new ArrayList<>();
    }

 */

    // Effects: return the name of the gm
    //public String getName() {
    //    return name;
    //}

    // Modifies: this
    // Effects: adds player to the trading block if they are not already there
    public void addPlayerToTradingBlock(Player player) {
        if (!tradingBlock.contains(player)) {
            tradingBlock.add(player);
        }
    }

    // Modifies: this
    // Effects: removes player from the trading block if there, but nothing happens if not
    public Player removePlayerFromTradingBlock(Player player) {
        if (tradingBlock.contains(player)) {
            tradingBlock.remove(player);
        }
        return player;
    }

    // Modifies: this
    // Effects: adds players to the current team if not there
    public void addPlayerToCurrTeam(Player player) {
        if (!currTeam.contains(player)) {
            currTeam.add(player);
        }
    }

    // Modifies: this
    // Effects: removes player from current team if there, but does nothing otherwise
    public Player removePlayerFromCurrTeam(Player player) {
        if (currTeam.contains(player)) {
            currTeam.remove(player);
        }
        return player;
    }

    // Effect: returns trading block
    public ArrayList<Player> getTradingBlock() {
        return tradingBlock;
    }

    // Effect: returns current roster
    public ArrayList<Player> getCurrTeam() {
        return currTeam;
    }

    // Effects: returns true if player is in trading block
    public boolean containTradingBlock(Player player) {
        return tradingBlock.contains(player);
    }

    // Effect: returns size of trading block
    public int sizeTradingBlock() {
        return tradingBlock.size();
    }

    // Effect: return true if player is in current team
    public boolean containCurrTeam(Player player) {
        return currTeam.contains(player);
    }

    // Effect: return size of current team
    public int sizeCurrTeam() {
        return currTeam.size();
    }
/*
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("name", name);
        json.put("tradingBlock", tradingBlockToJson());
        json.put("currTeam", currTeamToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray currTeamToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : currTeam) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray tradingBlockToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : tradingBlock) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

 */

}
