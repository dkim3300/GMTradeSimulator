package persistence;

import model.GeneralManager;
import model.Player;
import model.TeamFranchise;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class jsonReaderTest extends jsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TeamFranchise canucks = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCurrTeam() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCurrTeam.json");
        try {
            TeamFranchise canucks = reader.read();
            //assertEquals("Canucks", canucks.getName());
            assertEquals(0, canucks.getGm().sizeCurrTeam());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyTradingBlock() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTradingBlock.json");
        try {
            TeamFranchise canucks = reader.read();
            //assertEquals("Canucks", gm.getName());
            assertEquals(0, canucks.getGm().sizeTradingBlock());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralCurrentTeam() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCurrentTeam.json");
        try {
            TeamFranchise canucks = reader.read();
            //assertEquals("Canucks", gm.getName());
            ArrayList<Player> currTeam = canucks.getGm().getCurrTeam();
            assertEquals(3, canucks.getGm().sizeCurrTeam());
            checkPlayer("Connor McDavid", "Forward", "Available", 0, 0, 0, 0, 0, currTeam.get(0));
            checkPlayer("Leon Draisatl", "Forward", "Available", 0, 0, 0, 0, 0, currTeam.get(1));
            checkPlayer("Thatcher Demko", "Goalie", "Available", 0, 0, 0, 0, 0, currTeam.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralTradingBlock() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTradingBlockTeam.json");
        try {
            TeamFranchise canucks = reader.read();
            //assertEquals("Canucks", canucks.getGm().getName());
            ArrayList<Player> tradingBlock = canucks.getGm().getTradingBlock();
            assertEquals(4, canucks.getGm().sizeTradingBlock());
            checkPlayer("Quinn Hughes", "Defense", "Not Available", 1, 2, 3, 4, 5, tradingBlock.get(0));
            checkPlayer("Jack Hughes", "Forward", "Available", 9, 8, 7, 6, 5, tradingBlock.get(1));
            checkPlayer("Elias Pettersson", "Forward", "Not Available", 6, 5, 4, 3, 2, tradingBlock.get(2));
            checkPlayer("John Gibson", "Goalie", "Available", 9, 1, 8, 2, 7, tradingBlock.get(3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReaderGeneralTradingBlockAndCurrTeam() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCurrTeamAndTradingBlock.json");
        try {
            TeamFranchise canucks = reader.read();
            //assertEquals("Canucks", gm.getName());

            ArrayList<Player> tradingBlock = canucks.getGm().getTradingBlock();
            assertEquals(4, canucks.getGm().sizeTradingBlock());
            checkPlayer("Brock Boeser", "Forward", "Not Available", 1, 2, 3, 4, 5, tradingBlock.get(0));
            checkPlayer("Jay Beagle", "Forward", "Available", 9, 8, 7, 6, 5, tradingBlock.get(1));
            checkPlayer("Conor Garland", "Forward", "Not Available", 6, 5, 4, 3, 2, tradingBlock.get(2));
            checkPlayer("Cale Makar", "Defense", "Not Available", 9, 9, 9, 9, 9, tradingBlock.get(3));

            ArrayList<Player> currTeam = canucks.getGm().getCurrTeam();
            assertEquals(4, canucks.getGm().sizeCurrTeam());
            checkPlayer("Adam Fox", "Defense", "Not Available", 9, 9, 9, 9, 9, currTeam.get(0));
            checkPlayer("JT Miller", "Forward", "Not Available", 8, 8, 8, 9, 9, currTeam.get(1));
            checkPlayer("Jack Campbell", "Goalie", "Available", 7, 8, 7, 8, 7, currTeam.get(2));
            checkPlayer("Sam Girard", "Defense", "Not Available", 7, 6, 5, 5, 6, currTeam.get(3));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReaderCurrTeamAndTradingBlockException() {
        JsonReader reader = new JsonReader("./data/testReaderCurrTeamAndBlockException.json");
        try {
            TeamFranchise canucks = reader.read();
            //assertEquals("Canucks", canucks.getGm().getName());

            ArrayList<Player> tradingBlock = canucks.getGm().getTradingBlock();
            assertEquals(2, tradingBlock.size());
            checkPlayer("Maxi Domi", "Forward", "Available", 1, 2, 3, 4, 5, tradingBlock.get(0));
            checkPlayer("Patrik Laine", "Forward", "Not Available", 9, 8, 7, 6, 5, tradingBlock.get(1));

            ArrayList<Player> currTeam = canucks.getGm().getCurrTeam();
            assertEquals(2, currTeam.size());
            checkPlayer("Matthew Highmore", "Forward", "Not Available", 5, 5, 5, 5, 5, currTeam.get(0));
            checkPlayer("Jake Muzzin", "Defense", "Available", 4, 4, 4, 4, 4, currTeam.get(1));

            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

}


