package ui;

import jdk.nashorn.internal.scripts.JO;
import model.GeneralManager;
import model.Player;
import model.TeamFranchise;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditPlayerDialog {

    private FranchiseApp franchiseApp;

    private JFrame frame = new JFrame();
    private JLabel labelTitle = new JLabel();
    private JLabel positionTitle = new JLabel();
    private JLabel statusTitle = new JLabel();
    private JLabel shootingTitle = new JLabel();
    private JLabel skatingTitle = new JLabel();
    private JLabel puckSkillTitle = new JLabel();
    private JLabel hockeyIQTitle = new JLabel();
    private JLabel completeLevelTitle = new JLabel();
    private JButton upDateButton = new JButton();
    private JPanel panel = new JPanel();

    private JTextField playerNameInput = new JTextField();
    private JTextField playerPositionInput = new JTextField();
    private JTextField statusInput = new JTextField();
    private JTextField shootingInput = new JTextField();
    private JTextField skatingInput = new JTextField();
    private JTextField puckSkillsInput = new JTextField();
    private JTextField hockeyIQInput = new JTextField();
    private JTextField competeLevelInput = new JTextField();

    private List<String> allPlayersName = new ArrayList<>();

    private JComboBox playerComboBox;

    // This is where a player is edited with selected inputs from the user and updated on a panel in the
    // FranchiseApp class
    public EditPlayerDialog(FranchiseApp franchiseApp) {
        this.franchiseApp = franchiseApp;

        labelTitle.setText("Name:");
        panel.add(labelTitle);

        getAllPlayers(allPlayersName);

        String[] players = allPlayersName.toArray(new String[allPlayersName.size()]);

        playerComboBox = new JComboBox(players);

        panel.add(playerComboBox);
        setText();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panelAdd();

        int result = JOptionPane.showConfirmDialog(null, panel, "Edit player",
                                                 JOptionPane.OK_CANCEL_OPTION,
                                                 JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            List<Player> allPlayersObjects = new ArrayList<>();

            getAllPlayerObjects(allPlayersObjects);

            for (Player p : allPlayersObjects) {
                setAttributesOfPlayer(p);
            }
        }
    }

    // EFFECTS: setting the text for each JLabel
    private void setText() {
        positionTitle.setText("Position: ");
        statusTitle.setText("Status: ");
        shootingTitle.setText("Shooting: ");
        skatingTitle.setText("Skating: ");
        puckSkillTitle.setText("Puck Skills: ");
        hockeyIQTitle.setText("Hockey IQ: ");
        completeLevelTitle.setText("Compete Level: ");
    }

    // EFFECTS: adding the labels and inputs to the panel
    private void panelAdd() {
        panel.add(positionTitle);
        panel.add(playerPositionInput);
        panel.add(statusTitle);
        panel.add(statusInput);
        panel.add(shootingTitle);
        panel.add(shootingInput);
        panel.add(skatingTitle);
        panel.add(skatingInput);
        panel.add(puckSkillTitle);
        panel.add(puckSkillsInput);
        panel.add(hockeyIQTitle);
        panel.add(hockeyIQInput);
        panel.add(completeLevelTitle);
        panel.add(competeLevelInput);
    }

    // MODIFIES: this
    // EFFECTS: if the player selected from the combo box equals a name of one of the players in the franchise app
    //          then their attributed are set with the user's inputs
    private void setAttributesOfPlayer(Player p) {
        if (p.getName().equals(playerComboBox.getSelectedItem())) {

            p.setPosition(playerPositionInput.getText());

            int skating = Integer.parseInt(skatingInput.getText());
            p.addSkatingRating(skating);

            int shooting = Integer.parseInt(shootingInput.getText());
            p.addShootingRating(shooting);

            int puckSkills = Integer.parseInt(puckSkillsInput.getText());
            p.addPuckSkillsRating(puckSkills);

            int competeLevel = Integer.parseInt(competeLevelInput.getText());
            p.addCompeteLevelRating(competeLevel);

            int hockeyIQ = Integer.parseInt(hockeyIQInput.getText());
            p.addHockeyIQRating(hockeyIQ);

            if (statusTitle.getText().equals("Available")) {
                p.setStatusNotAvailable();
            } else if (statusTitle.getText().equals("Not Available")) {
                p.setStatusNotAvailable();
            } else {
                p.setStatusNotAvailable();
            }
        }
    }

    // EFFECTS: adding each player from the current team into one master list
    public List<Player> getAllPlayerObjects(List<Player> listOfPlayerObjects) {

        for (Player p : franchiseApp.getTeam().getGm().getCurrTeam()) {
            listOfPlayerObjects.add(p);
        }

        for (Player p : franchiseApp.getTeam().getGm().getTradingBlock()) {
            listOfPlayerObjects.add(p);
        }

        return listOfPlayerObjects;
    }

    // EFFECTS: adding each player from the trading block to one master list
    public List<String> getAllPlayers(List<String> allPlayersName) {

        for (Player p : franchiseApp.getTeam().getGm().getTradingBlock()) {
            allPlayersName.add(p.getName());
        }
        for (Player p : franchiseApp.getTeam().getGm().getCurrTeam()) {
            allPlayersName.add(p.getName());
        }
        return allPlayersName;
    }

}
