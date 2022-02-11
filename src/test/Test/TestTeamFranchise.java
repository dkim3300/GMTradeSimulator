package Test;

import model.GeneralManager;
import model.Player;
import model.RosterManagement;
import model.TeamFranchise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestTeamFranchise {

    ArrayList<Player> tradingBlock;
    ArrayList<Player> currTeam;

    TeamFranchise canucks;

    Player Auston_Matthews;
    Player Patrick_Kane;
    Player Connor_Mcdavid;
    private String teamName;
    private String teamLocation;

    GeneralManager gm;

    @BeforeEach
    public void setup() {
        Auston_Matthews = new Player("Auston Matthews", "Forward");
        Patrick_Kane = new Player("Patrick Kane", "Forward");
        Connor_Mcdavid = new Player("Connor Mcdavid", "Forward");
        teamName = "Canucks";
        teamLocation = "Vancouver";
        gm = new GeneralManager();
        canucks = new TeamFranchise(gm, teamName, teamLocation);
    }

    @Test
    public void testGMAddToTradingBlockFromCurrTeam() {
        // set half of the player's status to available
        Auston_Matthews.setStatusAvailable();
        assertTrue(Auston_Matthews.getStatus() == "Available");
        Patrick_Kane.setStatusNotAvailable();
        assertTrue(Patrick_Kane.getStatus() == "Not Available");
        Connor_Mcdavid.setStatusAvailable();
        assertTrue(Connor_Mcdavid.getStatus() == "Available");

        // adding each of these player's to current roster
        canucks.getGm().getCurrTeam().add(Auston_Matthews);
        assertTrue(canucks.getGm().getCurrTeam().contains(Auston_Matthews));
        assertEquals(1, canucks.getGm().sizeCurrTeam());

        canucks.getGm().getCurrTeam().add(Patrick_Kane);
        assertTrue(canucks.getGm().getCurrTeam().contains(Patrick_Kane));
        assertEquals(2, canucks.getGm().sizeCurrTeam());

        canucks.getGm().getCurrTeam().add(Connor_Mcdavid);
        assertTrue(canucks.getGm().getCurrTeam().contains(Connor_Mcdavid));
        assertEquals(3, canucks.getGm().getCurrTeam().size());

        // checking if a player's status is "Available" and moving it to trading block
        canucks.gmAddToTradingBlockFromCurrTeam();
        assertTrue(canucks.getGm().containTradingBlock(Connor_Mcdavid));
        assertTrue(canucks.getGm().containTradingBlock(Auston_Matthews));
        assertEquals(2, canucks.getGm().sizeTradingBlock());

        // checking if a player's status is "Not Available" and moving it to trading block
        Connor_Mcdavid.setStatusNotAvailable();
        assertTrue(Connor_Mcdavid.getStatus() == "Not Available");

        assertTrue(canucks.getGm().containTradingBlock(Connor_Mcdavid));

        canucks.gmAddToCurrRosterFromTradingBlock();
        assertTrue(canucks.getGm().containCurrTeam(Connor_Mcdavid));
        assertEquals(2, canucks.getGm().sizeCurrTeam());
    }

}
