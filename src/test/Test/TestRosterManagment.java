package Test;

import model.Player;
import model.RosterManagement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestRosterManagment {

    RosterManagement testRoster1;

    Player Sidney_Crosby;
    Player Brock_Boeser;
    Player Mitch_Marner;

    @BeforeEach
    public void setup() {
        testRoster1 = new RosterManagement();
        Sidney_Crosby = new Player("Sidney Crosby", "Forward");
        Brock_Boeser = new Player("Brock Boeser", "Forward");
        Mitch_Marner = new Player("Mitch Marner", "Forward");
    }

    @Test
    public void testAddPlayerToRoster() {
        // Number of elements in testRoster1
        assertEquals(0, testRoster1.sizeCurrTeam());

        // Adding a player to the roster
        testRoster1.addPlayerToRoster(Sidney_Crosby);

        // Testing if player is added
        assertEquals(1, testRoster1.sizeCurrTeam());

        // Adding another player to the roster
        testRoster1.addPlayerToRoster(Brock_Boeser);

        // Testing if the next player is added
        assertEquals(2, testRoster1.sizeCurrTeam());
    }

    @Test
    public void testUpdateRoster() {
        // adding 2 players to testRoster1
        testRoster1.addPlayerToRoster(Sidney_Crosby);
        testRoster1.addPlayerToRoster(Brock_Boeser);

        // checking if 2 players are added to team
        assertEquals(2, testRoster1.currRoster().size());
    }

    @Test
    public void testRemovePlayer() {
        // adding 1 player to testRoster1
        testRoster1.addPlayerToRoster(Brock_Boeser);
        assertEquals(1, testRoster1.sizeCurrTeam());

        // removing the player from testRoster1
        testRoster1.removePlayerFromRoster(Brock_Boeser);
        assertEquals(0, testRoster1.sizeCurrTeam());
    }
}
