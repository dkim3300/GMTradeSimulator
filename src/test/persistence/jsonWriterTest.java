package persistence;

import model.GeneralManager;
import model.Player;
import model.TeamFranchise;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class jsonWriterTest extends jsonTest {

    private GeneralManager gm;
    private String name;
    private String location;

    @Test
    void testWriterInvalidFile() {
        try {
            TeamFranchise canucks = new TeamFranchise(gm, name, location);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyCurrTeam() {
        try {
            TeamFranchise canucks = new TeamFranchise(gm, name, location);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCurrTeam.json");
            writer.open();
            writer.write(canucks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCurrTeam.json");
            canucks = reader.read();
            assertEquals(0, canucks.getGm().sizeCurrTeam());
        } catch (IOException e) {
            fail("Don't want exception to be caught");
        }
    }

    @Test
    public void testWriterEmptyTradingBlock() {
        try {
            TeamFranchise canucks = new TeamFranchise(gm, name, location);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTradingBlock.json");
            writer.open();
            writer.write(canucks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTradingBlock.json");
            canucks = reader.read();
            assertEquals(0, canucks.getGm().sizeTradingBlock());
        } catch (IOException e) {
            fail("Don't want exception to be caught");
        }
    }

    @Test
    public void testWriterGeneralCurrTeam() {
        try {
            TeamFranchise canucks = new TeamFranchise(gm, name, location);
            canucks.getGm().addPlayerToCurrTeam(new Player("John Doe", "Forward", "Available", 1, 2, 3, 4, 5));
            canucks.getGm().addPlayerToCurrTeam(new Player("Brent Burns", "Defense", "Not Available", 9, 8, 7, 6, 5));
            canucks.getGm().addPlayerToCurrTeam(new Player("Ryan Miller", "Goalie", "Available", 1, 2, 1, 2, 1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCurrTeam.json");
            writer.open();
            writer.write(canucks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCurrTeam.json");
            canucks = reader.read();
            ArrayList<Player> currTeam = canucks.getGm().getCurrTeam();
            assertEquals(3, currTeam.size());
            checkPlayer("John Doe", "Forward", "Available", 1, 2, 3, 4, 5, currTeam.get(0));
            checkPlayer("Brent Burns", "Defense", "Not Available", 9, 8, 7, 6, 5, currTeam.get(1));
            checkPlayer("Ryan Miller", "Goalie", "Available", 1, 2, 1, 2, 1, currTeam.get(2));
        } catch (IOException e) {
            fail("Don't want exception caught");
        }
    }

    @Test
    public void testWriterGeneralTradingBlock() {
        try {
            TeamFranchise canucks = new TeamFranchise(gm, name, location);
            canucks.getGm().addPlayerToTradingBlock(new Player("Bo Horvat", "Forward", "Available", 1, 2, 3, 4, 5));
            canucks.getGm().addPlayerToTradingBlock(new Player("TJ Brodie", "Defense", "Not Available", 9, 8, 7, 6, 5));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTradingBlock.json");
            writer.open();
            writer.write(canucks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTradingBlock.json");
            canucks = reader.read();
            ArrayList<Player> tradingBlock = canucks.getGm().getTradingBlock();
            assertEquals(2, tradingBlock.size());
            checkPlayer("Bo Horvat", "Forward", "Available", 1, 2, 3, 4, 5, tradingBlock.get(0));
            checkPlayer("TJ Brodie", "Defense", "Not Available", 9, 8, 7, 6, 5, tradingBlock.get(1));
        } catch (IOException e) {
            fail("Don't want exception caught");
        }
    }

    @Test
    public void testWriterCurrTeamAndTradingBlock() {
        try {
            TeamFranchise canucks = new TeamFranchise(gm, name, location);
            canucks.getGm().addPlayerToCurrTeam(new Player("Auston Matthews", "Forward", "Not Available", 8, 9, 8, 9, 8));
            canucks.getGm().addPlayerToCurrTeam(new Player("William Nylander", "Forward", "Available", 7, 6, 7, 6, 7));
            canucks.getGm().addPlayerToTradingBlock(new Player("Nils Hoglander", "Forward", "Available", 5, 4, 3, 2, 1));
            canucks.getGm().addPlayerToTradingBlock(new Player("Jack Rathbone", "Defense", "Available", 6, 6, 6, 6, 6));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCurrTeamAndTradingBlock.json");
            writer.open();
            writer.write(canucks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCurrTeamAndTradingBlock.json");
            canucks = reader.read();
            ArrayList<Player> currTeam = canucks.getGm().getCurrTeam();
            ArrayList<Player> tradingBlock = canucks.getGm().getTradingBlock();
            assertEquals(2, currTeam.size());
            assertEquals(2, tradingBlock.size());
            checkPlayer("Auston Matthews", "Forward", "Not Available", 8, 9, 8, 9, 8, currTeam.get(0));
            checkPlayer("William Nylander", "Forward", "Available", 7, 6, 7, 6, 7, currTeam.get(1));
            checkPlayer("Nils Hoglander", "Forward", "Available", 5, 4, 3, 2, 1, tradingBlock.get(0));
            checkPlayer("Jack Rathbone", "Defense", "Available", 6, 6, 6, 6, 6, tradingBlock.get(1));
        } catch (IOException e) {
            fail("Don't want exception caught");
        }
    }

    @Test
    public void testWriterCurrTeamAndTradingBlockExceptionCaught() {
        try {
            TeamFranchise canucks = new TeamFranchise(gm, name, location);
            canucks.getGm().addPlayerToCurrTeam(new Player("Tanner Pearson", "Forward", "Available", 1, 2, 3, 4, 5));
            canucks.getGm().addPlayerToTradingBlock(new Player("Chris Tanev", "Defense", "Not Available", 4, 5, 6, 7, 8));
            JsonWriter writer = new JsonWriter("./data/testWriterCurrTeamAndTradingBlockException.json");
            writer.open();
            writer.write(canucks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterCurrTeamException.json");
            canucks = reader.read();
            ArrayList<Player> currTeam = canucks.getGm().getCurrTeam();
            ArrayList<Player> tradingBlock = canucks.getGm().getTradingBlock();
            assertEquals(1, currTeam.size());
            assertEquals(1, tradingBlock.size());
            checkPlayer("Tanner Pearson", "Forward", "Available", 1, 2, 3, 4, 5, currTeam.get(0));
            checkPlayer("Chris Tanev", "Defense", "Not Available", 4, 5, 6, 7, 8, currTeam.get(0));
        } catch (IOException e) {
            // expected
        }
    }
}

