package ui;

import com.sun.tools.javah.Gen;
import model.GeneralManager;
import model.Player;
import model.TeamFranchise;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TradingBlockDialog {

    private FranchiseApp franchiseApp;
    private GeneralManager gm;
    private TeamFranchise canucks;

    JPanel tradingBlockPanel = new JPanel();

    // This is where the players in the trading block will be displayed on a panel when the "Trading Block" button
    // is clicked. With each new addition of players, edited players, or movement of players between the trading block
    // and the current roster will be updated.
    public TradingBlockDialog(FranchiseApp franchiseApp) {
        this.franchiseApp = franchiseApp;
    }

    // MODIFIES: this
    // EFFECTS: all the players from the trading block will be added to a new list of strings and the string list will
    //          then be put into a string array. For each element in the string array, it will be added to a
    //          default model in the FranchiseApp class
    public void actionPerform(FranchiseApp franchiseApp) {

        List<String> playersName = new ArrayList<>();

        for (Player p : franchiseApp.getTeam().getGm().getTradingBlock()) {
            playersName.add(p.getName());
        }

        String[] players = playersName.toArray(new String[playersName.size()]);

        franchiseApp.getRosterModelTradingBlock().clear();

        for (int i = 0, n = playersName.size(); i < n; i++) {
            franchiseApp.getRosterModelTradingBlock().addElement(playersName.get(i));
        }
    }

}
