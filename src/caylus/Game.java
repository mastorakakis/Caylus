package caylus;

import entities.Block;
import entities.players.Player;
import java.util.List;

public class Game {

    public static final String warning = "--Invalid input--";
    public static Player[] innPosition = new Player[2];

    protected static List<Player> playerList;
    protected static List<Block> road;

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
    }// end of getters setters
}
