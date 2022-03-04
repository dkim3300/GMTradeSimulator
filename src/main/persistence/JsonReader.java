package persistence;

import model.GeneralManager;
import model.Player;
import model.TeamFranchise;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Referencing WorkRoom
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    private String nm;
    private String location;
    private String name;

    private GeneralManager gm;

    private String source;

    // Referencing WorkRoom
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    public TeamFranchise read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeamFranchise(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses general manager from JSON object and returns it
    private TeamFranchise parseTeamFranchise(JSONObject jsonObject) {
        TeamFranchise canucks = new TeamFranchise(gm, name, location);
        addPlayersToTradingBlock(canucks, jsonObject);  // not sure about this
        addPlayersToCurrTeam(canucks, jsonObject);      // not sure about this
        return canucks;
    }

    // MODIFIES: canucks
    // EFFECTS: parses players from JSON object and adds them to the trading block
    private void addPlayersToTradingBlock(TeamFranchise canucks, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tradingBlock");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayerToTradingBlock(canucks, nextPlayer);  // not sure about this one
        }
    }

    // MODIFIES: canucks
    // EFFECTS: parses player from JSON object and adds it to trading block or curr team
    private Player addPlayer(TeamFranchise canucks, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String position = jsonObject.getString("position");
        String status = jsonObject.getString("status");
        int shooting = jsonObject.getInt("shooting");
        int skating = jsonObject.getInt("skating");
        int puckSkills = jsonObject.getInt("puckSkills");
        int competeLevel = jsonObject.getInt("competeLevel");
        int hockeyIQ = jsonObject.getInt("hockeyIQ");
        Player player = new Player(name, position, status, shooting, skating, puckSkills, competeLevel,
                hockeyIQ);
        return player;
    }

    // MODIFIES:
    // EFFECTS:
    private void addPlayerToCurrTeam(TeamFranchise canucks, JSONObject jsonObject) {
        Player p = addPlayer(canucks, jsonObject);
        canucks.getGm().addPlayerToCurrTeam(p);
    }

    // MODIFIES:
    // EFFECTS:
    private void addPlayerToTradingBlock(TeamFranchise canucks, JSONObject jsonObject) {
        Player p = addPlayer(canucks, jsonObject);
        canucks.getGm().addPlayerToTradingBlock(p);
    }

    // MODIFIES: canucks
    // EFFECTS: parses players from JSON object and adds them to the current team
    private void addPlayersToCurrTeam(TeamFranchise canucks, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("currTeam");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayerToCurrTeam(canucks, nextPlayer);
        }
    }

}
