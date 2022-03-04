package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class GeneralManager {

    private ArrayList<Player> tradingBlock;      // Players on the trading block
    private ArrayList<Player> currTeam;          // Current players on this team

    // EFFECTS: making the constructor
    public GeneralManager() {
        tradingBlock = new ArrayList<>();
        currTeam = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds player to the trading block if they are not already there
    public void addPlayerToTradingBlock(Player player) {
        if (!tradingBlock.contains(player)) {
            tradingBlock.add(player);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes player from the trading block if there, but nothing happens if not
    public Player removePlayerFromTradingBlock(Player player) {
        if (tradingBlock.contains(player)) {
            tradingBlock.remove(player);
        }
        return player;
    }

    // MODIFIES: this
    // EFFECTS: adds players to the current team if not there
    public void addPlayerToCurrTeam(Player player) {
        if (!currTeam.contains(player)) {
            currTeam.add(player);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes player from current team if there, but does nothing otherwise
    public Player removePlayerFromCurrTeam(Player player) {
        if (currTeam.contains(player)) {
            currTeam.remove(player);
        }
        return player;
    }

    // EFFECTS: returns trading block
    public ArrayList<Player> getTradingBlock() {
        return tradingBlock;
    }

    // EFFECTS: returns current roster
    public ArrayList<Player> getCurrTeam() {
        return currTeam;
    }

    // EFFECTS: returns true if player is in trading block
    public boolean containTradingBlock(Player player) {
        return tradingBlock.contains(player);
    }

    // EFFECTS: returns size of trading block
    public int sizeTradingBlock() {
        return tradingBlock.size();
    }

    // EFFECTS: return true if player is in current team
    public boolean containCurrTeam(Player player) {
        return currTeam.contains(player);
    }

    // EFFECTS: return size of current team
    public int sizeCurrTeam() {
        return currTeam.size();
    }

}
