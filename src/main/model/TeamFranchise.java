package model;

// TeamFranchise would have an admin variable here
// team has variables for gm, admin, rostermanagement, player
//    - each of those variables that points back tot the team they are associated with
// Use stories to focus on
// - 1) implementing adding/removing players from roster
// - 2) adding/removing players from a trading block

import com.sun.tools.javah.Gen;

import java.util.ArrayList;

public class TeamFranchise {

    private RosterManagement rostermgmt;

    private GeneralManager gm;

    private String teamName;
    private String teamlocation;

    ArrayList<Player> canucks;

    //public TeamFranchise() {
    //    canucks = new ArrayList<Player>();
    //    rostermgmt = new RosterManagement();
    //    gm = new GeneralManager();
    //    teamName = "Canucks";
    //    teamlocation = "Vancouver";
    //}

    public TeamFranchise(GeneralManager gm, String teamName, String teamlocation) {
        // canucks = new ArrayList<Player>();
        this.gm = new GeneralManager();
        this.teamName = "Canucks";
        this.teamlocation = "Vancouver";
    }

    // Requires:
    // Modifies:
    // Effects:
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

    // Requires:
    // Modifies:
    // Effects:
    public void gmAddToCurrRosterFromTradingBlock() {
        System.out.println("trading block: " + gm.getTradingBlock());
        ArrayList<Player> playersToRemove = new ArrayList<>();
        for (Player p : gm.getTradingBlock()) {
            if (p.getStatus() == "Not Available") {
                playersToRemove.add(p);
                gm.addPlayerToCurrTeam(p);
            }
        }
        for (Player p : playersToRemove) {
            gm.removePlayerFromTradingBlock(p);
            System.out.println("trading block: " + gm.getTradingBlock());
        }
    }

    // Effects: returns the General Manager
    public GeneralManager getGm() {
        return gm;
    }

    public void requirements() {

    }

    public void adjustRatings() {

    }

    public void proposeTrade() {

    }

    public void acceptTrade() {

    }

    public void declineTrade() {

    }

    public void counterTrade() {

    }
}
