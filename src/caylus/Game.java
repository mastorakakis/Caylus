package caylus;

import entities.Block;
import entities.players.Player;
import java.util.List;

public class Game {

    public static final String WARNING = "--Invalid input--";
    public Player[] innPosition = new Player[2];

    protected List<Player> playerList;
    protected List<Block> road;

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
    }

    public Player[] getInnPosition() {
        return innPosition;
    }// end of getters setters
}
