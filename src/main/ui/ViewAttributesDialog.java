package ui;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewAttributesDialog {

    private JPanel attributesPanel = new JPanel();
    private JLabel attributesLabel = new JLabel();
    private JPanel totalAttributesPanel = new JPanel();

    private FranchiseApp franchiseApp;

    private JPanel emptyPersonPanel = emptyPerson("./Photos/Empty_Person_Image.png", 195, 150);
    private JPanel quinnHughesPanel = quinnHughes("./Photos/Quinn_Hughes.png", 195, 150);
    private JPanel austonMatthrewsPanel = austonMatthews("./Photos/Auston_Matthews.png", 195, 150);
    private JPanel brockBoeserPanel = brockBoeser("./Photos/Brock_Boeser.png", 195, 150);
    private JPanel eliasPetterssonPanel = eliasPettersson("./Photos/Elias_Pettersson.png", 195, 150);
    private JPanel jackHughesPanel = jackHughes("./Photos/Jack_Hughes.png", 195, 150);

    public ViewAttributesDialog(FranchiseApp franchiseApp) {
        this.franchiseApp = franchiseApp;
    }

    // MODIFIES: this
    // EFFECTS: gets the master list consisting of all players in the franchise application. If a player selected
    //          from the j-list in the FranchiseApp Class equals a name from the master list, then the matched player
    //          is added to the default model in FranchiseApp class
    public void showAttributes(FranchiseApp franchiseApp) {

        List<Player> totalPlayerList = new ArrayList<>();

        totalPlayers(totalPlayerList);

        List<String> playerAttributes = new ArrayList<>();

        franchiseApp.getRosterModelPlayerAttributes().clear();
        franchiseApp.getAttributesPanelTwo().removeAll();

        for (Player p : totalPlayerList) {
            if (franchiseApp.allPlayersFranchise.getSelectedValue() == p.getName()) {
                addElement(franchiseApp, p);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds selected player's name, status, position, skating rating, shooting rating, puck skills rating
    //          compete level rating, hockey IQ rating, and the overall rating of the players
    private void addElement(FranchiseApp franchiseApp, Player p) {

        franchiseApp.getAttributesPanelTwo().add(pickedPLayerLabel(p));
        franchiseApp.getAttributesPanelTwo().setVisible(true);

        franchiseApp.getRosterModelPlayerAttributes().addElement(p.getName() + " || "
                + "Position: " + p.getPosition()
                + " || " + "Status: " + p.getStatus() + " || " + "Skating: " + p.getSkatingRating() + " || "
                + "Shooting: " + p.getShootingRating() + " || " + "Puck Skills: " + p.getPuckSkillsRating() + " || "
                + "Compete Level: " + p.getCompeteLevel() + " || " + "Hockey IQ: " + p.getHockeyIQ() + " || "
                + "Overall Rating: " + p.getOverallRating());
    }

    // MODIFIES: this
    // EFFECTS: if player p, matches the name, return corresponding JPanel
    public JPanel pickedPLayerLabel(Player p) {
        if (p.getName().equals("Quinn Hughes")) {
            return quinnHughesPanel;

        } else if (p.getName().equals("Auston Matthews")) {
            return austonMatthrewsPanel;

        } else if (p.getName().equals("Elias Pettersson")) {
            return eliasPetterssonPanel;

        } else if (p.getName().equals("Brock Boeser")) {
            return brockBoeserPanel;

        } else if (p.getName().equals("Jack Hughes")) {
            return jackHughesPanel;

        } else {
            return emptyPersonPanel;
        }
    }

    // EFFECTS: creating a JPanel for Jack Hughes
    private JPanel jackHughes(String s, int i, int i2) {
        ImageIcon jackHughesIcon = new ImageIcon(s);
        Image jackHughesImage = jackHughesIcon.getImage().getScaledInstance(i, i2, Image.SCALE_SMOOTH);
        ImageIcon jackHughesPic = new ImageIcon(jackHughesImage);

        JLabel jackHughesLabel = new JLabel();
        jackHughesLabel.setIcon(jackHughesPic);

        JPanel jackHughesPanel = new JPanel();
        jackHughesPanel.add(jackHughesLabel);

        return jackHughesPanel;
    }

    // EFFECTS: creating a JPanel for Elias Pettersson
    private JPanel eliasPettersson(String s, int i, int i2) {
        ImageIcon eliasPetterssonIcon = new ImageIcon(s);
        Image eliasPetterssonImage = eliasPetterssonIcon.getImage().getScaledInstance(i, i2, Image.SCALE_SMOOTH);
        ImageIcon eliasPetterssonPic = new ImageIcon(eliasPetterssonImage);

        JLabel eliasPetterssonLabel = new JLabel();
        eliasPetterssonLabel.setIcon(eliasPetterssonPic);

        JPanel eliasPetterssonPanel = new JPanel();
        eliasPetterssonPanel.add(eliasPetterssonLabel);

        return eliasPetterssonPanel;
    }

    // EFFECTS: creationg a JPanel for Brock Boeser
    private JPanel brockBoeser(String s, int i, int i2) {
        ImageIcon brockBoeserIcon = new ImageIcon(s);
        Image brockBoeserImage = brockBoeserIcon.getImage().getScaledInstance(i, i2, Image.SCALE_SMOOTH);
        ImageIcon brockBoeserPic = new ImageIcon(brockBoeserImage);

        JLabel brockBoeserLabel = new JLabel();
        brockBoeserLabel.setIcon(brockBoeserPic);

        JPanel brockBoeserPanel = new JPanel();
        brockBoeserPanel.add(brockBoeserLabel);

        return brockBoeserPanel;
    }

    // EFFECTS: creating an JPanel for Auston Matthews
    private JPanel austonMatthews(String s, int i, int i2) {
        ImageIcon austonMatthewsIcon = new ImageIcon(s);
        Image austonMatthewsImage = austonMatthewsIcon.getImage().getScaledInstance(i, i2, Image.SCALE_SMOOTH);
        ImageIcon austonMatthewsPic = new ImageIcon(austonMatthewsImage);

        JLabel austonMatthewsLabel = new JLabel();
        austonMatthewsLabel.setIcon(austonMatthewsPic);

        JPanel austonMatthewsPanel = new JPanel();
        austonMatthewsPanel.add(austonMatthewsLabel);

        return austonMatthewsPanel;

    }

    // EFFECTS: creating JPanel for an empty person
    private JPanel emptyPerson(String s, int i, int i2) {
        ImageIcon emptyIcon = new ImageIcon(s);
        Image emptyImage = emptyIcon.getImage().getScaledInstance(i, i2, Image.SCALE_SMOOTH);
        ImageIcon emptyPerson = new ImageIcon(emptyImage);

        JLabel emptyPersonLabel = new JLabel();
        emptyPersonLabel.setIcon(emptyPerson);

        JPanel emptyPersonPanel = new JPanel();
        emptyPersonPanel.add(emptyPersonLabel);
        emptyPersonPanel.setVisible(true);

        return emptyPersonPanel;
    }

    // EFFECTS: creating JPanel for Quinn Hughes
    private JPanel quinnHughes(String s, int i, int i2) {
        ImageIcon quinnHughesIcon = new ImageIcon(s);
        Image quinnHughesImage = quinnHughesIcon.getImage().getScaledInstance(i, i2, Image.SCALE_SMOOTH);

        ImageIcon quinnHughes = new ImageIcon(quinnHughesImage);
        JLabel quinnHughesLabel = new JLabel();
        quinnHughesLabel.setIcon(quinnHughes);
        JPanel quinnHughesPanel = new JPanel();

        quinnHughesPanel.add(quinnHughesLabel);
        quinnHughesPanel.setVisible(true);

        return quinnHughesPanel;
    }

    // EFFECTS: adds all the player from the trading block and current roster to one master list
    public void totalPlayers(List<Player> totalPlayerList) {

        for (Player p : franchiseApp.getTeam().getGm().getCurrTeam()) {
            totalPlayerList.add(p);
        }

        for (Player p : franchiseApp.getTeam().getGm().getTradingBlock()) {
            totalPlayerList.add(p);
        }
    }

}
