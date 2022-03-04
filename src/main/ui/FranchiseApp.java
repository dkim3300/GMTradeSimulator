package ui;

import model.GeneralManager;
import model.Player;
import model.TeamFranchise;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FranchiseApp {

    private static final String GM_Store = "./data/GMteam.json";

    private GeneralManager gm;

    private Player brockBoeser;
    private Player quinnHughes;
    private Player jackHughes;
    private Player eliasPettersson;
    private Player austonMatthews;

    private String teamName;
    private String teamLocation;
    private String nm;

    private TeamFranchise canucks;

    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Effects: runs application and initializes
    public FranchiseApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(GM_Store);
        jsonReader = new JsonReader(GM_Store);
        initialize();
        runFranchise();
    }

    // Modifies: this
    // Effects: runs Franchise user interface
    private void runFranchise() {

        // This is referencing the TellerApp
        boolean runningFranchise = true;
        String optionsToClickFrom = "";

        // This is referencing the TellerApp
        while (runningFranchise) {
            options();
            optionsToClickFrom = input.next();
            optionsToClickFrom = optionsToClickFrom.toLowerCase();

            // This is referencing the TellerApp
            if (optionsToClickFrom.equals("q")) {
                runningFranchise = false;

            } else {
                execute(optionsToClickFrom);
            }
        }

        System.out.println("GoodBye!");
    }

    // Referencing from TellerApp
    // Effect: methods that will be processed with execution
    private void execute(String optiontoClickFrom) {
        if (optiontoClickFrom.equals("c")) {
            pullOutCurrentRoster();
        } else if (optiontoClickFrom.equals("t")) {
            pullOutTradingBlock();
        } else if (optiontoClickFrom.equals("m")) {
            makePlayer();
        } else if (optiontoClickFrom.equals("s")) {
            saveGMRosters();
        } else if (optiontoClickFrom.equals("l")) {
            loadGMRosters();
        } else {
            System.out.println("System Not Valid");
        }
    }

    // Modifies: this
    // Effects: pulls out a list of players from the current roster
    private void pullOutCurrentRoster() {
        System.out.println("____");
        // printing out the list of players on the team
        for (Player p : canucks.getGm().getCurrTeam()) {
            System.out.println(p.getName());
        }

        System.out.println("____");
        System.out.println("What would you like to do?");
        optionsForCurrRoster();
    }

    // Modifies: this
    // Effects: pulls out a list of players from the trading block
    private void pullOutTradingBlock() {
        System.out.println("____");
        // Printing out the players in the trading block
        for (Player p : canucks.getGm().getTradingBlock()) {
            System.out.println(p.getName());
        }

        System.out.println("____");
        System.out.println("What would you like to do?");
        optionsForTradeBlock();
    }

    // Modifies: this
    // Effects: givers user an option to view a player's status from current roster,
    //          and an option to have some players moved depending on the player's status.
    //          If the player's status is "Available", the players will be moved from the current
    //          to the trading block.
    private void optionsForCurrRoster() {
        String select = "";

        while (!select.equals("view status") && !select.equals("move avail players to trading block")) {
            System.out.println("type 'view status' to view a player's status");
            System.out.println("type 'move avail players to trading block' to move available players to trading block");
            select = input.next();
            select = select.toLowerCase();
        }

        if (select.equals("view status")) {
            System.out.println("____");
            for (Player p : canucks.getGm().getCurrTeam()) {
                System.out.println(p.getName() + ": " + p.getStatus());
            }
        } else if (select.equals("move avail players to trading block")) {
            movingPlayersToTradeFromCurrTeam();

        } else {
            System.out.println("____");
            System.out.println("not valid option");
        }
    }

    // Modifies: this
    // Effects: givers user an option to view a player's status from current roster,
    //          and an option to have some players moved depending on the player's status.
    //          If the player's status is "Not Available", the players will be moved from the trading block
    //          to the current roster.
    private void optionsForTradeBlock() {
        String select = "";

        while (!select.equals("view status") && !select.equals("move not avail player to current roster")) {
            System.out.println("type 'view status' to view player's status");
            System.out.println("type 'move not avail player to current roster' to move a player to the current roster");
            select = input.next();
            select = select.toLowerCase();
        }

        if (select.equals("view status")) {
            System.out.println("____");
            for (Player p : canucks.getGm().getTradingBlock()) {
                System.out.println(p.getName() + ": " + p.getStatus());
            }
        } else if (select.equals("move not avail player to current roster")) {
            movingPlayersToCurrTeamFromTrade();

        } else {
            System.out.println("____");
            System.out.println("not valid option");
        }
    }

    // Modifies: this
    // Effects: makes a new player and added into the current roster
    private void makePlayer() {     // move makePlayer into TeamFranchise
        String nm = "";             //   and move chooseName and choosePosition as params to makePlayer
        String p = "";
        String status = "";         // also make edit player and do same thing

        Player player1;

        player1 = new Player(chooseName(nm), choosePosition(p));

        if (editStatus() == true) {
            player1.setStatusAvailable();
        } else {
            player1.setStatusNotAvailable();
        }

        canucks.getGm().addPlayerToCurrTeam(player1);

        System.out.println("____");
        for (Player plyer: canucks.getGm().getCurrTeam()) {
            System.out.println(plyer.getName());
        }
    }

    // Modifies: this
    // Effects: returns the name of an arbitrary player
    private String chooseName(String nm) {
        nm = "";

        System.out.println("Enter player's name:");
        nm = input.next();
        //nm = nm.toLowerCase();
        return nm;
    }

    // Modifies: this
    // Effects: returns the position of an arbitrary player
    private String choosePosition(String p) {
        p = "";

        System.out.println("Enter player's position:");
        p = input.next();
        //p = p.toLowerCase();
        return p;
    }

    // Modifies: this
    // Effects: returns true if the inputted status of the player by the user is
    //          true, false otherwise
    private boolean editStatus() {
        String status = "";

        System.out.println("Enter player's status:");
        status = input.next();

        if (status.equals("Available")) {      // how do I debug this? --> ask TA
            return true;
        } else {
            return false;   // when comparing strings, use .equals()
        }
    }

    // Modifies: this
    // Effects: moves a player to the current roster from the trading block and
    //          prints out the list of players from the updated current roster
    private void movingPlayersToCurrTeamFromTrade() {
        canucks.gmAddToCurrRosterFromTradingBlock();

        System.out.println("____");
        for (Player p : canucks.getGm().getCurrTeam()) {
            System.out.println(p.getName());
        }
    }

    // Modifies: this
    // Effects: moves a player to the trading block from the current roster and
    //          prints out the list of players from the updated trading block
    private void movingPlayersToTradeFromCurrTeam() {
        canucks.gmAddToTradingBlockFromCurrTeam();

        System.out.println("____");
        for (Player p : canucks.getGm().getTradingBlock()) {
            System.out.println(p.getName());
        }
    }

    // Effects: projects the options available for the user
    private void options() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> pull out current roster");
        System.out.println("\tt -> pull out trading block");
        System.out.println("\tm -> make player");
        System.out.println("\ts -> save current team and roster to file");   // just added
        System.out.println("\tl -> load current team and roster from file");  // just added
        System.out.println("\tq -> quit\n");
    }

    // Modifies: this
    // Effects: initializes team franchise, players, general manager
    private void initialize() {
        canucks = new TeamFranchise(gm, teamName, teamLocation);
        quinnHughes = new Player("Quinn Hughes", "Defense");
        brockBoeser = new Player("Brock Boeser", "Forward");
        eliasPettersson = new Player("Elias Pettersson", "Forward");
        jackHughes = new Player("Jack Hughes", "Forward");
        austonMatthews = new Player("Auston Matthews", "Foward");
        teamName = "Canucks";
        teamLocation = "Vancouver";
        gm = new GeneralManager();
        quinnHughes.setStatusAvailable();
        brockBoeser.setStatusAvailable();
        eliasPettersson.setStatusNotAvailable();
        jackHughes.setStatusNotAvailable();
        austonMatthews.setStatusNotAvailable();
        canucks.getGm().addPlayerToCurrTeam(brockBoeser);
        canucks.getGm().addPlayerToCurrTeam(eliasPettersson);
        canucks.getGm().addPlayerToCurrTeam(quinnHughes);
        canucks.getGm().addPlayerToTradingBlock(jackHughes);
        canucks.getGm().addPlayerToTradingBlock(austonMatthews);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: saves the GM rosters to file
    public void saveGMRosters() {
        try {
            jsonWriter.open();
            jsonWriter.write(canucks);
            jsonWriter.close();
            System.out.println("Saved " + "current roster and trading block" + " to " + GM_Store);
        } catch (FileNotFoundException e) {
            System.out.println("Not able to write file: " + GM_Store);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads GM rosters from file
    public void loadGMRosters() {
        try {
            canucks = jsonReader.read();
            System.out.println("Loaded " + "current roster and trading block" + " from " + GM_Store);
        } catch (IOException e) {
            System.out.println("Not able to read from file: " + GM_Store);
        }
    }

}

