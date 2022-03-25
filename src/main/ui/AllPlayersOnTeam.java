package ui;

import model.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

//
public class AllPlayersOnTeam {

    private JPanel attributesPanel = new JPanel();
    private JLabel attributesLabel = new JLabel();
    private JPanel totalAttributesPanel = new JPanel();
    private ImageIcon jackHughesImage = new ImageIcon("./Photos/Jack_Hughes.png");

    private FranchiseApp franchiseApp;

    // This is where all the players in the franchise application will be added to a string array then
    // added to a default model back to the FranchiseApp class
    public AllPlayersOnTeam(FranchiseApp franchiseApp) {
        this.franchiseApp = franchiseApp;
    }

    // MODIFIES: this
    // EFFECTS: add all the players on from the current roster and trading block into a string array
    //          then adding each element in the array into a default model in the FranchiseApp class
    public void allPlayersInFranchise(FranchiseApp franchiseApp) {

        List<String> totalPlayerList = new ArrayList<>();

        getAllPlayersInFranchise(totalPlayerList);

        String[] totalPlayers = totalPlayerList.toArray(new String[totalPlayerList.size()]);

        franchiseApp.getRosterModelAllPlayer().clear();

        for (int i = 0, n = totalPlayerList.size(); i < n; i++) {
            franchiseApp.getRosterModelAllPlayer().addElement(totalPlayerList.get(i));
        }
    }

    // EFFECTS: players on the current roster and trading block are added to one master list
    public void getAllPlayersInFranchise(List<String> totalPlayerList) {

        for (Player p : franchiseApp.getTeam().getGm().getCurrTeam()) {
            totalPlayerList.add(p.getName());
        }
        for (Player p : franchiseApp.getTeam().getGm().getTradingBlock()) {
            totalPlayerList.add(p.getName());
        }
    }
}
