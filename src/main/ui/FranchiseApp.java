package ui;

import model.GeneralManager;
import model.Player;
import model.TeamFranchise;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FranchiseApp extends JFrame implements ActionListener {

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

    private JButton jbuttonCurrTeam = new JButton();
    private JButton jbuttonMakePlayer = new JButton();
    private JButton jbuttonEditPlayer = new JButton();
    private JButton jbuttonTradingBlock = new JButton();
    private JButton moveToTradingBlock = new JButton();
    private JButton moveToCurrentRoster = new JButton();
    private JButton viewAttributes = new JButton();
    private JButton allPlayersButton = new JButton();
    private JMenuBar menuBar = new JMenuBar();

    private JMenu menuFile = new JMenu("File");
    private JMenu menuEdit = new JMenu("Edit");
    private JMenu menuHelp = new JMenu("Help");

    private JMenuItem loadItem = new JMenuItem("Load");
    private JMenuItem saveItem = new JMenuItem("Save");

    JPanel jpanelFranchiseOptions = new JPanel();
    JPanel jpanelCurrRoster = new JPanel();
    JPanel jpanelButtons = new JPanel();
    JPanel jpanelTradingBlock = new JPanel();
    JFrame jframe = new JFrame();
    JPanel attributesPanel = new JPanel();
    JPanel attributesPanelTwo = new JPanel();
    JPanel allPlayersFranchisePanel = new JPanel();
    JLabel jlabelAttributes = new JLabel("Player Attributes");
    JLabel jlabelOptions = new JLabel("Franchise Options");
    JLabel currRosterLabel = new JLabel("Current Roster");
    JLabel tradingBlockLabel = new JLabel("Trading Block");
    JLabel allPlayersFranchiseLabel = new JLabel("All Franchise Players");

    DefaultListModel rosterModelAllPlayer = new DefaultListModel();
    JList allPlayersFranchise = new JList(rosterModelAllPlayer);

    DefaultListModel rosterModelPlayerAttributes = new DefaultListModel();
    JList playerAttributesList = new JList(rosterModelPlayerAttributes);

    DefaultListModel rosterModelCurrTeam = new DefaultListModel();
    JList currRosterJList = new JList(rosterModelCurrTeam);

    DefaultListModel rosterModelTradingBlock = new DefaultListModel();
    JList tradingRosterJList = new JList(rosterModelTradingBlock);

    DefaultListModel rosterModelPlayerAttributesTwo = new DefaultListModel();
    JList playerImageList = new JList(rosterModelPlayerAttributesTwo);

    CurrentTeamDialog currentTeamDialog = new CurrentTeamDialog(this);
    TradingBlockDialog tradingBlockDialog = new TradingBlockDialog(this);
    ViewAttributesDialog viewAttributesDialog = new ViewAttributesDialog(this);
    AllPlayersOnTeam allPlayersOnTeam = new AllPlayersOnTeam(this);

    // EFFECTS: runs application and initializes
    public FranchiseApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(GM_Store);
        jsonReader = new JsonReader(GM_Store);
        initializeGraphics();
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
    public void makePlayer() {        // move makePlayer into TeamFranchise
        String nm = "";               //   and move chooseName and choosePosition as params to makePlayer
        String p = "";
        String status = "";           // also make edit player and do same thing

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

    // EFFECTS: setting the player's hockey IQ attributes from integer, iq
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

    // GETTER
    public TeamFranchise getTeam() {
        return canucks;
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

    @SuppressWarnings("checkstyle:MethodLength")
    public void initializeGraphics() {                     // using 24 lines
        currTeamButton(jbuttonCurrTeam, "Current Roster");
        tradingBlockButton(jbuttonTradingBlock, "Trading Block");
        editPlayerButton(jbuttonEditPlayer, "Edit Player");
        makePlayerButton(jbuttonMakePlayer, "Make Player");
        jlabelAttributesSet(jlabelAttributes);
        viewAttributesSetting(viewAttributes, "View Attributes");
        jlabelOptionsSet(jlabelOptions);
        attributePanelSet(jlabelAttributes);
        loadAndSaveItem();
        menuFileAdd();
        menuBarAdd();
        jframeSet();
        franchiseOptionsPanel(jlabelOptions);
        jpanelCurrRosterSet(jpanelCurrRoster, 210);
        currRosterLabelSet(currRosterLabel);
        jpanelButtonsSet();
        moveToTradingBlockSet(moveToTradingBlock, "Move To Trading Block", jpanelButtons);
        moveToCurrRosterSet(moveToCurrentRoster, "Move To Current Roster", jpanelButtons);
        allPlayersButtonSet(allPlayersButton, "All Players", jpanelFranchiseOptions);
        tradingBlockPanelSet(jpanelTradingBlock, 720);
        tradingBlockLabelSet(currRosterLabel, tradingBlockLabel, jpanelTradingBlock);
        allPlayersFranchisePanelSet(allPlayersFranchisePanel, 975);
        allPlayersFranchiseLabelSet(currRosterLabel, allPlayersFranchiseLabel, allPlayersFranchisePanel);
        panelAddingJlists();
        jframeAdd();
    }

    // MODIFIES: this
    // EFFECTS: adding JLists to JPanel
    private void panelAddingJlists() {
        allPlayersFranchisePanel.add(allPlayersFranchise);
        jpanelCurrRoster.add(currRosterJList);
        jpanelTradingBlock.add(tradingRosterJList);
    }

    // EFFECTS: conducting AccActionListener to the load and save JMenu
    private void loadAndSaveItem() {
        loadItem.addActionListener(this::actionPerformed);
        saveItem.addActionListener(this::actionPerformed);
    }

    // MODIFIES: this
    // EFFECTS: adding JSwing elements to JFrame
    private void jframeAdd() {
        attributesPanelTwo.setBounds(210, 335, 1015, 190);
        attributesPanelTwo.setBackground(Color.lightGray);
        attributesPanelTwo.add(playerImageList);

        jframe.add(jpanelFranchiseOptions);
        jframe.add(jpanelCurrRoster);
        jframe.add(jpanelButtons);
        jframe.add(jpanelTradingBlock);
        jframe.add(allPlayersFranchisePanel);
        jframe.add(attributesPanel);
        jframe.add(attributesPanelTwo);
        jframe.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: setting up All Player Franchise JPanel
    private void allPlayersFranchisePanelSet(JPanel allPlayersFranchisePanel, int i) {
        allPlayersFranchisePanel.setBounds(i, 5, 250, 250);
        allPlayersFranchisePanel.setBackground(Color.white);
    }

    // MODIFIES: this
    // EFFECTS: setting up Trading Block JPanel
    private void tradingBlockPanelSet(JPanel jpanelTradingBlock, int i) {
        jpanelTradingBlock.setBounds(i, 5, 250, 250);
        jpanelTradingBlock.setBackground(Color.white);
    }

    // MODIFIES: this
    // EFFECTS: setting up All Players JButton and adds image to Franchise Option Panel
    private void allPlayersButtonSet(JButton allPlayersButton, String s, JPanel jpanelFranchiseOptions) {
        ImageIcon canucksIcon = new ImageIcon("./Photos/Canucks_Logo.png");
        Image canucksImage = canucksIcon.getImage().getScaledInstance(135, 115, Image.SCALE_SMOOTH);
        ImageIcon newCanucksIcon = new ImageIcon(canucksImage);

        JLabel canucksImageLabel = new JLabel();

        allPlayersButton.addActionListener(this::actionPerformed);
        allPlayersButton.setText(s);
        allPlayersButton.setFocusable(false);
        jpanelFranchiseOptions.add(allPlayersButton, JPanel.CENTER_ALIGNMENT);

        canucksImageLabel.setIcon(newCanucksIcon);
        jpanelFranchiseOptions.add(canucksImageLabel);
    }

    // MODIFIES: this
    // EFFECTS: setting up Move To Current Team JButton and add Arrow Icon
    private void moveToCurrRosterSet(JButton moveToCurrentRoster, String s, JPanel jpanelButtons) {
        moveToCurrentRoster.addActionListener(this::actionPerformed);
        moveToCurrentRoster.setText(s);
        moveToCurrentRoster.setFocusable(false);

        ImageIcon leftArrowIcon = new ImageIcon("./Photos/Left_Arrow_Icon.png");
        Image leftArrowImage = leftArrowIcon.getImage().getScaledInstance(120, 50,Image.SCALE_SMOOTH);
        ImageIcon leftArrow = new ImageIcon(leftArrowImage);

        JLabel leftArrowLabel = new JLabel();
        leftArrowLabel.setIcon(leftArrow);

        jpanelButtons.add(moveToCurrentRoster, JPanel.CENTER_ALIGNMENT);
        jpanelButtons.add(leftArrowLabel);
    }

    // MODIFIES: this
    // EFFECTS: setting up Move To Trading Block JButton and add Arrow Icon
    private void moveToTradingBlockSet(JButton moveToTradingBlock, String s, JPanel jpanelButtons) {
        moveToTradingBlock.addActionListener(this::actionPerformed);
        moveToTradingBlock.setText(s);
        moveToTradingBlock.setFocusable(false);

        ImageIcon rightArrowIcon = new ImageIcon("./Photos/Right_Arrow_Icon.png");
        Image rightArrowImage = rightArrowIcon.getImage().getScaledInstance(120, 50,Image.SCALE_SMOOTH);
        ImageIcon rightArrow = new ImageIcon(rightArrowImage);

        JLabel rightArrowLabel = new JLabel();
        rightArrowLabel.setIcon(rightArrow);

        ImageIcon whiteBoardIcon = new ImageIcon("./Photos/White_Board.png");
        Image whiteBoardImage = whiteBoardIcon.getImage().getScaledInstance(140, 20, Image.SCALE_SMOOTH);
        ImageIcon whiteBoard = new ImageIcon(whiteBoardImage);

        JLabel whiteBoardLabel = new JLabel();
        whiteBoardLabel.setIcon(whiteBoard);

        jpanelButtons.add(moveToTradingBlock, JPanel.CENTER_ALIGNMENT);
        jpanelButtons.add(rightArrowLabel);
        jpanelButtons.add(whiteBoardLabel);

    }

    // MODIFIES: this
    // EFFECTS: setting up Trading Block Label JLabel
    private void tradingBlockLabelSet(JLabel currRosterLabel, JLabel tradingBlockLabel,
                                      JPanel jpanelTradingBlock) {
        tradingBlockLabel.setFont(new Font("MV Boli", Font.BOLD, 23));
        tradingBlockLabel.setVerticalTextPosition(currRosterLabel.TOP);
        tradingBlockLabel.setHorizontalTextPosition(currRosterLabel.CENTER);
        jpanelTradingBlock.add(tradingBlockLabel);
    }

    // MODIFIES: this
    // EFFECTS: setting up AllPlayersFranchiseLabel JLabel
    private void allPlayersFranchiseLabelSet(JLabel currRosterLabel, JLabel allPlayersFranchiseLabel,
                                             JPanel allPlayersFranchisePanel) {
        allPlayersFranchiseLabel.setFont(new Font("MV Boli", Font.BOLD, 21));
        allPlayersFranchiseLabel.setVerticalTextPosition(currRosterLabel.TOP);
        allPlayersFranchiseLabel.setHorizontalTextPosition(currRosterLabel.CENTER);
        allPlayersFranchisePanel.add(allPlayersFranchiseLabel);
    }

    // MODIFIES: this
    // EFFECTS: setting up Attributes Jlabel
    private void jlabelAttributesSet(JLabel jlabelAttributes) {
        jlabelAttributes.setBounds(5, 5, 10, 15);
        jlabelAttributes.setFont(new Font("MV Boli", Font.BOLD, 25));
    }

    // MODIFIES: this
    // EFFECTS: adding JMenu to JMenuBar
    private void menuBarAdd() {
        menuBar.add(menuFile);
        menuBar.add(menuHelp);
    }

    // MODIFIES: this
    // EFFECTS: adding JMenuItem to JMenuFile
    private void menuFileAdd() {
        menuFile.add(loadItem);
        menuFile.add(saveItem);
    }

    // MODIFIES: this
    // EFFECTS: setting up Options Label
    private void jlabelOptionsSet(JLabel jlabelOptions) {
        jlabelOptions.setHorizontalTextPosition(JLabel.CENTER);
        jlabelOptions.setVerticalTextPosition(JLabel.TOP);
        jlabelOptions.setFont(new Font("MV Boli", Font.BOLD, 20));
    }

    // MODIFIES: this
    // EFFECTS: setting up Current Roster label
    private void currRosterLabelSet(JLabel currRosterLabel) {
        currRosterLabel.setFont(new Font("MV Boli", Font.BOLD, 21));
        currRosterLabel.setVerticalTextPosition(currRosterLabel.TOP);
        currRosterLabel.setHorizontalTextPosition(currRosterLabel.CENTER);
        jpanelCurrRoster.add(currRosterLabel);
    }

    // MODIFIES: this
    // EFFECTS: setting up Current Roster Panel
    private void jpanelCurrRosterSet(JPanel jpanelCurrRoster, int i) {
        jpanelCurrRoster.setBounds(i, 5, 250, 250);
        jpanelCurrRoster.setBackground(Color.white);
    }

    // MODIFIES: this
    // EFFECTS: setting up Button Panel
    private void jpanelButtonsSet() {
        jpanelButtons.setBounds(465, 5, 250, 250);
        jpanelButtons.setBackground(new Color(255, 255, 255));
    }

    // MODIFIES: this
    // EFFECTS: setting up Attribute Panel
    private void attributePanelSet(JLabel jlabelAttributes) {
        attributesPanel.setBackground(Color.lightGray);
        attributesPanel.setBounds(210, 260, 1015, 75);
        attributesPanel.add(jlabelAttributes);
        attributesPanel.add(playerAttributesList);
    }

    // MODIFIES: this
    // EFFECTS: setting up Franchise Option Panel
    private void franchiseOptionsPanel(JLabel jlabelOptions) {
        jpanelFranchiseOptions.setBackground(new Color(40, 56, 180));
        jpanelFranchiseOptions.setBounds(5, 5, 200, 520);
        jpanelFranchiseOptions.setLayout(new FlowLayout());
        jpanelFranchiseOptions.add(jlabelOptions);
        jpanelFranchiseOptions.add(jbuttonCurrTeam, JPanel.CENTER_ALIGNMENT);
        jpanelFranchiseOptions.add(jbuttonTradingBlock);
        jpanelFranchiseOptions.add(jbuttonEditPlayer);
        jpanelFranchiseOptions.add(jbuttonMakePlayer);
        jpanelFranchiseOptions.add(viewAttributes);
    }

    // MODIFIES: this
    // EFFECTS: setting up JFrame
    private void jframeSet() {
        jframe.setTitle("FranchiseApp");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setJMenuBar(menuBar);
        jframe.setMinimumSize(new Dimension(1231, 580));
        jframe.setLayout(null);
        jframe.setResizable(false);
        jframe.getContentPane().setBackground(new Color(0, 0, 25));
    }

    // MODIFIES: this
    // EFFECTS: setting up View Attributes jButton
    private void viewAttributesSetting(JButton viewAttributes, String s) {
        viewAttributes.addActionListener(this::actionPerformed);
        viewAttributes.setText(s);
        viewAttributes.setFocusable(false);
    }

    // MODIFIES: this
    // EFFECTS: setting up Make Player JButton
    private void makePlayerButton(JButton jbuttonMakePlayer, String s) {
        jbuttonMakePlayer.addActionListener(this::actionPerformed);
        jbuttonMakePlayer.setText(s);
        jbuttonMakePlayer.setFocusable(false);
    }

    // MODIFIES: this
    // EFFECTS: setting up Edit Player JButton
    private void editPlayerButton(JButton jbuttonEditPlayer, String s) {
        jbuttonEditPlayer.addActionListener(this::actionPerformed);
        jbuttonEditPlayer.setText(s);
        jbuttonEditPlayer.setFocusable(false);
    }

    // MODIFIES: this
    // EFFECTS: setting up a JButton for trading block
    private void tradingBlockButton(JButton jbuttonTradingBlock, String s) {
        jbuttonTradingBlock.setBounds(100, 100, 200, 100);
        jbuttonTradingBlock.addActionListener(this::actionPerformed);
        jbuttonTradingBlock.setText(s);
        jbuttonTradingBlock.setFocusable(false);
    }

    // MODIFIES: this
    // EFFECTS: setting up a JButton for current team
    private void currTeamButton(JButton jbuttonCurrTeam, String s) {
        jbuttonCurrTeam.setBounds(100, 100, 200, 100);
        jbuttonCurrTeam.addActionListener(this::actionPerformed);
        jbuttonCurrTeam.setText(s);
        jbuttonCurrTeam.setFocusable(false);
    }

    // EFFECTS: if the ActionEvent e matches the JButton, then an action is performed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbuttonMakePlayer) {
            MakePlayerDialog currentTeam = new MakePlayerDialog(this);
        } else if (e.getSource() == jbuttonEditPlayer) {
            EditPlayerDialog editPlayer = new EditPlayerDialog(this);
        } else if (e.getSource() == jbuttonCurrTeam) {
            currentTeamDialogAction();
        } else if (e.getSource() == jbuttonTradingBlock) {
            tradingBlockDialogAction();
        } else if (e.getSource() == saveItem) {
            saveGMRosters();
        } else if (e.getSource() == loadItem) {
            loadGMRosters();
        } else if (e.getSource() == moveToTradingBlock) {
            movePlayerToTradingBlock();
        } else if (e.getSource() == moveToCurrentRoster) {
            movePlayerToCurrTeam();
        } else if (e.getSource() == viewAttributes) {
            getAttributes();
        } else if (e.getSource() == allPlayersButton) {
            allPlayersDialog();
        }
        SwingUtilities.updateComponentTreeUI(jframe);
    }

    // MODIFIES: this
    // EFFECTS: if a player's status is available, the player is moved from the current roster to the trading block
    public void movePlayerToTradingBlock() {
        canucks.gmAddToTradingBlockFromCurrTeam();
    }

    // MODIFIES: this
    // EFFECTS: if a player's status is "Not Available", the player is moved from the trading block to the
    //          current roster
    public void movePlayerToCurrTeam() {
        canucks.gmAddToCurrRosterFromTradingBlock();
    }

    // EFFECTS: performs the actionPerform method in the CurrentTeamDialog class
    public void currentTeamDialogAction() {
        currentTeamDialog.actionPerform(this);
    }

    // EFFECTS: performs the allPlayersInFranchise method in the AllPlayersOnTeam class
    public void allPlayersDialog() {
        allPlayersOnTeam.allPlayersInFranchise(this);
    }

    // EFFECTS: performs the actionPerform method in the TradingBlockDialog class
    public void tradingBlockDialogAction() {
        tradingBlockDialog.actionPerform(this);
    }

    // EFFECTS: performs the showAttributes method in the ViewAttributesDialog class
    public void getAttributes() {
        viewAttributesDialog.showAttributes(this);
    }

    // GETTERS
    public DefaultListModel getRosterModelCurrTeam() {
        return rosterModelCurrTeam;
    }

    public DefaultListModel getRosterModelTradingBlock() {
        return rosterModelTradingBlock;
    }

    public DefaultListModel getRosterModelPlayerAttributes() {
        return rosterModelPlayerAttributes;
    }

    public DefaultListModel getRosterModelAllPlayer() {
        return rosterModelAllPlayer;
    }

    public JPanel getAttributesPanel() {
        return attributesPanel;
    }

    public JPanel getAttributesPanelTwo() {
        return attributesPanelTwo;
    }

}

