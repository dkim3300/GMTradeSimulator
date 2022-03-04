package persistence;

import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class jsonTest {

    protected void checkPlayer(String playerName, String playerPosition, String status, int shooting, int skating,
                               int puckSkills, int competelevel, int hockeyIQ, Player player) {

        assertEquals(playerName, player.getName());
        assertEquals(playerPosition, player.getPosition());
        assertEquals(status, player.getStatus());
        assertEquals(shooting, player.getShootingRating());
        assertEquals(skating, player.getSkatingRating());
        assertEquals(puckSkills, player.getPuckSkillsRating());
        assertEquals(competelevel, player.getCompeteLevel());
        assertEquals(hockeyIQ, player.getHockeyIQ());
    }
}
