package model;

import java.util.ArrayList;

public class TeamFranchise {

    private RosterManagement rostermgmt;

    private GeneralManager gm;

    private String teamName;
    private String teamlocation;

    ArrayList<Player> canucks;

    public TeamFranchise(GeneralManager gm, String teamName, String teamlocation) {
        // canucks = new ArrayList<Player>();
        this.gm = new GeneralManager();
        this.teamName = "Canucks";
        this.teamlocation = "Vancouver";
    }

    // Modifies: this
    // Effects: if player on the current team has status, "Available", then each player is going to be
    //          added to the trading block list and removed from the current team
    public void gmAddToTradingBlockFromCurrTeam() {
        ArrayList<Player> playersToRemove = new ArrayList<>();
        for (Player p : gm.getCurrTeam()) {
            if (p.getStatus() == "Available") {
                playersToRemove.add(p);
                gm.addPlayerToTradingBlock(p);
            }
        }
        for (Player p : playersToRemove) {
            gm.removePlayerFromCurrTeam(p);
        }
    }

    // Modifies: this
    // Effects: if a player on the trading block has status "Not Available", then each of those players
    //          is going to be added to the current team and removed from the trading block
    public void gmAddToCurrRosterFromTradingBlock() {
        ArrayList<Player> playersToRemove = new ArrayList<>();
        for (Player p : gm.getTradingBlock()) {
            if (p.getStatus() == "Not Available") {
                playersToRemove.add(p);
                gm.addPlayerToCurrTeam(p);
            }
        }
        for (Player p : playersToRemove) {
            gm.removePlayerFromTradingBlock(p);
            //System.out.println("trading block: " + gm.getTradingBlock());
        }
    }

    // Effects: returns the General Manager
    public GeneralManager getGm() {
        return gm;
    }

    // -- will be for phase 2 ---
    //public void requirements() {
    //}

    // -- will be for phase 2 ---
    //public void adjustRatings() {
    //}

    // -- will be for phase 2 ---
    //public void proposeTrade() {
    //}

    // -- will be for phase 2 ---
    //public void acceptTrade() {
    //}

    // -- will be for phase 2 ---
    //public void declineTrade() {
    //}

    // -- will be for phase 2 ---
    //public void counterTrade() {
    //}
}
