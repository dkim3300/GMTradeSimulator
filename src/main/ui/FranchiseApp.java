package ui;

import model.GeneralManager;
import model.Player;
import model.TeamFranchise;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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

    private TeamFranchise canucks;

    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs application and initializes
    public FranchiseApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(GM_Store);
        jsonReader = new JsonReader(GM_Store);
        initialize();
        runFranchise();
    }

    // MODIFIES: this
    // EFFECTS: runs Franchise user interface
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
    // EFFECTS: methods that will be processed with execution
    private void execute(String optiontoClickFrom) {
        if (optiontoClickFrom.equals("c")) {
            pullOutCurrentRoster();
        } else if (optiontoClickFrom.equals("t")) {
            pullOutTradingBlock();
        } else if (optiontoClickFrom.equals("m")) {
            makePlayer();
        } else if (optiontoClickFrom.equals("e")) {
            editPlayer();
        } else if (optiontoClickFrom.equals("s")) {
            saveGMRosters();
        } else if (optiontoClickFrom.equals("l")) {
            loadGMRosters();
        } else {
            System.out.println("System Not Valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: pulls out a list of players from the current roster
    private void pullOutCurrentRoster() {
        System.out.println("--------");
        // printing out the list of players on the team
        for (Player p : canucks.getGm().getCurrTeam()) {
            System.out.println(p.getName());
        }

        System.out.println("--------");
        System.out.println("What would you like to do?");
        optionsForCurrRoster();
    }

    // MODIFIES: this
    // EFFECTS: pulls out a list of players from the trading block
    private void pullOutTradingBlock() {
        System.out.println("--------");
        // Printing out the players in the trading block
        for (Player p : canucks.getGm().getTradingBlock()) {
            System.out.println(p.getName());
        }

        System.out.println("--------");
        System.out.println("What would you like to do?");
        optionsForTradeBlock();
    }

    // MODIFIES: this
    // EFFECTS: givers user an option to view a player's status from current roster,
    //          and an option to have some players moved depending on the player's status.
    //          If the player's status is "Available", the players will be moved from the current
    //          to the trading block.
    private void optionsForCurrRoster() {
        String select = "";

        while (!select.equals("v") && !select.equals("m") && !select.equals("a")) {
            System.out.println("type 'v' to view a player's status");
            System.out.println("type 'm' to move available players to trading block");
            System.out.println("type 'a' to view player's attributes");
            select = input.next();
            select = select.toLowerCase();
        }

        if (select.equals("v")) {
            System.out.println("-------");
            for (Player p : canucks.getGm().getCurrTeam()) {
                System.out.println(p.getName() + ": " + p.getStatus());
            }
        } else if (select.equals("m")) {
            System.out.println("-------");
            movingPlayersToTradeFromCurrTeam();

        } else if (select.equals("a")) {
            System.out.println("-------");
            printAttributesCurrTeam();

        } else {
            System.out.println("-------");
            System.out.println("not valid option");
        }
    }

    // EFFECTS: printing out attributes for each player on the current team
    public void printAttributesCurrTeam() {

        for (Player p : canucks.getGm().getCurrTeam()) {
            System.out.println(p.getName() + ": " + "Skating: " + p.getSkatingRating() + " | " + "Shooting: "
                    + p.getShootingRating() + " | " + "Puck Skills: " + p.getPuckSkillsRating() + " | "
                    + "Compete Level: " + p.getCompeteLevel() + " | " + "Hockey IQ: " + p.getHockeyIQ() + " | "
                    + "Overall Rating: " + p.getOverallRating());
        }
    }

    // MODIFIES: this
    // EFFECTS: givers user an option to view a player's status from current roster,
    //          and an option to have some players moved depending on the player's status.
    //          If the player's status is "Not Available", the players will be moved from the trading block
    //          to the current roster.
    private void optionsForTradeBlock() {
        String select = "";

        while (!select.equals("v") && !select.equals("m") && !select.equals("a")) {
            System.out.println("type 'v' to view player's status");
            System.out.println("type 'm' to move player's who are not available to the current roster");
            System.out.println("type 'a' to view player's attributes");
            select = input.next();
            select = select.toLowerCase();
        }

        if (select.equals("v")) {
            System.out.println("-------");
            for (Player p : canucks.getGm().getTradingBlock()) {
                System.out.println(p.getName() + ": " + p.getStatus());
            }
        } else if (select.equals("m")) {
            System.out.println("-------");
            movingPlayersToCurrTeamFromTrade();

        } else if (select.equals("a")) {
            System.out.println("-------");
            printAttributesTradingBlock();

        } else {
            System.out.println("-------");
            System.out.println("not valid option");
        }
    }

    // EFFECTS: printing out all the attributed for each player on the trading block
    public void printAttributesTradingBlock() {

        for (Player p : canucks.getGm().getTradingBlock()) {
            System.out.println(p.getName() + ": " + "Skating: " + p.getSkatingRating() + " | " + "Shooting: "
                    + p.getShootingRating() + " | " + "Puck Skills: " + p.getPuckSkillsRating() + " | "
                    + "Compete Level: " + p.getCompeteLevel() + " | " + "Hockey IQ: " + p.getHockeyIQ() + " | "
                    + "Overall Rating: " + p.getOverallRating());
        }
    }

    // MODIFIES: this
    // EFFECTS: makes a new player and added into the current roster
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

        System.out.println("--------");
        for (Player plyer: canucks.getGm().getCurrTeam()) {
            System.out.println(plyer.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: returns the name of an arbitrary player
    private String chooseName(String nm) {
        nm = "";

        System.out.println("Enter player's name:");
        nm = input.next();
        return nm;
    }

    // MODIFIES: this
    // EFFECTS: returns the position of an arbitrary player
    private String choosePosition(String p) {
        p = "";

        System.out.println("Enter player's position:");
        p = input.next();
        return p;
    }

    // MODIFIES: this
    // EFFECTS: returns true if the inputted status of the player by the user is
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

    // MODIFIES: this
    // EFFECTS: moves a player to the current roster from the trading block and
    //          prints out the list of players from the updated current roster
    private void movingPlayersToCurrTeamFromTrade() {
        canucks.gmAddToCurrRosterFromTradingBlock();

        System.out.println("--------");
        for (Player p : canucks.getGm().getCurrTeam()) {
            System.out.println(p.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: moves a player to the trading block from the current roster and
    //          prints out the list of players from the updated trading block
    private void movingPlayersToTradeFromCurrTeam() {
        canucks.gmAddToTradingBlockFromCurrTeam();

        System.out.println("--------");
        for (Player p : canucks.getGm().getTradingBlock()) {
            System.out.println(p.getName());
        }
    }

    // EFFECTS: projects the options available for the user
    private void options() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> pull out current roster");
        System.out.println("\tt -> pull out trading block");
        System.out.println("\tm -> make player");
        System.out.println("\te -> edit player");
        System.out.println("\ts -> save current team and roster to file");   // just added
        System.out.println("\tl -> load current team and roster from file");  // just added
        System.out.println("\tq -> quit\n");
    }

    // MODIFIES: this
    // EFFECTS: editing each player in the franchise application
    public void editPlayer() {
        // adding all players from each team into one master player list
        String name = "";
        ArrayList<Player> masterList = new ArrayList<>();

        for (Player p : canucks.getGm().getCurrTeam()) {
            masterList.add(p);
        }

        for (Player p : canucks.getGm().getTradingBlock()) {
            masterList.add(p);
        }

        for (Player p : masterList) {
            System.out.println(p.getName());
        }

        // options to enter a player's name from the master list who the user wants to edit
        System.out.println("--------");
        System.out.println("From players above, enter a player's name to edit");
        name = input.next();

        for (Player p : masterList) {
            if (p.getName().equals(name)) {
                setAttributes(p);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: setting attributes (skating, shooting, puck skills, compete level, hockey IQ) to each player selected
    //          by the use
    public void setAttributes(Player p) {
        int shooting = 0;
        int skating = 0;
        int puckSkills = 0;
        int hockeyIQ = 0;
        int competeLevel = 0;

        p.addShootingRating(shooting(shooting));

        p.addSkatingRating(skating(skating));

        p.addPuckSkillsRating(puckSkills(puckSkills));

        p.addHockeyIQRating(hockeyIQ(hockeyIQ));

        p.addCompeteLevelRating(completeLevel(competeLevel));

        System.out.println("--------");
        System.out.println("Successfully added attributes to " + p.getName() + "!");
        System.out.println("--------");
    }

    // EFFECTS: setting the player's shooting attribute from integer, s
    public int shooting(int s) {

        System.out.println("Set shooting rating between range [1, 5]");
        s = Integer.parseInt(input.next());

        while (s < 1 || s > 5) {
            if (s < 1 || s > 5) {
                System.out.println("--------");
                System.out.println("Invalid range, please try again between range [1, 5]");
            }
            s = Integer.parseInt(input.next());
        }
        return s;
    }

    // EFFECTS: setting the player's skating attribute from integer, s
    public int skating(int s) {

        System.out.println("Set skating rating between range [1,5]");
        s = Integer.parseInt(input.next());

        while (s < 1 || s > 5) {
            if (s < 1 || s > 5) {
                System.out.println("--------");
                System.out.println("Invalid range, please try again between range [1, 5]");
            }
            s = Integer.parseInt(input.next());
        }
        return s;

    }

    // EFFECTS: setting the player's puck skills attribute from integer, ps
    public int puckSkills(int ps) {

        System.out.println("Set puck skills rating between range [1,5]");
        ps = Integer.parseInt(input.next());

        while (ps < 1 || ps > 5) {
            if (ps < 1 || ps > 5) {
                System.out.println("--------");
                System.out.println("Invalid range, please try again between range [1, 5]");
            }
            ps = Integer.parseInt(input.next());
        }
        return ps;

    }

    // EFFECTS: setting the player's hockey IQ attribute from integer, iq
    public int hockeyIQ(int iq) {

        System.out.println("Set hockey IQ rating between range [1,5]");
        iq = Integer.parseInt(input.next());

        while (iq < 1 || iq > 5) {
            if (iq < 1 || iq > 5) {
                System.out.println("--------");
                System.out.println("Invalid range, please try again between range [1, 5]");
            }
            iq = Integer.parseInt(input.next());
        }
        return iq;
    }

    // EFFECTS: setting the player's compete level attribute from integer, cl
    public int completeLevel(int cl) {

        System.out.println("Set compete level rating between range [1, 5]");
        cl = Integer.parseInt(input.next());

        while (cl < 1 || cl > 5) {
            if (cl < 1 || cl > 5) {
                System.out.println("--------");
                System.out.println("Invalid range, please try again between range [1, 5]");
            }
            cl = Integer.parseInt(input.next());
        }
        return cl;
    }

    // MODIFIES: this
    // EFFECTS: initializes team franchise, players, general manager
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

    // Referencing WorkRoom
    // EFFECTS: saves the GM rosters to file
    public void saveGMRosters() {
        try {
            jsonWriter.open();
            jsonWriter.write(canucks);
            jsonWriter.close();
            System.out.println("Saved " + "current roster and trading block" + " to: " + GM_Store);
        } catch (FileNotFoundException e) {
            System.out.println("Not able to write file: " + GM_Store);
        }
    }

    // Referencing WorkRoom
    // MODIFIES: this
    // EFFECTS: loads GM rosters from file
    public void loadGMRosters() {
        try {
            canucks = jsonReader.read();
            System.out.println("Loaded " + "current roster and trading block" + " from: " + GM_Store);
        } catch (IOException e) {
            System.out.println("Not able to read from file: " + GM_Store);
        }
    }

}

