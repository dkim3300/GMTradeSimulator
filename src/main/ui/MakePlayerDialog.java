package ui;

import model.Player;
import model.TeamFranchise;

import javax.swing.*;

public class MakePlayerDialog {

    private FranchiseApp franchiseApp;
    private TeamFranchise canucks;
    private Player newPlayer;

    JFrame frame = new JFrame();
    JLabel labelTitle = new JLabel();
    JLabel positionTitle = new JLabel();
    JLabel statusTitle = new JLabel();
    JLabel shootingTitle = new JLabel();
    JLabel skatingTitle = new JLabel();
    JLabel puckSkillTitle = new JLabel();
    JLabel hockeyIQTitle = new JLabel();
    JLabel completeLevelTitle = new JLabel();
    JPanel panel = new JPanel();
    JPanel fullPanel = new JPanel();
    JButton upDateButton = new JButton();

    JTextField playerNameInput = new JTextField();
    JTextField playerPositionInput = new JTextField();
    JTextField statusInput = new JTextField();
    JTextField shootingInput = new JTextField();
    JTextField skatingInput = new JTextField();
    JTextField puckSkillsInput = new JTextField();
    JTextField hockeyIQInput = new JTextField();
    JTextField competeLevelInput = new JTextField();
    Box boxPanel = Box.createVerticalBox();
    Box boxTitle = Box.createHorizontalBox();

    // This is where a new player is created when the user clicks the "Make Player" button. This class will
    // create a new player with the user inputs and update the current roster
    public MakePlayerDialog(FranchiseApp franchiseApp) {
        this.franchiseApp = franchiseApp;

        setText();
        panelAdd();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        int result = JOptionPane.showConfirmDialog(null, panel, "Make Player",
                                                   JOptionPane.OK_CANCEL_OPTION,
                                                   JOptionPane.PLAIN_MESSAGE);

        newPlayer = new Player(playerNameInput.getText(), playerPositionInput.getText());

        if (result == JOptionPane.OK_OPTION) {
            setAttributesOfNewPlayer(franchiseApp);
        }
    }

    // EFFECTS: JLabel and JText added to a panel
    private void panelAdd() {
        panel.add(labelTitle);
        panel.add(playerNameInput);
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

    // EFFECTS: setting JText
    private void setText() {
        labelTitle.setText("Name: ");
        positionTitle.setText("Position: ");
        statusTitle.setText("Status: ");
        shootingTitle.setText("Shooting: ");
        skatingTitle.setText("Skating: ");
        puckSkillTitle.setText("Puck Skills: ");
        hockeyIQTitle.setText("Hockey IQ: ");
        completeLevelTitle.setText("Compete Level: ");
    }

    // MODIFIES: this
    // EFFECTS: setting a newly created player with the user's inputs and adding the new player to the current team
    private void setAttributesOfNewPlayer(FranchiseApp franchiseApp) {

        if (statusInput.getText().equals("Available")) {
            newPlayer.setStatusAvailable();
        } else if (statusInput.getText().equals("Not Available")) {
            newPlayer.setStatusNotAvailable();
        } else {
            newPlayer.setStatusNotAvailable();
        }
        int shooting = Integer.parseInt(shootingInput.getText());
        newPlayer.addShootingRating(shooting);

        int skating = Integer.parseInt(skatingInput.getText());
        newPlayer.addSkatingRating(skating);

        int puckSkills = Integer.parseInt(puckSkillsInput.getText());
        newPlayer.addPuckSkillsRating(puckSkills);

        int hockeyIQ = Integer.parseInt(hockeyIQInput.getText());
        newPlayer.addHockeyIQRating(hockeyIQ);

        int competeLevel = Integer.parseInt(competeLevelInput.getText());
        newPlayer.addCompeteLevelRating(competeLevel);

        franchiseApp.getTeam().getGm().getCurrTeam().add(newPlayer);
    }

}
