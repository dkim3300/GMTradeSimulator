package ui;

import model.GeneralManager;
import model.Player;
import model.TeamFranchise;

import java.util.ArrayList;
import java.util.Scanner;

public class FranchiseApp {

    private ArrayList<Player> tradingBlock;
    private ArrayList<Player> currTeam;

    private GeneralManager gm;

    private Player brockBoeser;
    private Player quinnHughes;
    private Player jackHughes;
    private Player eliasPettersson;
    private Player austonMatthews;
    private Player mitchMarner;

    private String teamName;
    private String teamLocation;

    private TeamFranchise canucks;

    private Scanner input;

    public FranchiseApp() {
        runFranchise();
    }

    private void runFranchise() {
        initialize();

        // This is referencing the TellerApp
        boolean runningFranchise = true;
        String otpionsToClickFrom = "";

        //  This is referencing the TellerApp
        while (runningFranchise) {
            options();
            otpionsToClickFrom = input.next();
            otpionsToClickFrom = otpionsToClickFrom.toLowerCase();

            // This is referencing the TellerApp
            if (otpionsToClickFrom.equals("q")) {
                runningFranchise = false;

            } else {
                execute(otpionsToClickFrom);
            }
        }

        System.out.println("GoodBye!");
    }

    // Referencing from TellerApp
    private void execute(String optiontoClickFrom) {
        if (optiontoClickFrom.equals("c")) {
            pullOutCurrentRoster();
        } else if (optiontoClickFrom.equals("t")) {
            pullOutTradingBlock();
        } else if (optiontoClickFrom.equals("e")) {
            editPlayer();
        } else {
            System.out.println("System Not Valid");
        }
    }

    private void pullOutCurrentRoster() {

        // adding players into trading block
        brockBoeser.setStatusNotAvailable();
        canucks.getGm().getCurrTeam().add(brockBoeser);
        eliasPettersson.setStatusAvailable();
        canucks.getGm().getCurrTeam().add(eliasPettersson);
        quinnHughes.setStatusAvailable();
        canucks.getGm().getCurrTeam().add(quinnHughes);

        // printing out the list of players on the team
        for (Player p : canucks.getGm().getCurrTeam()) {
            System.out.println(p.getName());
        }

        System.out.println("What would you like to do?");
        optionsForCurrRoster();
    }

    private void pullOutTradingBlock() {

        // GeneralManager tradingBlock = new GeneralManager();
        austonMatthews.setStatusAvailable();
        canucks.getGm().getTradingBlock().add(austonMatthews);
        mitchMarner.setStatusNotAvailable();
        canucks.getGm().getTradingBlock().add(mitchMarner);
        jackHughes.setStatusNotAvailable();
        canucks.getGm().getTradingBlock().add(jackHughes);

        // Printing out the players in the trading block
        for (Player p : canucks.getGm().getTradingBlock()) {
            System.out.println(p.getName());
        }

        System.out.println("What would you like to do?");
        optionsForTradeBlock();
    }

    private void optionsForCurrRoster() {
        String select = "";

        while (!select.equals("view status") && !select.equals("move avail players to trading block")) {
            System.out.println("type 'view status' to view a player's status");
            System.out.println("type 'move avail players to trading block' to move available players to trading block");
            select = input.next();
            select = select.toLowerCase();
        }

        if (select.equals("view status")) {
            // Printing out the players in current roster
            for (Player p : canucks.getGm().getCurrTeam()) {
                System.out.println(p.getName() + ": " + p.getStatus());
            }
        } else if (select.equals("move avail players to trading block")) {
            movingPlayersToTradeFromCurrTeam();

        } else {
            System.out.println("not valid option");
        }
    }

    private void optionsForTradeBlock() {
        String select = "";

        // while (!select.equals("view status") && !select.equals("move not avail players to current roster"))
        while (!select.equals("view status") && !select.equals("move not avail player to current roster")) {
            System.out.println("type 'view status' to view player's status");
            System.out.println("type 'move not avail player to current roster' to move a player to the current roster");
            select = input.next();
            select = select.toLowerCase();
            // How do I loop back to the options in "while"
        }

        if (select.equals("view status")) {
            // Printing out the players in the trading block
            for (Player p : canucks.getGm().getTradingBlock()) {
                System.out.println(p.getName() + ": " + p.getStatus());
            }
        } else if (select.equals("move not avail player to current roster")) {
            movingPlayersToCurrTeamFromTrade();

        } else {
            System.out.println("not valid option");
        }
    }

    private void editPlayer() {
        // this is where I can edit players
    }

    private void movingPlayersToCurrTeamFromTrade() {
        System.out.println("____");
        canucks.gmAddToCurrRosterFromTradingBlock();

        for (Player p : canucks.getGm().getCurrTeam()) {
            System.out.println(p.getName());
        }
    }

    private void movingPlayersToTradeFromCurrTeam() {
        System.out.println("____");
        canucks.gmAddToTradingBlockFromCurrTeam();

        for (Player p : canucks.getGm().getTradingBlock()) {
            System.out.println(p.getName());
        }
    }

    // Effects: displays menu of options to user
    private void options() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> pull out current roster");
        System.out.println("\tt -> pull out trading block");
        System.out.println("\te -> edit player");
        System.out.println("\tq -> quit\n");
    }

    private void initialize() {
        quinnHughes = new Player("Quinn Hughes", "Defense");
        brockBoeser = new Player("Brock Boeser", "Forward");
        mitchMarner = new Player("Mitch Marner", "Forward");
        jackHughes = new Player("Jack Hughes", "Forward");
        eliasPettersson = new Player("Elias Pettersson", "Forward");
        austonMatthews = new Player("Auston Matthews", "Forward");

        teamName = "Canucks";
        teamLocation = "Vancouver";

        gm = new GeneralManager();

        canucks = new TeamFranchise(gm, teamName, teamLocation);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }
}
