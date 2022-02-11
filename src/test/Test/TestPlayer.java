package Test;

import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {

    Player Elias_Petterson;
    Player Quinn_Hughes;
    Player Jack_Hughes;

    @BeforeEach
    public void setup() {
        Elias_Petterson = new Player("Elias Pettersson", "Forward");
        Quinn_Hughes = new Player("Quinn Hughes", "Defense");
        Jack_Hughes = new Player("Jack Hughes", "Forward");
    }

    @Test
    public void testSkatingRating() {
        // add a skating rating into the player
        Elias_Petterson.addSkatingRating(50);
        Quinn_Hughes.addSkatingRating(60);
        Jack_Hughes.addSkatingRating(70);

        // check if player's skating rating is implemented
        assertEquals(50, Elias_Petterson.getSkatingRating());
        assertEquals(60, Quinn_Hughes.getSkatingRating());
        assertEquals(70, Jack_Hughes.getSkatingRating());
    }

    @Test
    public void testShootingRating() {
        // add a shooting rating into the player
        Elias_Petterson.addShootingRating(100);
        Quinn_Hughes.addShootingRating(90);
        Jack_Hughes.addShootingRating(80);

        // Check if player's shooting is implemented
        assertEquals(100, Elias_Petterson.getShootingRating());
        assertEquals(90, Quinn_Hughes.getShootingRating());
        assertEquals(80, Jack_Hughes.getShootingRating());
    }

    @Test
    public void testHockeyIQ() {
        // add hockey IQ into player
        Elias_Petterson.addHockeyIQRating(100);
        Quinn_Hughes.addHockeyIQRating(70);
        Jack_Hughes.addHockeyIQRating(40);

        // check if player's hockey IQ is implemented
        assertEquals(100, Elias_Petterson.getHockeyIQ());
        assertEquals(70, Quinn_Hughes.getHockeyIQ());
        assertEquals(40, Jack_Hughes.getHockeyIQ());
    }

    @Test
    public void testCompeteLevel() {
        // add compete level rating to player
        Elias_Petterson.addCompeteLevelRating(66);
        Quinn_Hughes.addCompeteLevelRating(41);
        Jack_Hughes.addCompeteLevelRating(31);

        // check if player's compete level is implemented
        assertEquals(66, Elias_Petterson.getCompeteLevel());
        assertEquals(41, Quinn_Hughes.getCompeteLevel());
        assertEquals(31, Jack_Hughes.getCompeteLevel());
    }

    @Test
    public void testPuckSkill() {
        // add puck skill rating to player
        Elias_Petterson.addPuckSkillsRating(99);
        Quinn_Hughes.addPuckSkillsRating(87);
        Jack_Hughes.addPuckSkillsRating(86);

        // check if player's puck skills is added
        assertEquals(99, Elias_Petterson.getPuckSkillsRating());
        assertEquals(87, Quinn_Hughes.getPuckSkillsRating());
        assertEquals(86, Jack_Hughes.getPuckSkillsRating());
    }

    @Test
    public void testNameAndPosition() {
        // Elias Pettersson
        assertEquals("Elias Pettersson", Elias_Petterson.getName());
        assertEquals("Forward", Elias_Petterson.getPosition());

        // Quinn Hughes
        assertEquals("Quinn Hughes", Quinn_Hughes.getName());
        assertEquals("Defense", Quinn_Hughes.getPosition());

        // Jack Hughes
        assertEquals("Jack Hughes", Jack_Hughes.getName());
        assertEquals("Forward", Jack_Hughes.getPosition());
    }
}
