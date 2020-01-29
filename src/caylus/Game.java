package caylus;

import entities.Bailiff;
import entities.Block;
import entities.Provost;
import entities.buildings.Bridge;
import entities.buildings.Building;
import entities.buildings.Castle;
import entities.buildings.Inn;
import entities.players.Player;
import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {

    public static final String WARNING = "--Invalid input--";

    private List<Player> playerList;
    private List<Block> road;
    private List<Building> buildingList;
    private Castle castle = Castle.getCastleInstance();
    private Bridge bridge = Bridge.getBridgeInstance();
    private Bailiff bailiff = Bailiff.getBailiffInstance();
    private Provost provost = Provost.getProvostInstance();

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

    public Castle getCastle() {
        return castle;
    }

    public Bridge getBridge() {
        return bridge;
    }

    public Provost getProvost() {
        return provost;
    }

    public Inn getInn() {
        if (road.get(6).getBuilding() instanceof Inn) {
            return (Inn) road.get(6).getBuilding();
        } else {
            throw new NullPointerException("Inn not found");
        }
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }// end of getters setters
}
