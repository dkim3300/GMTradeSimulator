package ui;

import model.Player;
import java.util.ArrayList;
import java.util.List;

public class CurrentTeamDialog {

    private FranchiseApp franchiseApp;

    // This is where all players current on the roster list will be added to a string array then added to a
    // default model in the FranchiseApp class. With each new addition of players, edited players, or movement of
    // players between the trading block and the current roster will be updated.
    public CurrentTeamDialog(FranchiseApp franchiseApp) {
        this.franchiseApp = franchiseApp;
    }

    // MODIFIES: this
    // EFFECTS: add all the players on the current roster into a string array
    //          then adding each element in the array into a default model in the FranchiseApp class
    public void actionPerform(FranchiseApp franchiseApp) {

        List<String> playersName = new ArrayList<>();

        for (Player p : franchiseApp.getTeam().getGm().getCurrTeam()) {
            playersName.add(p.getName());
        }

        String[] players = playersName.toArray(new String[playersName.size()]);

        franchiseApp.getRosterModelCurrTeam().clear();

        for (int i = 0, n = playersName.size(); i < n; i++) {
            franchiseApp.getRosterModelCurrTeam().addElement(playersName.get(i));
        }
    }

}
