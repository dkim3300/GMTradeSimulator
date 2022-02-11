package Test;

import model.GeneralManager;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestGeneralManager {

    GeneralManager testCurrentTeam;
    GeneralManager testTradingBlock;

    Player Quinn_Hughes;
    Player Elias_Petterson;

    @BeforeEach
    public void setup() {
        testCurrentTeam = new GeneralManager();
        testTradingBlock = new GeneralManager();
        Quinn_Hughes = new Player("Quinn Hughes", "Defense");
        Elias_Petterson = new Player("Elias Pettersson", "Forward");
    }


    @Test
    public void testAddPlayerToTradingBlock() {
        // check that a player is not already there
        checkTradingBlockDoesntContain(Quinn_Hughes);

        // insert the player into the trading block
        testTradingBlock.addPlayerToTradingBlock(Quinn_Hughes);

        // check that the number in the set
        checkTradingBlockContainsOnce(Quinn_Hughes);
    }

    @Test
    public void testRemovePlayerFromTradingBlock() {
        // adding 2 player into the trading block
        testTradingBlock.addPlayerToTradingBlock(Elias_Petterson);
        testTradingBlock.addPlayerToTradingBlock(Quinn_Hughes);

        // checking if the player is in trading block
        assertEquals(2, testTradingBlock.sizeTradingBlock());

        // removing player from trading block
        testTradingBlock.removePlayerFromTradingBlock(Elias_Petterson);

        // checking if player has been removed
        assertFalse(testTradingBlock.containTradingBlock(Elias_Petterson));
    }

    @Test
    public void testAddPlayerToCurrTeam() {
        // checking if there is no players currently on the team
        checkCurrTeamDoesntContain(Quinn_Hughes);

        // adding 2 players to the current team
        testCurrentTeam.addPlayerToCurrTeam(Elias_Petterson);
        testCurrentTeam.addPlayerToCurrTeam(Quinn_Hughes);

        // checking if the 2 players has been added to the current team
        assertEquals(2, testCurrentTeam.sizeCurrTeam());
        assertTrue(testCurrentTeam.containCurrTeam(Elias_Petterson));
        assertTrue(testCurrentTeam.containCurrTeam(Quinn_Hughes));
        assertFalse(testCurrentTeam.sizeCurrTeam() == 3);
    }

    @Test
    public void testRemovePlayerFromCurrTeam() {
        // adding a player to the current team
        testCurrentTeam.addPlayerToCurrTeam(Elias_Petterson);
        assertTrue(testCurrentTeam.containCurrTeam(Elias_Petterson));

        // removing the player from the current team
        testCurrentTeam.removePlayerFromCurrTeam(Elias_Petterson);
        assertFalse(testCurrentTeam.containCurrTeam(Elias_Petterson));
    }



     private void checkTradingBlockDoesntContain(Player player) {
        assertEquals(testTradingBlock.sizeTradingBlock(), 0);
        assertFalse(testTradingBlock.containTradingBlock(player));
     }

    private void checkTradingBlockContainsOnce(Player player) {
        assertEquals(testTradingBlock.sizeTradingBlock(), 1);
        assertTrue(testTradingBlock.containTradingBlock(player));
    }

     private void checkCurrTeamDoesntContain(Player player) {
        assertEquals(testCurrentTeam.sizeCurrTeam(), 0);
        assertFalse(testCurrentTeam.containCurrTeam(player));
    }

     private void checkCurrTeamContainOnce(Player player) {
        assertEquals(testCurrentTeam.sizeCurrTeam(), 1);
        assertTrue(testCurrentTeam.containCurrTeam(player));
     }
}
