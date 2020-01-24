package caylus;

import entities.Block;
import entities.buildings.Bridge;
import entities.buildings.Castle;
import entities.buildings.Inn;
import entities.players.Player;
import java.util.List;

public class Game {

    public static final String WARNING = "--Invalid input--";

    protected List<Player> playerList;
    protected List<Block> road;
    public Castle castle = Castle.getCastleInstance();
    public Bridge bridge = Bridge.getBridgeInstance();
    public static Inn inn = Inn.getInnInstance();

    // getters setters
    public List<Player> getPlayerList() {
        return playerList;
    }

    public List<Block> getRoad() {
        return road;
    }

    public void setRoad(List<Block> road) {
        this.road = road;
    }

    public void setPlayerList(List<Player> playerList) {
        if (playerList.size() < 2) {
            throw new IllegalArgumentException("The game is for 2-5 players");
        }
        this.playerList = playerList;
    }

    public String getWARNING() {
        return WARNING;
    }// end of getters setters
}
