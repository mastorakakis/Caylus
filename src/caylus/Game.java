package caylus;

import entities.Bailiff;
import entities.Block;
import entities.FavorTable;
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
    public static final int DUNGEON_SCORING = 18;
    public static final int WALLS_SCORING = 24;
    public static final int TOWERS_SCORING = 30;

    private List<Player> playerList;
    private List<Block> road;
    private List<Building> buildingList;
    private FavorTable favorTable;
    private Castle castle = new Castle("Castle");
    private Bridge bridge = new Bridge();
    private Bailiff bailiff = new Bailiff();
    private Provost provost = new Provost();

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

    public Bailiff getBailiff() {
        return bailiff;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public Inn getInn() {
        if (road.get(6).getBuilding() instanceof Inn) {
            return (Inn) road.get(6).getBuilding();
        } else {
            throw new NullPointerException("Inn not found");
        }
    }

    public FavorTable getFavorTable() {
        return favorTable;
    }

    public void setFavorTable(FavorTable favorTable) {
        this.favorTable = favorTable;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }// end of getters setters
}
