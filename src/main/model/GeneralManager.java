package model;

// Things I want the GM to be able to do
// - 1) add and remove players on their team
// - 2) add "value" to each player on their team
// - 3) add and remove players my team from a trading block

import java.util.ArrayList;

public class GeneralManager {

    private Player player;

    private ArrayList<Player> tradingBlock;     // Players on the trading block
    private ArrayList<Player> currTeam;          // Current players on this team

    public GeneralManager() {
        tradingBlock = new ArrayList<>();
        currTeam = new ArrayList<>();
    }

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
        //System.out.println("trading block before removing: " + tradingBlock);
        if (tradingBlock.contains(player)) {
            tradingBlock.remove(player);
        }
        //System.out.println("trading block after removing: " + tradingBlock);
        //System.out.println("player: " + player);
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

    // Effects: returns a player from a list
    public Player getPlayer() {
        return player;
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
}
