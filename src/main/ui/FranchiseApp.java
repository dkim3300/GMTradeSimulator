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
        boolean keepGoing = true;
        String command = "";

        initialize();

        // Referencing the TellerApp from
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            // Referencing the TellerApp
            if (command.equals("q")) {
                keepGoing = false;

            } else {
                doCommand(command);
            }
        }

        System.out.println("GoodBye!");
    }

    // Referencing from TellerApp
    private void doCommand(String command) {
        if (command.equals("c")) {
            pullOutCurrentRoster();
        } else if (command.equals("t")) {
            pullOutTradingBlock();
        } else if (command.equals("e")) {
            editPlayer();
        } else {
            System.out.println("System Not Valid");
        }
    }

    private void pullOutCurrentRoster() {
        // want to be able to pull out player here
        // also, have the option to pull out player's stats
        // also, have option to change player's status
        //    - and if player's status is changed, remove it
        //      from the current roster and add it to the trade block

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
        // need to pull out players on the trade block (print out list of players out)
        // want to be able to pull out trading block players
        // all players here should have the player status' as "Available"
        // players who has status "Not Available can not be in this list
        // if user wants changes mind and wants to move player out of trading block,
        //     change status to, have the option of removing the playing from the
        //     trading block to the current roster

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

        // while (!select.equals("view status") && !select.equals("move avail players to trading block"))
        while (!select.equals("view status") && !select.equals("move not avail player to current roster")) {
            System.out.println("type 'view status' to view player's status");
            System.out.println("type 'move avail players to trading block' to move a player to the current roster");
            select = input.next();
            select = select.toLowerCase();
            // How do I loop back to the options in "while"
        }

        if (select.equals("view status")) {
            // Printing out the players in the trading block
            for (Player p : canucks.getGm().getTradingBlock()) {
                System.out.println(p.getName() + ": " + p.getStatus());
            }
        } else if (select.equals("move a player to current roster")) {
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
    private void displayMenu() {
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
