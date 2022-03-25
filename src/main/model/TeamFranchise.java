package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class TeamFranchise implements Writable {

    private RosterManagement rostermgmt;

    private GeneralManager gm;

    private String nm;

    private String teamName;
    private String teamlocation;

    ArrayList<Player> canucks;

    //EFFECTS: making the constructor
    public TeamFranchise(GeneralManager gm, String teamName, String teamlocation) {
        this.gm = new GeneralManager();
        this.teamName = "Canucks";
        this.teamlocation = "Vancouver";
    }

    // MODIFIES: this
    // EFFECTS: if player on the current team has status, "Available", then each player is going to be
    //          added to the trading block list and removed from the current team
    public void gmAddToTradingBlockFromCurrTeam() {
        ArrayList<Player> playersToRemove = new ArrayList<>();
        for (Player p : gm.getCurrTeam()) {
            if (p.getStatus().equals("Available")) {
                playersToRemove.add(p);
                gm.addPlayerToTradingBlock(p);
            }
        }
        for (Player p : playersToRemove) {
            gm.removePlayerFromCurrTeam(p);
        }
    }

    // MODIFIES: this
    // EFFECTS: if a player on the trading block has status "Not Available", then each of those players
    //          is going to be added to the current team and removed from the trading block
    public void gmAddToCurrRosterFromTradingBlock() {
        ArrayList<Player> playersToRemove = new ArrayList<>();
        for (Player p : gm.getTradingBlock()) {
            if (p.getStatus().equals("Not Available")) {
                playersToRemove.add(p);
                gm.addPlayerToCurrTeam(p);
            }
        }
        for (Player p : playersToRemove) {
            gm.removePlayerFromTradingBlock(p);
        }
    }

    // EFFECTS: returns the General Manager
    public GeneralManager getGm() {
        return gm;
    }


    // Referencing WorkRoom
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tradingBlock", tradingBlockToJson());
        json.put("currTeam", currTeamToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray currTeamToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : gm.getCurrTeam()) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray tradingBlockToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : gm.getTradingBlock()) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
